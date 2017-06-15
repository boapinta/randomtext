package com.github.randomtext.text;

import rx.Observable;

/**
 * Created by alexey on 6/15/17.
 */
public interface RemoteService {
    Observable<TextResponse> collectTexts(int start, int end, int min, int max);
}
