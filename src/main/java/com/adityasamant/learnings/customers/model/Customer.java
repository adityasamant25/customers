package com.adityasamant.learnings.customers.model;

import jakarta.validation.constraints.NotEmpty;

public record Customer(Integer id, @NotEmpty String firstName, @NotEmpty String lastName) {
}
