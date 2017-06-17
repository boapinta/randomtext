package com.github.randomtext.history;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by alexey on 6/17/17.
 */
public interface HistoryRepository extends CrudRepository<History, Integer> {
    List<History> findFirst10ByOrderByCreatedDesc();
}
