package org.parsky.error;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class PositionService {
    public Pair<Integer, Integer> position (char[] content, int offset) {
        int lines = 1;
        int column = 1;
        for (int i = 0; i < offset; i++) {
            if (isNewLine(content[i])) {
                lines++;
                column = 1;
            } else {
                column++;
            }
        }

        return new ImmutablePair<>(lines, column);
    }


    private boolean isNewLine(char c) {
        return c == '\n' || c == '\r';
    }
}
