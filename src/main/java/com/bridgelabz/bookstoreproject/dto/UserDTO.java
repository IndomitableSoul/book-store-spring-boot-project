package com.bridgelabz.bookstoreproject.dto;


import lombok.Data;

@Data
public class UserDTO {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String role;

    private boolean enabled;
}
