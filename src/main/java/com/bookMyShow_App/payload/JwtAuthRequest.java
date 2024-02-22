package com.bookMyShow_App.payload;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username;
    private String password;
}

