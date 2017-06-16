package com.github.randomtext.text;

import rx.Observable;

/**
 * Created by alexey on 6/15/17.
 */
public interface RandomTextService {
    Observable<TextResponse> compute(int start, int end, int min, int max);
}
