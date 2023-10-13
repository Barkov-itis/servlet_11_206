package ru.itis.dto;

import lombok.Data;

@Data
public class SigUpForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
