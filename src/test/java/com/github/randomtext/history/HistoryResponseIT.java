package com.github.randomtext.history;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by alexey on 6/15/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HistoryResponseIT {

    @Autowired
    private ObjectMapper mapper;

    private HistoryResponse victim;

    @Before
    public void setUp() throws Exception {
        victim = new HistoryResponse.Builder()
                .build();
    }

    @Test
    public void shouldSerialize() throws Exception {
        String result = mapper.writeValueAsString(victim);

        assertThat(result).isEqualTo("");
    }

    @Test
    public void shouldDeserialize() throws Exception {
        String input = "";

        HistoryResponse result = mapper.readValue(input, HistoryResponse.class);

        assertThat(result).isEqualTo(victim);
    }

}