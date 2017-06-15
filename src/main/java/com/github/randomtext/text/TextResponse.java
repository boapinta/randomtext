package com.github.randomtext.text;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

/**
 * Created by alexey on 6/15/17.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonDeserialize(builder = TextResponse.Builder.class)
class TextResponse {

    private String freqWord;
    private int avgParagraphSize;
    private double avgParagraphProcessingTime;
    private double totalProcessingTime;

    private TextResponse(Builder builder) {
        freqWord = builder.freqWord;
        avgParagraphSize = builder.avgParagraphSize;
        avgParagraphProcessingTime = builder.avgParagraphProcessingTime;
        totalProcessingTime = builder.totalProcessingTime;
    }

    public String getFreqWord() {
        return freqWord;
    }

    public int getAvgParagraphSize() {
        return avgParagraphSize;
    }

    public double getAvgParagraphProcessingTime() {
        return avgParagraphProcessingTime;
    }

    public double getTotalProcessingTime() {
        return totalProcessingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextResponse that = (TextResponse) o;
        return avgParagraphSize == that.avgParagraphSize &&
                Double.compare(that.avgParagraphProcessingTime, avgParagraphProcessingTime) == 0 &&
                Double.compare(that.totalProcessingTime, totalProcessingTime) == 0 &&
                Objects.equals(freqWord, that.freqWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(freqWord, avgParagraphSize, avgParagraphProcessingTime, totalProcessingTime);
    }

    @Override
    public String toString() {
        return "TextResponse{" +
                "freqWord='" + freqWord + '\'' +
                ", avgParagraphSize=" + avgParagraphSize +
                ", avgParagraphProcessingTime=" + avgParagraphProcessingTime +
                ", totalProcessingTime=" + totalProcessingTime +
                '}';
    }

    @JsonPOJOBuilder
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    static class Builder {

        private String freqWord;
        private int avgParagraphSize;
        private double avgParagraphProcessingTime;
        private double totalProcessingTime;

        public Builder withFreqWord(String freqWord) {
            this.freqWord = freqWord;
            return this;
        }

        public Builder withAvgParagraphSize(int avgParagraphSize) {
            this.avgParagraphSize = avgParagraphSize;
            return this;
        }

        public Builder withAvgParagraphProcessingTime(double avgParagraphProcessingTime) {
            this.avgParagraphProcessingTime = avgParagraphProcessingTime;
            return this;
        }

        public Builder withTotalProcessingTime(double totalProcessingTime) {
            this.totalProcessingTime = totalProcessingTime;
            return this;
        }

        TextResponse build() {
            return new TextResponse(this);
        }
    }
}
