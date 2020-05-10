package com.barisdere.kafka.springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendCustomerData(Customer customer) {

        try {
            String json = new ObjectMapper().writeValueAsString(customer);
            kafkaTemplate.send("customer_topic", customer.getFirstName(), json);
            System.out.println("Message sent");
        } catch (JsonProcessingException e) {
            System.out.println("Error: when sending kafka message" + e.getMessage());
        }

    }
}
