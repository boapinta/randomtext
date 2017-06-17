package com.github.randomtext.text;

import org.springframework.context.ApplicationEventPublisher;
import rx.Observable;
import rx.functions.Action1;

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
                .doOnNext(textResponse -> publisher.publishEvent(TextResponseProcessedEvent.create(textResponse)));
    }
}
