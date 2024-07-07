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