package com.bookMyShow_App.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserDto {

    private Integer id;

    @NotEmpty(message = "first name should not be empty")
    private String fname;

    @NotEmpty(message = "last name should not be empty")
    private String lname;

    @NotNull(message = "dob should not be null")
    private LocalDate dob;

    @Email(message = "Please enter valid email id")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    //private List<BookingDto> bookingDtos;

}
