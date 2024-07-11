package guru.springframework.client;

import com.fasterxml.jackson.databind.JsonNode;
import guru.springframework.model.BeerDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

/**
 * Modified by Pierrot on 10-07-2024.
 */
@Service
public class BeerClientImpl implements BeerClient {

    public static final String API_V3_BEER_PATH = "/api/v3/beer";
    public static final String API_V3_BEER_PATH_BY_ID = API_V3_BEER_PATH + "/{beerID}";

    private final WebClient webClient;

    public BeerClientImpl(WebClient.Builder weClientBuilder) {
        this.webClient = weClientBuilder.build();
    }

    @Override
    public Mono<BeerDTO> createNewBeer(BeerDTO beerDTO) {
        return webClient.post().uri(API_V3_BEER_PATH)
                .body(Mono.just(beerDTO), BeerDTO.class)
                .retrieve()
                .toBodilessEntity()
                .flatMap(voidResponseEntity -> Mono.just(Objects.requireNonNull(voidResponseEntity.getHeaders().get("Location")).getFirst()))
                .map(path -> path.split("/")[path.split("/").length-1] )
                .flatMap(this::getBeerByID);
    }

    @Override
    public Flux<BeerDTO> getBeerByStyle(String beerStyle) {
        return webClient.get().uri(uriBuilder ->
                uriBuilder.path(API_V3_BEER_PATH).queryParam("beerStyle", beerStyle).build())
                .retrieve()
                .bodyToFlux(BeerDTO.class);
    }

    @Override
    public Mono<BeerDTO> getBeerByID(String uuid) {
        return webClient.get().uri(uriBuilder ->
                        uriBuilder.path(API_V3_BEER_PATH_BY_ID).build(uuid))
                .retrieve()
                .bodyToMono(BeerDTO.class);
    }


    @Override
    public Flux<BeerDTO> listBeerDtos() {
        return webClient.get().uri(API_V3_BEER_PATH)
                .retrieve().
                bodyToFlux(ParameterizedTypeReference.forType(BeerDTO.class));
    }

    @Override
    public Flux<JsonNode> listBeersJsonNode() {
        return webClient.get().uri(API_V3_BEER_PATH)
                .retrieve()
                .bodyToFlux(JsonNode.class);
    }

    @Override
    public Flux<String> listBeer() {
        return webClient.get().uri(API_V3_BEER_PATH)
                .retrieve()
                .bodyToFlux(String.class);
    }

    @Override
    public Flux<Map<String, String>> listBeerMap() {
        return webClient.get().uri(API_V3_BEER_PATH)
                .retrieve().bodyToFlux(ParameterizedTypeReference.forType(Map.class));

    }
}
