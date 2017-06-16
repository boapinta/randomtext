package com.github.randomtext.text;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Created by alexey on 6/15/17.
 */
class DefaultRandomTextService implements RandomTextService {

    private final APIService service;

    DefaultRandomTextService(APIService service) {
        this.service = service;
    }

    @Override
    public Observable<TextResponse> compute(int start, int end, int min, int max) {
        return Observable.range(start, end)
                .flatMap(id -> Observable.just(id)
                        .observeOn(Schedulers.computation())
                        .flatMap(pid -> service.getText(pid, min, max)
                                .map(value -> TextOut.create(value.getTextOut()))))
                .toList()
                .map(textOuts -> {
                    List<Section> sections = textOuts.stream()
                            .flatMap(c -> c.getSections().stream())
                            .collect(Collectors.toList());

                    AtomicLong counter = new AtomicLong(0);

                    TextResponse.Builder builder = new TextResponse.Builder()
                            .withAvgParagraphSize(averageParagraphSize(sections));

                    mostFrequentWord(sections, counter).ifPresent(builder::withFreqWord);

                    return builder.withAvgParagraphProcessingTime(counter.doubleValue() / 1000 / sections.size()).build();
                });
    }

    private Optional<String> mostFrequentWord(List<Section> sections, AtomicLong counter) {
        Map<String, Long> map = sections.parallelStream()
                .flatMap(c -> c.splitByWord(counter).entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingLong(Map.Entry::getValue)));

        return map
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(1)
                .map(Map.Entry::getKey)
                .findFirst();
    }

    private int averageParagraphSize(List<Section> sections) {
        return sections.parallelStream()
                .mapToInt(Section::getSize)
                .reduce((a, b) -> a + b)
                .orElseThrow(IllegalArgumentException::new) / sections.size();
    }

}
