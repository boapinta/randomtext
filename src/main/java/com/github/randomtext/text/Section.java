package com.github.randomtext.text;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexey Koptenkov on 16/06/2017.
 */
public class Section {

    private final String body;
    private final int size;

    static Section create(String value) {
        Assert.notNull(value);

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

    public List<String> words() {
        return Arrays.asList(body.split("[,!?.\\s]+"));
    }
}
