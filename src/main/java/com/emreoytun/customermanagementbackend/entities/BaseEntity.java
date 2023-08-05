package com.emreoytun.customermanagementbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Data
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    public BaseEntity() {
        createDate = new Date();
        updateDate = new Date();
    }
}
