package guru.springframework.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient webClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetBeerByBeerStyle() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        webClient.getBeerByStyle("PALE ALE")
                .subscribe(beerDTO -> {
                    System.out.println(beerDTO.getBeerStyle());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerByID() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        webClient.listBeerDtos()
                .flatMap(beerDTO -> webClient.getBeerByID(beerDTO.getId()))
                .subscribe(beerDTOByID -> {
                    System.out.println(beerDTOByID.getBeerName());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerDto() {

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        webClient.listBeerDtos().subscribe(dto -> {
            System.out.println(dto.getBeerName());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerJson() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        webClient.listBeersJsonNode().subscribe(jsonNode -> {
            System.out.println(jsonNode.toPrettyString());
            atomicBoolean.set(true);
        } );

        await().untilTrue(atomicBoolean);
    }

    @Test
    void listBeerMap() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        webClient.listBeerMap().subscribe(resp -> {
            System.out.println(resp);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);

    }
    @Test
    void listBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        webClient.listBeer().subscribe(resp -> {
            System.out.println(resp);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);

    }
}