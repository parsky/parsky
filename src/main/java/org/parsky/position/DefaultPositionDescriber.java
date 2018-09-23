package org.parsky.position;

import org.apache.commons.lang3.StringUtils;
import org.parsky.context.Label;

import java.util.Collection;

public class DefaultPositionDescriber implements PositionDescriber {
    public static DefaultPositionDescriber create (Collection<Label> labels) {
        return new DefaultPositionDescriber(labels, new LineExtractorService(), new PositionExtractorService(), new PrintLabelListService());
    }

    private final Collection<Label> labels;
    private final LineExtractorService lineExtractorService;
    private final PositionExtractorService positionExtractorService;
    private final PrintLabelListService printLabelListService;

    protected DefaultPositionDescriber(Collection<Label> labels, LineExtractorService lineExtractorService, PositionExtractorService positionExtractorService, PrintLabelListService printLabelListService) {
        this.labels = labels;
        this.lineExtractorService = lineExtractorService;
        this.positionExtractorService = positionExtractorService;
        this.printLabelListService = printLabelListService;
    }

    @Override
    public String explain(char[] content, int offset) {
        return String.format(
                "%s%n%s",
                printLocation(content, offset),
                printLabelListService.printTree(labels)
        );
    }



    protected String printLocation(char[] content, int jump) {
        if (content.length == 0) return "No content";

        LineExtractorService.Line line = lineExtractorService.extract(content, jump);
        PositionExtractorService.Position position = positionExtractorService.extract(content, jump);

        return String.format(
                "Location (Line: %d, Column: %d):%n%s%n%s",
                position.getLine(),
                position.getColumn(),
                line.getText(),
                StringUtils.repeat(' ', Math.max(jump - line.getStart(), 0)) + "^^^"
        );
    }
}
