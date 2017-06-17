package com.github.randomtext.text.history;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by alexey on 6/17/17.
 */
class TextResponseProcessedEventListener {

    private final HistoryRepository repository;

    TextResponseProcessedEventListener(HistoryRepository repository) {
        this.repository = repository;
    }

    @Async
    @Transactional
    @EventListener(TextResponseProcessedEvent.class)
    public void handleTextResponseProcessedEvent(TextResponseProcessedEvent event) {
        repository.save(new History.Builder()
                .freqWord(event.getFreqWord())
                .avgParagraphSize(event.getAvgParagraphSize())
                .avgParagraphProcessingTime(event.getAvgParagraphProcessingTime())
                .totalProcessingTime(event.getTotalProcessingTime())
                .build());
    }
}
