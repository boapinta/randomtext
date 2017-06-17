package com.github.randomtext.text.history;

import com.github.randomtext.text.RandomTextService;
import com.github.randomtext.text.TextResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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

    private TextResponse input;

    private final int start = 0;
    private final int end = 1;
    private final int min = 1;
    private final int max = 25;

    @Before
    public void setUp() throws Exception {
        given(randomTextService.compute(start, end, min, max))
                .willReturn(Observable.just(input = new TextResponse.Builder()
                        .withFreqWord("foo")
                        .withAvgParagraphSize(1)
                        .withAvgParagraphProcessingTime(2.0)
                        .withTotalProcessingTime(3.0)
                        .build()
                ));
    }

    @Test
    public void shouldCompute() throws Exception {
        TestSubscriber<TextResponse> testSubscriber = TestSubscriber.create();

        victim.compute(start, end, min, max)
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();

        List<TextResponse> result = testSubscriber.getOnNextEvents();

        assertThat(result).hasSize(1);

        TextResponse next = result.iterator().next();

        assertThat(next).isSameAs(input);

        verify(randomTextService).compute(start, end, min, max);

        verify(publisher).publishEvent(captor.capture());
        TextResponseProcessedEvent value = captor.getValue();

        assertThat(value.getFreqWord()).isEqualTo(input.getFreqWord()).isNotBlank();
        assertThat(value.getTotalProcessingTime()).isEqualTo(input.getTotalProcessingTime()).isNotNull();
        assertThat(value.getAvgParagraphSize()).isEqualTo(input.getAvgParagraphSize()).isNotNull();
        assertThat(value.getAvgParagraphProcessingTime()).isEqualTo(input.getAvgParagraphProcessingTime()).isNotNull();

    }
}