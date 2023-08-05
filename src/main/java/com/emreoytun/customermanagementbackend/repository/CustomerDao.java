package com.emreoytun.customermanagementbackend.repository;

import com.emreoytun.customermanagementbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, Integer> {
    Customer findById(int id);
}
