package com.bookMyShow_App.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
public class UserRoleDto {

    private Integer id;

    private String role;
}
