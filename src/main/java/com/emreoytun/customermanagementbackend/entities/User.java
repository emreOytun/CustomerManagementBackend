package com.emreoytun.customermanagementbackend.entities;

import lombok.*;

import jakarta.persistence.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "\"user\"") // Manually escaping from the reserved SQL keywords.
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", length = 20, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    // IMPORTANT NOTE: FetchType should be EAGER. Because transaction is closed when spring security tries to fetch the authorities.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
