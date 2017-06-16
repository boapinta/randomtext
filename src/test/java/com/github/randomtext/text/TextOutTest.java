package com.github.randomtext.text;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Alexey Koptenkov on 16/06/2017.
 */
public class TextOutTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowException__blank() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("A value must not be blank");

        TextOut.create(" ");
    }

    @Test
    public void shouldThrowException__invalid_format() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("A collection must not be empty: it must contain at least 1 element");

        TextOut.create("<b>Elephant hence sudden sensible reindeer much charming messy iguanodon reckless endearingly along trout</b>");
    }

    @Test
    public void shouldCreate() throws Exception {
        TextOut textOut = TextOut.create("<p>Elephant hence sudden sensible reindeer much charming messy iguanodon reckless endearingly along trout because one crud hey inside far yikes and firefly restfully affectionately.</p>\r");

        assertThat(textOut.getSections()).hasSize(1);
    }
}