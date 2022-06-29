package com.project.springbootexample.controller;

import com.project.springbootexample.entity.Customer;
import com.project.springbootexample.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/data")
public class RestControllerImpl {

    @Autowired
    CustomerService service;

    @GetMapping
    public ResponseEntity getOrderByUserId(){
        return new ResponseEntity<>("Hi, This is rest controller",HttpStatus.OK );
    }

    @GetMapping("/customer")
    public ResponseEntity getCustomers(){
        return new ResponseEntity<>(service.listAll(),HttpStatus.OK );
    }


    @PostMapping("/customer")
    public ResponseEntity postCustomer(@RequestBody Customer customer) throws ExecutionException, InterruptedException {
        return new ResponseEntity<Customer>(service.save(customer), HttpStatus.OK );
    }
}
