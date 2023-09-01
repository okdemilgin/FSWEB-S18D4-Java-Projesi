package com.workintech.jpamany.service;

import com.workintech.jpamany.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAll();
    Address find(int id);
    Address save(Address address);
    void delete(Address address);
}