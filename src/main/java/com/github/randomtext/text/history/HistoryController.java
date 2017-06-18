package com.github.randomtext.text.history;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/betvictor/history")
public class HistoryController {

    private final HistoryRepository repository;

    @Autowired
    HistoryController(HistoryRepository repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "Get last 10 history entries", response = History.class, responseContainer = "List")
    @GetMapping
    @Transactional(readOnly = true)
    public List<History> getLastTen() {
        return repository.findFirst10ByOrderByCreatedDesc();
    }
}