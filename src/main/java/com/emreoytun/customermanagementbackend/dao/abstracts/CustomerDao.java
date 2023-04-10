package com.emreoytun.customermanagementbackend.dao.abstracts;

//
/**/

import com.emreoytun.customermanagementbackend.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Dao - Repository
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
    Customer findById(int id);
    boolean existsByUserName(String username);
}
