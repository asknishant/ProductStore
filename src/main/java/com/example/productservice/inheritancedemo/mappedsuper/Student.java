package com.example.productservice.inheritancedemo.mappedsuper;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Student extends User {
    private int psp;
    private String batch;
}
