package com.github.randomtext.text;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value ="Retrieve and compute data from randomtext.me", response = TextResponse.class)
    @GetMapping
    public DeferredResult<TextResponse> get(@ApiParam(value = "${TextController.get.start}") @RequestParam(name = "p_start") int start,
                                            @ApiParam(value = "${TextController.get.end}") @RequestParam(name = "p_end") int end,
                                            @ApiParam(value = "${TextController.get.min}") @RequestParam(name = "w_count_min") int min,
                                            @ApiParam(value = "${TextController.get.max}")@RequestParam(name = "w_count_max") int max) {
        final DeferredResult<TextResponse> deferredResult = new DeferredResult<>();

        service.compute(start, end, min, max)
                .subscribe(deferredResult::setResult, deferredResult::setErrorResult);

        return deferredResult;
    }
}