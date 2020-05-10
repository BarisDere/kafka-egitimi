package com.barisdere.kafka.akkastreams

import java.util.UUID

import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.stream.ActorMaterializer
import akka.stream.alpakka.elasticsearch.scaladsl.ElasticsearchSink
import akka.stream.alpakka.elasticsearch.{ApiVersion, ElasticsearchWriteSettings, RetryAtFixedRate, WriteMessage}
import org.apache.http.HttpHost
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.elasticsearch.client.RestClient
import spray.json.DefaultJsonProtocol._
import spray.json._

import scala.concurrent.duration.DurationDouble

case class SystemInfoItem(word: String, countTxt: String)


object KafkaApplication extends App {
  implicit val format: JsonFormat[SystemInfoItem] = jsonFormat2(SystemInfoItem)

  implicit val system = ActorSystem("test")
  implicit val actorMaterializer = ActorMaterializer()
  implicit val client = RestClient.builder(new HttpHost("es01", 9200)).build()

  val bootstrapServers = "kafka-1:9092"
  val topic = "customer_analysis_topic"

  val sinkSettings =
    ElasticsearchWriteSettings()
      .withBufferSize(10)
      .withVersionType("internal")
      .withRetryLogic(RetryAtFixedRate(maxRetries = 5, retryInterval = 1.second))
      .withApiVersion(ApiVersion.V7)

  println("Basliyoruz")
  val config = system.settings.config.getConfig("akka.kafka.consumer")
  val consumerSettings =
    ConsumerSettings(config, new StringDeserializer, new StringDeserializer)
      .withBootstrapServers(bootstrapServers)
      .withGroupId("group1")
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  val control =
    Consumer
      .committableSource(consumerSettings, Subscriptions.topics(topic))
      .map { message =>
        println("Creating Elasticsearch msg => key: " + message.record.key() + ", value: " + message.record.value())
        WriteMessage.createCreateMessage(UUID.randomUUID().toString, SystemInfoItem(
          message.record.key(),
          message.record.value()
        ))
      }
      .runWith(
        ElasticsearchSink.create[SystemInfoItem](
          "systemindex",
          "_doc",
          settings = sinkSettings
        )
      )
}
