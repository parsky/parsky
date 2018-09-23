package org.parsky.position;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LineExtractorServiceTest {
    private final LineExtractorService underTest = new LineExtractorService();

    @Test
    public void extractMiddleLine() throws Exception {

        String text = "one\ntwo\nthree";
        LineExtractorService.Line line = underTest.extract(text.toCharArray(), text.indexOf("two"));

        assertThat(line.getText(), is("two"));
    }

    @Test
    public void extractLastLine() throws Exception {

        String text = "one\ntwo\nthree";
        LineExtractorService.Line line = underTest.extract(text.toCharArray(), text.indexOf("th"));

        assertThat(line.getText(), is("three"));
    }

    @Test
    public void extractSingleLine() throws Exception {

        String text = "onetwothree";
        LineExtractorService.Line line = underTest.extract(text.toCharArray(), text.indexOf("two"));

        assertThat(line.getText(), is("onetwothree"));
    }
}