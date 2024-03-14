package com.adityasamant.learnings.customers.model;


import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record Customer(@Id Integer id, @NotEmpty String firstName, @NotEmpty String lastName, String country) {

}
