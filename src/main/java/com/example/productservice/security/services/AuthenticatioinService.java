package com.example.productservice.security.services;

import com.example.productservice.inheritancedemo.joinedtable.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthenticatioinService {
    private RestTemplate restTemplate;
    public AuthenticatioinService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public boolean authenticate(String token) {
        ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://localhost:9000/users/validate/" + token,
                null, User.class);
        if(responseEntity.getBody() != null) {
            return true;
        }
        return false;
    }
}
