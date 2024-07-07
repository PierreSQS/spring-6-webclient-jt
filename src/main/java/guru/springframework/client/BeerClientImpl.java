package guru.springframework.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class BeerClientImpl implements BeerClient {

    private final WebClient webClient;

    public BeerClientImpl(WebClient.Builder webBuilder) {
        this.webClient = webBuilder.baseUrl("http://localhost:8080").build();
    }


    @Override
    public Flux<String> listBeer() {
        return webClient.get().uri("/api/v1/beer", String.class)
                .retrieve().bodyToFlux(String.class);
    }
}
