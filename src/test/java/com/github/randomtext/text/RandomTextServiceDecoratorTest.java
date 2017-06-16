package com.github.randomtext.text;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Created by Alexey Koptenkov on 16/06/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class RandomTextServiceDecoratorTest {

    @Mock
    private RandomTextService randomTextService;

    @InjectMocks
    private RandomTextServiceDecorator victim;

    private TextResponse textResponse;

    private final int start = 0;
    private final int end = 1;
    private final int min = 1;
    private final int max = 25;

    @Before
    public void setUp() throws Exception {

        given(randomTextService.compute(start, end, min, max))
                .willReturn(Observable.just(textResponse = new TextResponse.Builder()
                        .withFreqWord("foo")
                        .withAvgParagraphSize(1)
                        .withAvgParagraphProcessingTime(2.0)
                        .build()
                ));
    }

    @Test
    public void shouldCompute() throws Exception {
        TestSubscriber<TextResponse> testSubscriber =  TestSubscriber.create();

        victim.compute(start, end, min, max)
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();

        List<TextResponse> result = testSubscriber.getOnNextEvents();

        assertThat(result).hasSize(1);

        TextResponse next = result.iterator().next();

        assertThat(next).isNotSameAs(textResponse);

        assertThat(next.getFreqWord()).isEqualTo(textResponse.getFreqWord());
        assertThat(next.getAvgParagraphSize()).isEqualTo(textResponse.getAvgParagraphSize());
        assertThat(next.getAvgParagraphProcessingTime()).isCloseTo(textResponse.getAvgParagraphProcessingTime(), within(0.1));
        assertThat(next.getTotalProcessingTime()).isNotNull();

        verify(randomTextService).compute(start, end, min, max);
    }
}