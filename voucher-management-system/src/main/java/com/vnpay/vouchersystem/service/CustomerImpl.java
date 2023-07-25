package com.vnpay.vouchersystem.service;

import com.vnpay.vouchersystem.model.Customer;
import com.vnpay.vouchersystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomerImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Set<Customer> findSatisfiedCustomers() {
        return customerRepository.findAllByRedeemedVouchersGreaterThan(5);
    }
}

