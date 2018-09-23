package org.parsky.position;

public class PositionExtractorService {
    public Position extract (char[] content, int offset) {
        int lines = 1;
        int column = 0;
        for (int i = 0; i < offset; i++) {
            if (isNL(content[i])) {
                lines++;
                column = 0;
            } else {
                column++;
            }
        }
        return new Position(
                lines,
                column
        );
    }

    protected boolean isNL(char character) {
        return (((character == '\n') ||
                (character == '\r')));
    }

    public static class Position {
        private final int line;
        private final int column;

        public Position(int line, int column) {
            this.line = line;
            this.column = column;
        }

        public int getLine() {
            return line;
        }

        public int getColumn() {
            return column;
        }
    }
}
