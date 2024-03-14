package com.adityasamant.learnings.customers.controller;

import com.adityasamant.learnings.customers.model.Customers;
import com.adityasamant.learnings.customers.repository.CustomerDbRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class CustomerDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CustomerDataLoader.class);
    private final ObjectMapper objectMapper;
    private final CustomerDbRepository customerDbRepository;

    public CustomerDataLoader(ObjectMapper objectMapper, CustomerDbRepository customerDbRepository) {
        this.objectMapper = objectMapper;
        this.customerDbRepository = customerDbRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (customerDbRepository.count() == 0) {
            String CUSTOMERS_JSON = "/data/customers.json";
            log.info("Loading customers into database from JSON: {}", CUSTOMERS_JSON);
            try (InputStream inputStream = TypeReference.class.getResourceAsStream(CUSTOMERS_JSON)) {
                Customers response = objectMapper.readValue(inputStream, Customers.class);
                customerDbRepository.saveAll(response.customers());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        }
    }
}
