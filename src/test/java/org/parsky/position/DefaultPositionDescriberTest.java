package org.parsky.position;

import org.junit.Test;
import org.parsky.context.Label;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class DefaultPositionDescriberTest {
    private final ArrayList<Label> context = new ArrayList<>();
    private DefaultPositionDescriber underTest = DefaultPositionDescriber.create(context);

    @Test
    public void name() throws Exception {
        context.add(new Label("start"));
        context.add(new Label("if"));

        String text = "example\none big thing\ntest me";
        String result = underTest.explain(text.toCharArray(), text.indexOf("big"));

        assertThat(result, containsString("one big thing"));
        assertThat(result, containsString("start"));
        assertThat(result, containsString("if"));
    }
}