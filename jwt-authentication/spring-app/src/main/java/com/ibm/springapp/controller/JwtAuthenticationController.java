package com.ibm.springapp.controller;

import com.ibm.springapp.dto.JwtRequest;
import com.ibm.springapp.dto.JwtResponse;
import com.ibm.springapp.service.JwtAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class JwtAuthenticationController {


    private JwtAuthenticationService jwtAuthenticationService;


    @Autowired
    public JwtAuthenticationController(JwtAuthenticationService jwtAuthenticationService) {
        this.jwtAuthenticationService = jwtAuthenticationService;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest){
        String token =null;
        try {
            token=jwtAuthenticationService.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (
                DisabledException e) {
            return new ResponseEntity<>("USER_DISABLED" + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (
                BadCredentialsException e) {
            return new ResponseEntity<>("INVALID_CREDENTIALS"+e.getMessage(), HttpStatus.BAD_REQUEST);
        }


        return ResponseEntity.ok(new JwtResponse(token));

    }
}
