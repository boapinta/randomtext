package com.github.randomtext.history;

import java.util.Objects;

/**
 * Created by alexey on 6/17/17.
 */
class TextResponseProcessedEvent {

    private String freqWord;
    private Integer avgParagraphSize;
    private Double avgParagraphProcessingTime;
    private Double totalProcessingTime;

    private TextResponseProcessedEvent(Builder builder) {
        freqWord = builder.freqWord;
        avgParagraphSize = builder.avgParagraphSize;
        avgParagraphProcessingTime = builder.avgParagraphProcessingTime;
        totalProcessingTime = builder.totalProcessingTime;
    }

    public String getFreqWord() {
        return freqWord;
    }

    public Integer getAvgParagraphSize() {
        return avgParagraphSize;
    }

    public Double getAvgParagraphProcessingTime() {
        return avgParagraphProcessingTime;
    }

    public Double getTotalProcessingTime() {
        return totalProcessingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextResponseProcessedEvent that = (TextResponseProcessedEvent) o;
        return Objects.equals(freqWord, that.freqWord) &&
                Objects.equals(avgParagraphSize, that.avgParagraphSize) &&
                Objects.equals(avgParagraphProcessingTime, that.avgParagraphProcessingTime) &&
                Objects.equals(totalProcessingTime, that.totalProcessingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(freqWord, avgParagraphSize, avgParagraphProcessingTime, totalProcessingTime);
    }

    @Override
    public String toString() {
        return "TextResponseProcessedEvent{" +
                "freqWord='" + freqWord + '\'' +
                ", avgParagraphSize=" + avgParagraphSize +
                ", avgParagraphProcessingTime=" + avgParagraphProcessingTime +
                ", totalProcessingTime=" + totalProcessingTime +
                '}';
    }

    static class Builder {
        private String freqWord;
        private Integer avgParagraphSize;
        private Double avgParagraphProcessingTime;
        private Double totalProcessingTime;

        public Builder freqWord(String value) {
            this.freqWord = value;
            return this;
        }

        public Builder avgParagraphSize(Integer value) {
            this.avgParagraphSize = value;
            return this;
        }

        public Builder avgParagraphProcessingTime(Double value) {
            this.avgParagraphProcessingTime = value;
            return this;
        }

        public Builder totalProcessingTime(Double value) {
            this.totalProcessingTime = value;
            return this;
        }

        public TextResponseProcessedEvent build() {
            return new TextResponseProcessedEvent(this);
        }
    }
}
