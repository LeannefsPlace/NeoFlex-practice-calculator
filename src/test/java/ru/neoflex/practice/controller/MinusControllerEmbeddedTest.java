package ru.neoflex.practice.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MinusControllerEmbeddedTest {
    @LocalServerPort
    private int port;
    private final TestRestTemplate restTemplate;
    private final MinusController minusController;
    @Autowired
    public MinusControllerEmbeddedTest(TestRestTemplate restTemplate, MinusController minusController) {
        this.restTemplate = restTemplate;
        this.minusController = minusController;
    }

    @Test
    void isNotNullController() {
        Assertions.assertThat(minusController).isNotNull();
    }

    @Test
    void minusTest() {
        Assertions.assertThat(restTemplate
                    .getForObject("http://localhost:" + port + "/minus/3/3", String.class)).contains("0");
    }

    @Test
    void minusUnusualTest() {
        Assertions.assertThat(restTemplate
                .getForObject("http://localhost:" + port + "/minus/-3/3", String.class)).contains("-6");
    }

    @Test
    void minusAllTest() {
        restTemplate
                .getForObject("http://localhost:" + port + "/minus/2/2", String.class);
        Assertions.assertThat(restTemplate
                .getForObject("http://localhost:" + port + "/minus", String.class)).contains("0");
    }
}
