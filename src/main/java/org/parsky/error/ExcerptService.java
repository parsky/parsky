package org.parsky.error;


import org.apache.commons.lang3.StringUtils;

public class ExcerptService {
    public String excerpt (char[] content, int offset) {
        int start = offset;
        int end = offset;

        for (; start > 0 && !isNewLine(content[start]); start--) {}
        for (; end < content.length && !isNewLine(content[end]); end++) {}

        return String.format(
                "%s\n%s^",
                new String(content, start, end - start),
                StringUtils.repeat(' ', offset - start)
        );
    }

    private boolean isNewLine(char c) {
        return c == '\n' || c == '\r';
    }
}
