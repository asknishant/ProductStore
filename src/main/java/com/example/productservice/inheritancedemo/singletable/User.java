package com.example.productservice.inheritancedemo.singletable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "0") // 0 for user, 1 for mentor and 2 for student
// this creats a single table for all the classes and uses a discriminator column to differentiate between the classes.
// memory inefficient as it stores null values for the columns that are not applicable to the class.
// mostly used when the classes have a lot of common fields.
// non-nullable columns are created for the fields of the parent class.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
}
