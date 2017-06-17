package com.github.randomtext.text;

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
public class RandomTextResponseIT {

    @Autowired
    private ObjectMapper mapper;

    private RandomTextResponse victim;

    @Before
    public void setUp() throws Exception {
        victim = new RandomTextResponse.Builder()
                .withAmount(1)
                .withNumber(2)
                .withNumberMax(3)
                .withTextOut("textOut")
                .build();
    }

    @Test
    public void shouldSerialize() throws Exception {
        String result = mapper.writeValueAsString(victim);

        assertThat(result).isEqualTo("{\"amount\":1,\"number\":2,\"number_max\":3,\"text_out\":\"textOut\"}");
    }

    @Test
    public void shouldDeserialize() throws Exception {
        String input = "{\"type\":\"gibberish\",\"amount\":1,\"number\":2,\"number_max\":3,\"format\":\"p\",\"time\":\"09:22:48\",\"text_out\":\"textOut\"}";

        RandomTextResponse result = mapper.readValue(input, RandomTextResponse.class);

        assertThat(result).isEqualTo(victim);
    }
}