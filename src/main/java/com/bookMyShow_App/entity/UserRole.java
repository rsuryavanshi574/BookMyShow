package com.bookMyShow_App.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UserRole {

    @Id
    private Integer id;

    private String role;
}
