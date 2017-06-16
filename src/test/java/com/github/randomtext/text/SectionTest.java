package com.github.randomtext.text;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Alexey Koptenkov on 16/06/2017.
 */
public class SectionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowException() throws Exception {
        thrown.expect(IllegalArgumentException.class);

        Section.create(null);
    }

    @Test
    public void shouldSplitByWord() throws Exception {
        Section section = Section.create("Foo Bar Baz Bar.!-/[]()?@#$%ˆ*");

        Map<String, Long> map = section.splitByWord();

        assertThat(map)
                .containsEntry("Foo", 1l)
                .containsEntry("Bar", 2l)
                .containsEntry("Baz", 1l)
                .hasSize(3);
    }

}