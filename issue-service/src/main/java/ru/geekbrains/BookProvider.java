package ru.geekbrains;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.models.Book;


@Service
public class BookProvider {
    private final WebClient webClient;
    public BookProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }
    public Book getRandomBook() {
        return  webClient.get()
                .uri("http://book-service/books/random")
                .retrieve()
                .bodyToMono(Book.class)
                .block();
    }
}
