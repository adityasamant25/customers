package com.adityasamant.learnings.customers.controller;

import com.adityasamant.learnings.customers.exception.CustomerNotFoundException;
import com.adityasamant.learnings.customers.model.Customer;
import com.adityasamant.learnings.customers.repository.CustomerDbRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerDbController.class)
@AutoConfigureMockMvc
public class CustomerDbControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerDbRepository customerDbRepository;

    List<Customer> customers = new ArrayList<>();

    @BeforeEach
    void setUp() {
        // create some customers
        customers = List.of(new Customer(1, "John", "Doe", "Australia", null),
                new Customer(2, "Alice", "Smith", "USA", null),
                new Customer(3, "Bob", "Stevens", "England", null));
    }

    // REST API

    // list
    @Test
    void shouldFindAllCustomers() throws Exception {
        String jsonResponse = """
                [
                    {
                      "id": 1,
                      "firstName": "John",
                      "lastName": "Doe",
                      "country": "Australia",
                      "version": null
                    },
                    {
                      "id": 2,
                      "firstName": "Alice",
                      "lastName": "Smith",
                      "country": "USA",
                      "version": null
                    },
                    {
                      "id": 3,
                      "firstName": "Bob",
                      "lastName": "Stevens",
                      "country": "England",
                      "version": null
                    }
                  ]
                """;
        when(customerDbRepository.findAll()).thenReturn(customers);
        mockMvc.perform(get("/api/db/customers")).andExpect(status().isOk()).andExpect(content().json(jsonResponse));
    }

    @Test
    void shouldFindCustomerWhenGivenValidID() throws Exception {
        when(customerDbRepository.findById(1)).thenReturn(Optional.of(customers.getFirst()));

        var customer = customers.get(0);
        var json = STR."""
            {
                "id":\{customer.id()},
                "firstName":\{customer.firstName()},
                "lastName":\{customer.lastName()},
                "country":\{customer.country()},
                "version": null
            }
        """;
        mockMvc.perform(get("/api/db/customers/1")).andExpect(status().isOk()).andExpect(content().json(json));
    }

    @Test
    void shouldNotFindCustomerWhenGivenInvalidID() throws Exception {
        when(customerDbRepository.findById(999)).thenThrow(CustomerNotFoundException.class);
        mockMvc.perform(get("/api/db/customers/999")).andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewCustomerWhenCustomerIsValid() throws Exception {
        var customer = new Customer(4, "Trent", "Davids", "Germany", null);
        when(customerDbRepository.save(customer)).thenReturn(customer);

        var json = STR."""
        {
            "id":\{customer.id()},
            "firstName":"\{customer.firstName()}",
            "lastName":"\{customer.lastName()}",
            "country":"\{customer.country()}",
            "version": null
        }
        """;

        mockMvc.perform(post("/api/db/customers").contentType("application/json").content(json)).andExpect(status().isCreated());
    }

    @Test
    void shouldNotCreateCustomerWhenCustomerIsInvalid() throws Exception {
        var customer = new Customer(4, "", "Davids", "France", null);
        when(customerDbRepository.save(customer)).thenReturn(customer);

        var json = STR."""
        {
            "id":\{customer.id()},
            "firstName":"\{customer.firstName()}",
            "lastName":"\{customer.lastName()}",
            "country":"\{customer.country()}",
            "version": null
        }
        """;

        mockMvc.perform(post("/api/db/customers").contentType("application/json").content(json)).andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateCustomerWhenGivenValidCustomer() throws Exception {
        Customer updated = new Customer(1, "NewFirstName", "NewLastName", "Spain", 1);

        when(customerDbRepository.findById(1)).thenReturn(Optional.of(updated));
        when(customerDbRepository.save(updated)).thenReturn(updated);

        var requestBody = STR."""
        {
            "id":\{updated.id()},
            "firstName":"\{updated.firstName()}",
            "lastName":"\{updated.lastName()}",
            "country":"\{updated.country()}",
            "version": null
        }
        """;

        mockMvc.perform(put("/api/db/customers/1").contentType("application/json").content(requestBody)).andExpect(status().isOk());
    }

    @Test
    void shouldDeleteCustomerWhenGivenValidId() throws Exception {
        doNothing().when(customerDbRepository).deleteById(1);

        mockMvc.perform(delete("/api/db/customers/1")).andExpect(status().isNoContent());

        verify(customerDbRepository, times(1)).deleteById(1);
    }
}
