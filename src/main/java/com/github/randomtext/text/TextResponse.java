package com.github.randomtext.text;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Created by alexey on 6/15/17.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonDeserialize(builder = TextResponse.Builder.class)
public class TextResponse {

    @ApiModelProperty(value = "${TextResponse.freqWord}", required = true)
    private String freqWord;
    @ApiModelProperty(value = "${TextResponse.avgParagraphSize}", required = true)
    private Integer avgParagraphSize;
    @ApiModelProperty(value = "${TextResponse.avgParagraphProcessingTime}", required = true)
    private Double avgParagraphProcessingTime;
    @ApiModelProperty(value = "${TextResponse.totalProcessingTime}", required = true)
    private Double totalProcessingTime;

    private TextResponse(Builder builder) {
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
        TextResponse that = (TextResponse) o;
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
        return "TextResponse{" +
                "freqWord='" + freqWord + '\'' +
                ", avgParagraphSize=" + avgParagraphSize +
                ", avgParagraphProcessingTime=" + avgParagraphProcessingTime +
                ", totalProcessingTime=" + totalProcessingTime +
                '}';
    }

    @JsonPOJOBuilder
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Builder {

        private String freqWord;
        private Integer avgParagraphSize;
        private Double avgParagraphProcessingTime;
        private Double totalProcessingTime;

        public Builder from(TextResponse value) {
            freqWord = value.freqWord;
            avgParagraphSize = value.avgParagraphSize;
            avgParagraphProcessingTime = value.avgParagraphProcessingTime;
            totalProcessingTime = value.totalProcessingTime;
            return this;
        }

        public Builder withFreqWord(String value) {
            this.freqWord = value;
            return this;
        }

        public Builder withAvgParagraphSize(Integer value) {
            this.avgParagraphSize = value;
            return this;
        }

        public Builder withAvgParagraphProcessingTime(Double value) {
            this.avgParagraphProcessingTime = value;
            return this;
        }

        public Builder withTotalProcessingTime(Double value) {
            this.totalProcessingTime = value;
            return this;
        }

        public TextResponse build() {
            return new TextResponse(this);
        }
    }
}
