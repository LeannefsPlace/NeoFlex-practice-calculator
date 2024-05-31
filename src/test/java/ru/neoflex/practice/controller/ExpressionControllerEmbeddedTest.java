package ru.neoflex.practice.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExpressionControllerEmbeddedTest {
    @LocalServerPort
    private int port;
    private final TestRestTemplate restTemplate;
    private final ExpressionController expressionController;
    @Autowired
    public ExpressionControllerEmbeddedTest(TestRestTemplate restTemplate, ExpressionController expressionController) {
        this.restTemplate = restTemplate;
        this.expressionController = expressionController;
    }

    @Test
    void isNotNullController() {
        Assertions.assertThat(expressionController).isNotNull();
    }

    @Test
    void AllTest() {
        restTemplate
                .getForObject("http://localhost:" + port + "/minus/30/10", String.class);
        restTemplate
                .getForObject("http://localhost:" + port + "/plus/10/10", String.class);
        Assertions.assertThat(restTemplate
                .getForObject("http://localhost:" + port + "/expressions", String.class)).contains("20");
    }
}
