package com.vnpay.vouchersystem.repository;

import com.vnpay.vouchersystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Set<Customer> findAllByNumberOfVouchersRedeemedGreaterThan(int value);
}

