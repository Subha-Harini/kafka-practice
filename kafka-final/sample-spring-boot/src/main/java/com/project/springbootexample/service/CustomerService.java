package com.project.springbootexample.service;

import com.project.springbootexample.entity.Customer;
import com.project.springbootexample.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository repo;

    @Autowired
    MessagingService messagingService;

    public Customer save(Customer customer) throws ExecutionException, InterruptedException {
        Customer c = repo.save(customer);
        //publishing message to kafka topic
        messagingService.produce(customer.getEmail());
        return c;
    }

    public List<Customer> listAll() {
        return (List<Customer>) repo.findAll();
    }

    public Customer get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}