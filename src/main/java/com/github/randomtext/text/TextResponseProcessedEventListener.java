package com.github.randomtext.text;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by alexey on 6/17/17.
 */
public class TextResponseProcessedEventListener {

    @Async
    @EventListener(TextResponseProcessedEvent.class)
    public void handleTextResponseProcessedEvent(TextResponseProcessedEvent event) {
        System.out.println(event);
    }
}
