package com.github.randomtext.text;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by alexey on 6/15/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(locations="classpath:test.properties")
public class TextResponseIT {

    @Autowired
    private ObjectMapper mapper;

    private TextResponse victim;

    @Before
    public void setUp() throws Exception {
        victim = new TextResponse.Builder()
                .withFreqWord("foo")
                .withTotalProcessingTime(0.1)
                .withAvgParagraphProcessingTime(0.2)
                .withAvgParagraphSize(1)
                .build();
    }

    @Test
    public void shouldSerialize() throws Exception {
        String result = mapper.writeValueAsString(victim);

        assertThat(result).isEqualTo("{\"freq_word\":\"foo\",\"avg_paragraph_size\":1,\"avg_paragraph_processing_time\":0.2,\"total_processing_time\":0.1}");
    }

    @Test
    public void shouldDeserialize() throws Exception {
        String input = "{\"freq_word\":\"foo\",\"avg_paragraph_size\":1,\"avg_paragraph_processing_time\":0.2,\"total_processing_time\":0.1}";

        TextResponse result = mapper.readValue(input, TextResponse.class);

        assertThat(result).isEqualTo(victim);
    }

}