package com.github.randomtext.text.history;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by alexey on 6/17/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoryControllerIT {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private HistoryRepository repository;

    private MockMvc mockMvc;

    private List<History> historyList = new ArrayList<>();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        IntStream.range(0, 11).forEach(c -> historyList.add(history(c)));
    }

    @Transactional
    @Test
    public void shouldReturnTenItemsIfMoreIsAvailable() throws Exception {

        repository.save(historyList);

        mockMvc.perform(get("/betvictor/history"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    private History history(int id) {
        return new History.Builder()
                .freqWord("foo" + id)
                .avgParagraphSize(1)
                .avgParagraphProcessingTime(0.1)
                .totalProcessingTime(0.2)
                .build();
    }
}