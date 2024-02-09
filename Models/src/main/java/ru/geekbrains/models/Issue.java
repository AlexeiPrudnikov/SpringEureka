package ru.geekbrains.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Issue {
    private UUID id;
    private LocalDate date;
    private Book book;
    private Reader reader;
}
