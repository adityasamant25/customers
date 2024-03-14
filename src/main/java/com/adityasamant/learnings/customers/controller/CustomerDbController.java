package com.adityasamant.learnings.customers.controller;

import com.adityasamant.learnings.customers.exception.CustomerNotFoundException;
import com.adityasamant.learnings.customers.model.Customer;
import com.adityasamant.learnings.customers.repository.CustomerDbRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    Customer findById(@PathVariable Integer id) {
        return customerDbRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Customer create(@RequestBody @Valid Customer customer) {
        return customerDbRepository.save(customer);
    }

    @PutMapping("/{id}")
    Customer update(@PathVariable Integer id, @RequestBody @Valid Customer customer) {
        Optional<Customer> existing = customerDbRepository.findById(id);

        if (existing.isPresent()) {
            Customer updated = new Customer(existing.get().id(), customer.firstName(), customer.lastName(), customer.country(), existing.get().version());
            return customerDbRepository.save(updated);
        } else {
            throw new CustomerNotFoundException();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        customerDbRepository.deleteById(id);
    }

}
