package com.github.randomtext.text;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Created by Alexey Koptenkov on 16/06/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultRandomTextServiceTest {

    @Mock
    private APIService service;

    @InjectMocks
    private DefaultRandomTextService victim;

    @Before
    public void setUp() throws Exception {
        given(service.getText(1, 1, 25))
                .willReturn(Observable.just(randomTextResponseFooBar()));

        given(service.getText(2, 1, 25))
                .willReturn(Observable.just(randomTextResponseFoo()));
    }

    @Test
    public void shouldCompute() throws Exception {
        TextResponse next = victim.compute(1, 2, 1, 25).toBlocking().single();

        assertThat(next.getFreqWord()).isEqualTo("Foo");
        assertThat(next.getAvgParagraphSize()).isEqualTo(("Foo.".length() + "Foo Bar.".length())/2);
        assertThat(next.getAvgParagraphProcessingTime()).isNotNull();

        assertThat(next.getTotalProcessingTime()).isNull();

        verify(service).getText(1, 1, 25);
    }

    private RandomTextResponse randomTextResponseFooBar() {
        return new RandomTextResponse.Builder()
                .withAmount(1)
                .withNumber(1)
                .withNumberMax(25)
                .withTextOut("<p>Foo Bar.</p>\r")
                .build();
    }

    private RandomTextResponse randomTextResponseFoo() {
        return new RandomTextResponse.Builder()
                .withAmount(1)
                .withNumber(1)
                .withNumberMax(25)
                .withTextOut("<p>Foo .</p>\r")
                .build();
    }


}