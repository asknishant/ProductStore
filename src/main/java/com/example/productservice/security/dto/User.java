package com.example.productservice.security.dto;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private String email;
    private String hashedPassword;
    private boolean isEmailVerified;
}
