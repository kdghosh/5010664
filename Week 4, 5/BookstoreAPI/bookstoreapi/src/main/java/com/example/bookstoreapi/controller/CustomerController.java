package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final List<Customer> customers = new ArrayList<>();
    private long idCounter = 1;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customer.setId(idCounter++);
        customers.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    @PostMapping("/register")
public ResponseEntity<Customer> registerCustomer(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam String address) {

    Customer customer = new Customer(idCounter++, name, email, address);
    customers.add(customer);
    return ResponseEntity.status(HttpStatus.CREATED).body(customer);
}

}
