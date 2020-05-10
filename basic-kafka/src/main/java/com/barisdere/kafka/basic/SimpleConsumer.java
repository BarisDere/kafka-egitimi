package com.barisdere.kafka.basic;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

import static java.text.MessageFormat.format;
import static java.time.Duration.ofMillis;

public class SimpleConsumer {

    public static void main(String[] args) {

        Properties consumerProperties = KafkaProperties.consumerProperties();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(consumerProperties);

        consumer.subscribe(Arrays.asList("kafkabasic_topic"));

        System.out.println(format("Start consuming from topic: {0}", "kafkabasic_topic"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(format("Message: offset: {0}, key: {1}, value: {2}, partition: {3}",
                        record.offset(), record.key(), record.value(), record.partition()));
                consumer.commitAsync();
            }

        }
    }

}