package com.github.randomtext.history;

import com.github.randomtext.history.ProcessedEventRandomTextService;
import com.github.randomtext.history.TextResponseProcessedEvent;
import com.github.randomtext.text.RandomTextService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Created by alexey on 6/17/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProcessedEventRandomTextServiceTest {

    @Mock
    private RandomTextService randomTextService;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private ProcessedEventRandomTextService victim;

    @Captor
    private ArgumentCaptor<TextResponseProcessedEvent> captor;

    @Test
    public void shouldCompute() throws Exception {

    }
}