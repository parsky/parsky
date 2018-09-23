package org.parsky.position;

public class LineExtractorService {
    public Line extract (char[] content, int offset) {
        if (offset > content.length) offset = content.length - 1;
        int start = offset;
        int end = offset;

        for (; start > 0 && !isNL(content[start - 1]); start--);
        for (; end < content.length && !isNL(content[end]); end++);

        return new Line(new String(content, start, end - start), start, end);
    }

    protected boolean isNL(char character) {
        return (((character == '\n') ||
                (character == '\r')));
    }

    public static class Line {
        private final String line;
        private final int start;
        private final int end;

        public Line(String line, int start, int end) {
            this.line = line;
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public String getText() {
            return line;
        }
    }
}
