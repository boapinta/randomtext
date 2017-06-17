package com.github.randomtext.text;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alexey Koptenkov on 16/06/2017.
 */
public class TextOut {
    private static final Pattern PATTERN = Pattern.compile("<p>(.+?)<\\/p>(\\r)?");

    private final List<Section> sections;

    static TextOut create(String input) {
        Assert.hasText(input, "this String argument must have text; it must not be null, empty, or blank");

        Matcher matcher = PATTERN.matcher(input);

        List<Section> sections = new ArrayList<>();

        while (matcher.find()) {
            sections.add(Section.create(matcher.group(1)));
        }

        Assert.notEmpty(sections, "this collection must not be empty: it must contain at least 1 element");

        return new TextOut(sections);
    }

    private TextOut(List<Section> sections) {
        this.sections = sections;
    }

    public List<Section> getSections() {
        return Collections.unmodifiableList(sections);
    }

    @Override
    public String toString() {
        return "TextOut{" +
                "sections=" + sections +
                '}';
    }
}
