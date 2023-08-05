package com.emreoytun.customermanagementbackend.repository;

import com.emreoytun.customermanagementbackend.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDao extends JpaRepository<Post, Integer> {

    List<Post> getPostsByCustomer_id(int customerId);

    void deleteAllByCustomer_id(int customerId);
}
