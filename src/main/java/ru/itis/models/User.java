package ru.itis.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
}