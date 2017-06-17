package com.github.randomtext.text;

import org.springframework.util.Assert;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Alexey Koptenkov on 16/06/2017.
 */
class Section {
    private static final Pattern PATTERN = Pattern.compile("[,\\-!?/.()ˆ*@#$%'\\[\\]\"\\s]+");

    private final String body;
    private final int size;

    static Section create(String value) {
        Assert.notNull(value, "this argument is required; it must not be null");

        return new Section(value, value.length());
    }

    private Section(String value, int size) {
        this.body = value;
        this.size = size;
    }

    public String getBody() {
        return body;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Section{" +
                "body='" + body + '\'' +
                ", size=" + size +
                '}';
    }

    public Map<String, Long> splitByWord() {
        return PATTERN.splitAsStream(body)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
    }
}
