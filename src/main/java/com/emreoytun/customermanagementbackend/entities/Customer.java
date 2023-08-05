package com.emreoytun.customermanagementbackend.entities;

import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY) // We don't want to fetch the posts everytime.
    @Fetch(FetchMode.SUBSELECT) // to solve N+1 query problem
    private List<Post> posts;

}
