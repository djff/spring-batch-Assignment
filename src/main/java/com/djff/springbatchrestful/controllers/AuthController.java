package com.djff.springbatchrestful.controllers;

import com.djff.springbatchrestful.config.jwt.AuthResponse;
import com.djff.springbatchrestful.config.jwt.Util;
import com.djff.springbatchrestful.models.AuthRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final Util jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, Util jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/login")
    public ResponseEntity<String> getLogin(){
        return ResponseEntity.ok("It iw working");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) throws Exception{
        System.out.println("it is working here");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e){
            throw new Exception("Invalid username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        final String jwtToken = jwtUtil.generateToken(userDetails);
        final Date expiresAt = jwtUtil.extractExpiration(jwtToken);

        return ResponseEntity.ok(new AuthResponse(jwtToken, expiresAt));
    }
}
