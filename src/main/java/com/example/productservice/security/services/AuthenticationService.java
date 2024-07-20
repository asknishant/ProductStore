package com.example.productservice.security.services;

import com.example.productservice.inheritancedemo.joinedtable.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class AuthenticationService { /// we are creating a service to authenticate the user. This is one way
    // other way is to use spring security.
    private RestTemplate restTemplate;
    public AuthenticationService(RestTemplate restTemplate) {
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


/*
* In summary, in JWT:
* https://www.youtube.com/watch?v=Q_DAS69ntQY

For symmetric JWTs, a single secret key is used for both signing and verifying the token, and this secret key is stored securely on the server-side.
For asymmetric JWTs, a private-public key pair is used, with the private key being kept secret on the server-side and the public key being shared openly for signature verification.
* */