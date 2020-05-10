package com.barisdere.kafka.springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private KafkaService kafkaService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody @Valid Customer customer) {
        customer.setTimestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).getEpochSecond());
        kafkaService.sendCustomerData(customer);
        return customer;
    }
}
