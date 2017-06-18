package com.github.randomtext.text.history;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by alexey on 6/17/17.
 */
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class History {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "${History.freqWord}", required = true)
    private String freqWord;
    @ApiModelProperty(value = "${History.avgParagraphSize}", required = true)
    private Integer avgParagraphSize;
    @ApiModelProperty(value = "${History.avgParagraphProcessingTime}", required = true)
    private Double avgParagraphProcessingTime;
    @ApiModelProperty(value = "${History.totalProcessingTime}", required = true)
    private Double totalProcessingTime;
    @JsonIgnore
    @Column(updatable = false, insertable = false)
    private LocalDateTime created;

    protected History() {
    }

    private History(Builder builder) {
        freqWord = builder.freqWord;
        avgParagraphSize = builder.avgParagraphSize;
        avgParagraphProcessingTime = builder.avgParagraphProcessingTime;
        totalProcessingTime = builder.totalProcessingTime;
    }

    public Integer getId() {
        return id;
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

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", freqWord='" + freqWord + '\'' +
                ", avgParagraphSize=" + avgParagraphSize +
                ", avgParagraphProcessingTime=" + avgParagraphProcessingTime +
                ", totalProcessingTime=" + totalProcessingTime +
                ", created=" + created +
                '}';
    }

    public static class Builder {
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

        public History build() {
            return new History(this);
        }
    }
}
