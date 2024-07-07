package guru.springframework.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient webClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    void listBeer() {
        webClient.listBeer().subscribe(System.out::println);

    }
}