package com.github.randomtext.history;

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

    @GetMapping
    @Transactional(readOnly = true)
    public List<History> getLastTen() {
        return repository.findFirst10ByOrderByCreatedDesc();
    }
}