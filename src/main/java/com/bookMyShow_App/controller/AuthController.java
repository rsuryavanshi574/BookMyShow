package com.bookMyShow_App.controller;

import com.bookMyShow_App.exception.ApiException;
import com.bookMyShow_App.payload.JwtAuthRequest;
import com.bookMyShow_App.payload.JwtAuthResponse;
import com.bookMyShow_App.security.CustomUserDetailsService;
import com.bookMyShow_App.security.JwtTokenHelper;
import com.bookMyShow_App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin()
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

        authenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());

        String generatedToken = jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAuth(generatedToken);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);

    }

    private void authenticate(String userName, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);

        try {
            authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException ex){
            throw new ApiException("Invalid username and password");
        }

    }

}
