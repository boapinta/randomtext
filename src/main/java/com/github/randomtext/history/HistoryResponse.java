package com.github.randomtext.history;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Created by alexey on 6/15/17.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonDeserialize(builder = HistoryResponse.Builder.class)
class HistoryResponse {

    private HistoryResponse(Builder builder) {
    }

    @JsonPOJOBuilder
    static class Builder {
        HistoryResponse build() {
            return new HistoryResponse(this);
        }
    }
}
