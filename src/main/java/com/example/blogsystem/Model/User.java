package com.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotEmpty(message = "please write username!")
    @Size(min = 4, message = "please write more than 4 characters!")
    @Column(columnDefinition = "varchar(15) check(username >= 4) not null")//if it doesn't work just switch between not null and check!
    private String username;

    @NotEmpty(message = "please write a password!")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    @Email
    @NotEmpty(message = "please enter email!")
    @Column(columnDefinition = "varchar(30) not null")
    private String email;


    private Date registration_date;

    //extra endponints:
        //get user by email
        //check for the user and password
        //get user by username
        //get user by register date



}
