package com.vnpay.vouchersystem.service;


import com.vnpay.vouchersystem.model.Customer;

import java.util.Set;

public interface CustomerService {

        Set<Customer> findSatisfiedCustomers();
}
