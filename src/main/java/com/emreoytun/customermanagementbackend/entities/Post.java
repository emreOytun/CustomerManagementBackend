package com.emreoytun.customermanagementbackend.entities;

import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseEntity {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDHexGenerator")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(name = "post", nullable = false)
    private String post;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
