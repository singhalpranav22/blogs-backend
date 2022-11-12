package com.pranav.blog.controllers;

import com.pranav.blog.payloads.JWTAuthRequest;
import com.pranav.blog.payloads.JWTAuthResponse;
import com.pranav.blog.security.JWTTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JWTTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> createToken(
            @RequestBody JWTAuthRequest jwtAuthRequest
            ) throws Exception {
        this.authenticate(jwtAuthRequest.getUsername(),jwtAuthRequest.getPassword());
        System.out.println(jwtAuthRequest.getUsername());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse(token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    public void authenticate(String username,String password) throws Exception {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch (BadCredentialsException e){
            throw new Exception("Username/password incorrect.");
        }


    }
}
