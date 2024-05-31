package ru.neoflex.practice.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlusControllerEmbeddedTest {
    @LocalServerPort
    private int port;
    private final TestRestTemplate restTemplate;
    private final PlusController plusController;
    @Autowired
    public PlusControllerEmbeddedTest(TestRestTemplate restTemplate, PlusController plusController) {
        this.restTemplate = restTemplate;
        this.plusController = plusController;
    }

    @Test
    void isNotNullController() {
        Assertions.assertThat(plusController).isNotNull();
    }

    @Test
    void minusTest() {
        Assertions.assertThat(restTemplate
                .getForObject("http://localhost:" + port + "/plus/3/3", String.class)).contains("6");
    }

    @Test
    void minusUnusualTest() {
        Assertions.assertThat(restTemplate
                .getForObject("http://localhost:" + port + "/plus/-3/2", String.class)).contains("-1");
    }

    @Test
    void minusAllTest() {
        restTemplate
                .getForObject("http://localhost:" + port + "/plus/2/2", String.class);
        Assertions.assertThat(restTemplate
                .getForObject("http://localhost:" + port + "/plus/", String.class)).contains("4");
    }
}
