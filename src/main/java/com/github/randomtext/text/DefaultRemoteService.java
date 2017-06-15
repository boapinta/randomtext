package com.github.randomtext.text;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by alexey on 6/15/17.
 */
class DefaultRemoteService implements RemoteService {

    private final RandomTextService service;

    private Executor executor = Executors.newFixedThreadPool(10);

    DefaultRemoteService(RandomTextService service) {
        this.service = service;
    }

    @Override
    public Observable<TextResponse> collectTexts(int start, int end, int min, int max) {
        return Observable.range(start, end)
                .flatMap(new Func1<Integer, Observable<RandomTextResponse>>() {
                    @Override
                    public Observable<RandomTextResponse> call(Integer value) {
                        return service.getText(value, min, max)
                                .subscribeOn(Schedulers.computation());
                    }
                })
                .map(new Func1<RandomTextResponse, List<String>>() {
                    @Override
                    public List<String> call(RandomTextResponse value) {
                        System.out.println("Subscriber thread: " + Thread.currentThread().getName());
                        return Arrays.asList(value.getTextOut());
                    }
                })
                .concatMap(new Func1<List<String>, Observable<TextResponse>>() {
                    @Override
                    public Observable<TextResponse> call(List<String> strings) {
                        return Observable.just(new TextResponse.Builder().build());
                    }
                });
    }

    // most frequent word
    // the average paragraph size
    // the average paragraph processing time
    // total processing time

}
