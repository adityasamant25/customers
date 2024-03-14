package com.adityasamant.learnings.customers.controller;

import com.adityasamant.learnings.customers.model.Customer;
import com.adityasamant.learnings.customers.repository.CustomerDbRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/db/customers")
class CustomerDbController {

    private final CustomerDbRepository customerDbRepository;

    CustomerDbController(CustomerDbRepository customerDbRepository) {
        this.customerDbRepository = customerDbRepository;
    }

    @GetMapping("")
    List<Customer> findAll() {
        return customerDbRepository.findAll();
    }

}
