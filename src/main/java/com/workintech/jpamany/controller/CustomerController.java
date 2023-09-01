package com.workintech.jpamany.controller;

import com.workintech.jpamany.entity.Customer;
import com.workintech.jpamany.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<Customer> find() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable int id) {
        return customerService.find(id);
    }

    @PostMapping("/")
    public Customer save(@RequestBody Customer customer) {
        System.out.println(customer.getAccountList());
        return customerService.save(customer);
    }

    @PutMapping("/{id}")
    public Customer update(@RequestBody Customer customer, @PathVariable int id) {
        Customer foundCustomer = customerService.find(id);
        if (foundCustomer != null) {
            customer.setId(id);
            return customerService.save(customer);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Customer delete(@PathVariable int id) {
        Customer customer = customerService.find(id);
        customerService.delete(customer);
        return customer;
    }
}