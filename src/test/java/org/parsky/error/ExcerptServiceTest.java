package org.parsky.error;

import org.junit.Test;

public class ExcerptServiceTest {
    private ExcerptService underTest = new ExcerptService();

    @Test
    public void test() throws Exception {

        String result = underTest.excerpt("jo\naomel\no".toCharArray(), 4);

        System.out.println(result);
    }
}