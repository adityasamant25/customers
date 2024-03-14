package com.adityasamant.learnings.customers.controller;

import com.adityasamant.learnings.customers.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerDbController.class)
@AutoConfigureMockMvc
public class CustomerDbControllerTest {

    @Autowired
    MockMvc mockMvc;

    List<Customer> customers = new ArrayList<>();

    @BeforeEach
    void setUp() {
        // create some customers
        customers = List.of(new Customer(1, "John", "Doe", "Australia"),
                new Customer(2, "Alice", "Smith", "USA"),
                new Customer(3, "Bob", "Stevens", "England"));
    }

    // REST API

    // list
    @Test
    void shouldFindAllCustomers() throws Exception {
        mockMvc.perform(get("/api/db/customers")).andExpect(status().isOk());
    }
}
