package com.github.randomtext.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping(path = "/betvictor/text")
public class TextController {

    private RandomTextService service;

    @Autowired
    TextController(RandomTextService service) {
        this.service = service;
    }

    @GetMapping
    public DeferredResult<Object> get(@RequestParam(name = "p_start") int start,
                                      @RequestParam(name = "p_end") int end,
                                      @RequestParam(name = "w_count_min") int min,
                                      @RequestParam(name = "w_count_max") int max) {
        // Create DeferredResult
        final DeferredResult<Object> deferredResult = new DeferredResult<>();

        service.compute(start, end, min, max)
                .subscribe(deferredResult::setResult, deferredResult::setErrorResult);

        return deferredResult;
    }
}