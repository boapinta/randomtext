package com.github.randomtext.history;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Created by alexey on 6/17/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class TextResponseProcessedEventListenerTest {

    @Mock
    private HistoryRepository repository;

    @InjectMocks
    private TextResponseProcessedEventListener victim;

    @Captor
    private ArgumentCaptor<History> captor;

    private TextResponseProcessedEvent input = textResponseProcessedEvent();

    @Test
    public void shouldHandleTextResponseProcessedEvent() throws Exception {

        victim.handleTextResponseProcessedEvent(input);

        verify(repository).save(captor.capture());
        History value = captor.getValue();

        assertThat(value.getFreqWord()).isEqualTo(input.getFreqWord()).isNotBlank();
        assertThat(value.getTotalProcessingTime()).isEqualTo(input.getTotalProcessingTime()).isNotNull();
        assertThat(value.getAvgParagraphSize()).isEqualTo(input.getAvgParagraphSize()).isNotNull();
        assertThat(value.getAvgParagraphProcessingTime()).isEqualTo(input.getAvgParagraphProcessingTime()).isNotNull();
    }

    private TextResponseProcessedEvent textResponseProcessedEvent() {
        return new TextResponseProcessedEvent.Builder()
                .freqWord("foo")
                .avgParagraphProcessingTime(1.0)
                .totalProcessingTime(2.0)
                .avgParagraphSize(3)
                .build();
    }

}