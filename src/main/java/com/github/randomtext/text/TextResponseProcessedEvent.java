package com.github.randomtext.text;

import org.springframework.util.Assert;

import java.util.Objects;

/**
 * Created by alexey on 6/17/17.
 */
public class TextResponseProcessedEvent {

    private String freqWord;
    private Integer avgParagraphSize;
    private Double avgParagraphProcessingTime;
    private Double totalProcessingTime;

    public static TextResponseProcessedEvent create(TextResponse value) {
        Assert.notNull(value, "this argument is required; it must not be null");
        return new TextResponseProcessedEvent(value.getFreqWord(),value.getAvgParagraphSize(), value.getAvgParagraphProcessingTime(), value.getTotalProcessingTime());
    }

    private TextResponseProcessedEvent(String freqWord, Integer avgParagraphSize, Double avgParagraphProcessingTime, Double totalProcessingTime) {
        this.freqWord = freqWord;
        this.avgParagraphSize = avgParagraphSize;
        this.avgParagraphProcessingTime = avgParagraphProcessingTime;
        this.totalProcessingTime = totalProcessingTime;
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
}
