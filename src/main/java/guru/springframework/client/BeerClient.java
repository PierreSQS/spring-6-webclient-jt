package guru.springframework.client;


import com.fasterxml.jackson.databind.JsonNode;
import guru.springframework.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface BeerClient {
    Flux<String> listBeer();

    Flux<Map<String,String>> listBeerMap();

    Flux<JsonNode> listBeersJsonNode();

    Flux<BeerDTO> listBeerDtos();

    Mono<BeerDTO> getBeerByID(String uuid);

    Flux<BeerDTO> getBeerByStyle(String beerStyle);

    Mono<BeerDTO> createNewBeer(BeerDTO beerDTO);
}