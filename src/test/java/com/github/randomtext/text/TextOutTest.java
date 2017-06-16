package com.github.randomtext.text;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Alexey Koptenkov on 16/06/2017.
 */
public class TextOutTest {

    String input = "<p>Yet agreeably ably more hot before about goodness save oppressive far.<\\/p>\\r<p>Aardvark or snorted considering more one crookedly or much tiger panther much jay before much in echidna fumbling due dire.<\\/p>\\r<p>The a swept gosh amidst.<\\/p>\\r";

    @Test
    public void shouldCreate() throws Exception {
        Pattern pattern = Pattern.compile("<p>(.+?)<\\\\/p>(\r)?");

        Matcher matcher = pattern.matcher(input);

        List<String> strings = new ArrayList<>();

        while (matcher.find()) {
            strings.add(matcher.group(1));
        }

        assertThat(strings).hasSize(3);
    }
}