package ru.neoflex.practice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.neoflex.practice.model.Expression;
import ru.neoflex.practice.service.MinusService;
import ru.neoflex.practice.service.PlusService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalcControllerMockTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlusService plusMock;
    @MockBean
    private MinusService minusMock;

    @Test
    public void checkAll() throws Exception {

        String responseBody = "{\"expressions\": [ { \"expression\": \"50 + 50\", \"result\": 100 }, { \"expression\": \"50 - 50\", \"result\": 0 } ] }";

        given(plusMock.getAll())
                .willReturn(List.of(new Expression[]{new Expression("50 + 50", 100)}));
        given(minusMock.getAll())
                .willReturn(List.of(new Expression[]{new Expression("50 - 50", 0)}));

        this.mockMvc.perform(get("/expressions")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));
    }

    @Test
    public void checkPlus() throws Exception {

        String responseBody = "{\"plusExpressions\": [ { \"expression\": \"50 + 50\", \"result\": 100 } ] }";

        given(plusMock.getAll())
                .willReturn(List.of(new Expression[]{new Expression("50 + 50", 100)}));

        this.mockMvc.perform(get("/plus")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));
    }

    @Test
    public void checkMinus() throws Exception {

        String responseBody = "{\"minusExpressions\": [ { \"expression\": \"50 - 50\", \"result\": 0 } ] }";

        given(minusMock.getAll())
                .willReturn(List.of(new Expression[]{new Expression("50 - 50", 0)}));

        this.mockMvc.perform(get("/minus")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));
    }
}
