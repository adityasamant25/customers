package com.adityasamant.learnings.customers.repository;

import com.adityasamant.learnings.customers.model.Customer;
import org.springframework.data.repository.ListCrudRepository;

public interface CustomerDbRepository extends ListCrudRepository<Customer, Integer> {
}
