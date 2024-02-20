package ru.geekbrains;

import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.annotation.Timer;
import ru.geekbrains.models.Issue;

import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/issues")
@Timer
public class IssueController {
    private final ReaderProvider readerProvider;
    private final BookProvider bookProvider;
    private final List<Issue> issues;
    private final Faker faker;

    public IssueController(ReaderProvider readerProvider, BookProvider bookProvider) {
        this.readerProvider = readerProvider;
        this.bookProvider = bookProvider;
        issues = new ArrayList<>();
        faker = new Faker();
        refreshData();
    }
    private void refreshData(){
        issues.clear();
        for (int i = 0; i < 15; i++) {
            Issue issue = new Issue();
            issue.setId(UUID.randomUUID());
            issue.setBook(bookProvider.getRandomBook());
            issue.setReader(readerProvider.getRandomReader());
            Date between = faker.date().between(startOfYear(), endOfYear());
            issue.setDate(between.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            issues.add(issue);
        }
    }
    private Date startOfYear() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, 2024);
        instance.set(Calendar.MONTH, Calendar.JANUARY);
        instance.set(Calendar.DAY_OF_MONTH, 1);
        return instance.getTime();
    }

    private Date endOfYear() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, 2024);
        instance.set(Calendar.MONTH, Calendar.DECEMBER);
        instance.set(Calendar.DAY_OF_MONTH, 31);
        return instance.getTime();
    }
    @GetMapping
    public List<Issue> getAll() {
        return issues;
    }

    @GetMapping("/refresh")
    public List<Issue> refresh() {
        refreshData();
        return issues;
    }
}
