package ru.neoflex.practice.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalcControllerEmbeddedTest {
    @LocalServerPort
    private int port;
    private final CalcController CalcController;
    private final TestRestTemplate restTemplate;
    @Autowired
    public CalcControllerEmbeddedTest(ru.neoflex.practice.controller.CalcController calcController, TestRestTemplate restTemplate) {
        CalcController = calcController;
        this.restTemplate = restTemplate;
    }

    @Test
    void isNotNullController() {
        Assertions.assertThat(CalcController).isNotNull();
    }

    @Test
    void plusResultTest() {
        Assertions.assertThat(restTemplate
                .getForObject("http://localhost:" + port + "/plus/10/10", String.class)).contains("20");
    }

    @Test
    void minusResultTest() {
        Assertions.assertThat(restTemplate
                .getForObject("http://localhost:" + port + "/minus/30/10", String.class)).contains("20");
    }

    @Test
    void AllTest() {
        restTemplate
                .getForObject("http://localhost:" + port + "/minus/30/10", String.class);
        restTemplate
                .getForObject("http://localhost:" + port + "/plus/10/10", String.class);
        Assertions.assertThat(restTemplate
                .getForObject("http://localhost:" + port + "/expressions", String.class).contains("20"));
    }
}
