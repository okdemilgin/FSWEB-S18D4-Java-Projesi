package com.workintech.jpamany.service;

import com.workintech.jpamany.entity.Address;
import com.workintech.jpamany.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer find(int id);
    Customer save(Customer customer);
    void delete(Customer customer);
}