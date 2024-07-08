package guru.springframework.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
public class BeerClientImpl implements BeerClient {

    public static final String API_V1_BEER_PATH = "/api/v1/beer";

    private final WebClient webClient;

    public BeerClientImpl(WebClient.Builder webBuilder) {
        this.webClient = webBuilder.baseUrl("http://localhost:8080").build();
    }

    @Override
    public Flux<JsonNode> listBeersJsonNode() {
        return webClient.get().uri(API_V1_BEER_PATH).retrieve().bodyToFlux(JsonNode.class);
    }

    @Override
    public Flux<String> listBeer() {
        return webClient.get().uri(API_V1_BEER_PATH, String.class)
                .retrieve().bodyToFlux(String.class);
    }

    @Override
    public Flux<Map<String, String>> listBeerMap() {
        return webClient.get().uri(API_V1_BEER_PATH)
                .retrieve().bodyToFlux(ParameterizedTypeReference.forType(Map.class));

    }
}
