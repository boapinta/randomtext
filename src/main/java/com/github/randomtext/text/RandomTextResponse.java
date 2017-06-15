package com.github.randomtext.text;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

/**
 * Created by alexey on 6/15/17.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonDeserialize(builder = RandomTextResponse.Builder.class)
class RandomTextResponse {

    private Integer amount;
    private Integer number;
    private Integer numberMax;
    private String textOut;

    private RandomTextResponse(Builder builder) {
        amount = builder.amount;
        number = builder.number;
        numberMax = builder.numberMax;
        textOut = builder.textOut;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getNumberMax() {
        return numberMax;
    }

    public String getTextOut() {
        return textOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RandomTextResponse that = (RandomTextResponse) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(number, that.number) &&
                Objects.equals(numberMax, that.numberMax) &&
                Objects.equals(textOut, that.textOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, number, numberMax, textOut);
    }

    @Override
    public String toString() {
        return "RandomTextResponse{" +
                "amount=" + amount +
                ", number=" + number +
                ", numberMax=" + numberMax +
                ", textOut='" + textOut + '\'' +
                '}';
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    static class Builder {
        private Integer amount;
        private Integer number;
        private Integer numberMax;
        private String textOut;

        public Builder withAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public Builder withNumber(Integer number) {
            this.number = number;
            return this;
        }

        public Builder withNumberMax(Integer numberMax) {
            this.numberMax = numberMax;
            return this;
        }

        public Builder withTextOut(String textOut) {
            this.textOut = textOut;
            return this;
        }

        RandomTextResponse build() {
            return new RandomTextResponse(this);
        }
    }
}
