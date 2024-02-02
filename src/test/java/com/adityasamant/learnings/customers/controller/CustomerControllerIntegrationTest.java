package com.adityasamant.learnings.customers.controller;

import com.adityasamant.learnings.customers.model.Customer;
import com.adityasamant.learnings.customers.repository.CustomerCollectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CustomerCollectionRepository customerCollectionRepository;

    List<Customer> customers = new ArrayList<>();

    @BeforeEach
    void setUp() {
        customers = List.of(new Customer(1, "John", "Doe"),
                new Customer(2, "Alice", "Smith"),
                new Customer(3, "Bob", "Stevens"));
    }

    @Test
    void findAll() throws Exception {
        String jsonResponse = """
                [
                    {
                        "id":1,
                        "firstName":"John",
                        "lastName":"Doe"
                    },
                    {
                        "id":2,
                        "firstName":"Alice",
                        "lastName":"Smith"
                    },
                    {
                        "id":3,
                        "firstName":"Bob",
                        "lastName":"Stevens"
                    }
                ]
                """;
        when(customerCollectionRepository.findAll()).thenReturn(customers);
        mvc.perform(get("/api/customers"))
                .andExpect(status().isOk()).andExpect(content().json(jsonResponse));
    }
}