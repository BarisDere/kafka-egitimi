package com.barisdere.kafka.springboot;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Customer {

    @NotNull
    private Integer id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private Long timestamp;

    @NotBlank
    private String metaInformation;

    public String getMetaInformation() {
        return metaInformation;
    }

    public void setMetaInformation(String metaInformation) {
        this.metaInformation = metaInformation;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
