package com.exam.controller;

import com.exam.config.JwtUtils;
import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.model.User;
import com.exam.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch (UsernameNotFoundException e){
            throw new Exception("User not found");

        }
       UserDetails userDetails= userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token  = jwtUtils.generateToken(userDetails);
       return ResponseEntity.ok(new JwtResponse(token));

    }

    private void authenticate(String username,String password) throws Exception {
        try {
     authenticationManager.authenticate(
             new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException d){
            throw new Exception("User disable " +d.getMessage());

        }catch (BadCredentialsException s){
            throw new Exception("Invalid Token " +s.getMessage());
        }
    }

    @GetMapping("/get-current-user")
    public User getCurrentUser(Principal principal){
        return ((User) userDetailsService.loadUserByUsername(principal.getName()));

    }
}
