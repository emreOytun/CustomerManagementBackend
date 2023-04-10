package com.emreoytun.customermanagementbackend.dao.abstracts;

import com.emreoytun.customermanagementbackend.entities.concretes.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao extends JpaRepository<Post, Integer> {
    void deleteAllByCustomerId(int id);
}
