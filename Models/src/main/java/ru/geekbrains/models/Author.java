package ru.geekbrains.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Author {
    private UUID id;
    private String lastName;
    private String firstName;
}
