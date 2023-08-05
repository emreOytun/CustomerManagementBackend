package com.emreoytun.customermanagementbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.emreoytun.customermanagementbackend.entities")
@ComponentScan(basePackages = "com.emreoytun.customermanagementdata")
@ComponentScan(basePackages = "com.emreoytun.customermanagementbackend")
@EnableJpaRepositories("com.emreoytun.customermanagementbackend.repository")
@EnableDiscoveryClient
public class CustomerManagementBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerManagementBackendApplication.class, args);
    }

}
