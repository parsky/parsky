package org.parsky.sequence.transform;

import com.google.common.base.Function;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class TransformationsTest {
    @Test
    public void synonyms() throws Exception {
        assertThat(Transformations.as(String.class), instanceOf(CastTransformation.class));
        assertThat(Transformations.as("label", String.class), instanceOf(LabelledTransformation.class));
        assertThat(Transformations.constant("test"), instanceOf(ConstantTransformation.class));
        assertThat(Transformations.constant("label", "test"), instanceOf(LabelledTransformation.class));
        assertThat(Transformations.identity(), instanceOf(IdentityTransformation.class));
        assertThat(Transformations.identity("label"), instanceOf(LabelledTransformation.class));
        assertThat(Transformations.fromString(mock(Function.class)), instanceOf(ContentTransformation.class));
        assertThat(Transformations.fromString("label", mock(Function.class)), instanceOf(LabelledTransformation.class));
        assertThat(Transformations.from(mock(Function.class)), instanceOf(ContentTransformation.class));
        assertThat(Transformations.from("label", mock(Function.class)), instanceOf(LabelledTransformation.class));
        assertThat(Transformations.fromContentList(mock(Function.class)), instanceOf(ListContentTransformation.class));
        assertThat(Transformations.fromContentList("label", mock(Function.class)), instanceOf(LabelledTransformation.class));
    }
}