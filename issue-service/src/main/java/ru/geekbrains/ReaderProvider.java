package ru.geekbrains;

import org.springframework.stereotype.Service;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.models.Reader;
@Service
public class ReaderProvider {
    private final WebClient webClient;
    public ReaderProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }
    public Reader getRandomReader() {
        return  webClient.get()
                .uri("http://reader-service/readers/random")
                .retrieve()
                .bodyToMono(Reader.class)
                .block();
    }
}
