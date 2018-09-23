package org.parsky.engine.print.model;

import org.apache.commons.lang3.StringUtils;

public class PrintNode {
    public static PrintNode ignore() {
        return new PrintNode("", true);
    }
    public static PrintNode simple(String content) {
        return new PrintNode(content, true);
    }
    public static PrintNode complex (String content) {
        return new PrintNode(content, false);
    }

    private final String content;
    private final boolean simple;

    public PrintNode(String content, boolean simple) {
        this.content = content;
        this.simple = simple;
    }

    public String getContent() {
        return content;
    }

    public boolean isSimple() {
        return simple;
    }

    public boolean isIgnored () { return StringUtils.isBlank(content); }
}
