package com.github.randomtext.history;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by alexey on 6/17/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HistoryIT {

    @Autowired
    private ObjectMapper mapper;

    private History victim;

    @Before
    public void setUp() throws Exception {
        victim = new History.Builder()
                .freqWord("foo")
                .totalProcessingTime(0.1)
                .avgParagraphProcessingTime(0.2)
                .avgParagraphSize(1)
                .build();

        setInternalState(victim, "id", 1);
        setInternalState(victim, "created", LocalDateTime.now());
    }

    @Test
    public void shouldSerialize() throws Exception {

        String result = mapper.writeValueAsString(victim);

        assertThat(result).isEqualTo("{\"freq_word\":\"foo\",\"avg_paragraph_size\":1,\"avg_paragraph_processing_time\":0.2,\"total_processing_time\":0.1}");
    }

    private void setInternalState(Object target, String fieldName, Object value) {
        Field fieldId = ReflectionUtils.findField(target.getClass(), fieldName);
        ReflectionUtils.makeAccessible(fieldId);
        ReflectionUtils.setField(fieldId, target, value);
    }
}