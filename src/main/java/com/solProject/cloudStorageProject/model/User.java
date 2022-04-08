package com.solProject.cloudStorageProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {

    private Integer userId;
    private String username;
    private String salt;
    private String password;
    private String firstname;
    private String lastname;

}

