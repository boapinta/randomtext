package com.github.randomtext.text;

import org.springframework.util.StopWatch;
import rx.Observable;

/**
 * Created by Alexey Koptenkov on 16/06/2017.
 */
class RandomTextServiceDecorator implements RandomTextService {

    private final RandomTextService delegate;

    RandomTextServiceDecorator(RandomTextService delegate) {
        this.delegate = delegate;
    }

    @Override
    public Observable<TextResponse> compute(int start, int end, int min, int max) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        return delegate.compute(start, end, min, max)
                .map(textResponse -> {
                    stopWatch.stop();

                    return new TextResponse.Builder()
                            .from(textResponse)
                            .withTotalProcessingTime(stopWatch.getTotalTimeSeconds())
                            .build();
                });
    }
}
