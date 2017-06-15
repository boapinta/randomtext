package com.github.randomtext.history;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by alexey on 6/15/17.
 */
@Configuration
public class HistoryConfiguration {


    @RestController
    @RequestMapping(path = "/betvictor/history")
    static class HistoryController {


    }
}
