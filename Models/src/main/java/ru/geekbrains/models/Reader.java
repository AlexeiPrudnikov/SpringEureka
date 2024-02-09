package ru.geekbrains.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Reader {
    private UUID id;
    private String lastName;
    private String firstName;
}
