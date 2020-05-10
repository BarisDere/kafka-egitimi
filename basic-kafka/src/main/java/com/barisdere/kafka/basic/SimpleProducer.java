package com.barisdere.kafka.basic;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SimpleProducer {

    public static void main(String[] args) {
        int count = 50;
        String msgText = "This is a test";

        Properties props = KafkaProperties.producerProperties();

        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        System.out.println("Started producing messages");
        for (int i = 0; i < count; i++) {
            String msg = msgText + " index: " + i;
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(
                    "kafkabasic_topic",
                    Integer.toString(i),
                    msg);
            producer.send(producerRecord);
        }
        System.out.println(count + " messages sent successfully");

        producer.close();
    }

}
