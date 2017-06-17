package com.github.randomtext.history;

import com.github.randomtext.text.RandomTextService;
import com.github.randomtext.text.TextResponse;
import org.springframework.context.ApplicationEventPublisher;
import rx.Observable;

/**
 * Created by alexey on 6/17/17.
 */
class ProcessedEventRandomTextService implements RandomTextService {

    private final RandomTextService delegate;
    private final ApplicationEventPublisher publisher;

    ProcessedEventRandomTextService(RandomTextService delegate, ApplicationEventPublisher publisher) {
        this.delegate = delegate;
        this.publisher = publisher;
    }

    @Override
    public Observable<TextResponse> compute(int start, int end, int min, int max) {
        return delegate.compute(start, end, min, max)
                .doOnNext(res -> publisher.publishEvent(new TextResponseProcessedEvent.Builder()
                        .freqWord(res.getFreqWord())
                        .avgParagraphProcessingTime(res.getAvgParagraphProcessingTime())
                        .avgParagraphSize(res.getAvgParagraphSize())
                        .totalProcessingTime(res.getTotalProcessingTime())
                        .build()));
    }
}
