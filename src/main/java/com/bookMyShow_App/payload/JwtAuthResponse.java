package com.bookMyShow_App.payload;

import lombok.Data;

@Data
public class JwtAuthResponse {

    private String email;
    private String password;
    private String auth;

}
