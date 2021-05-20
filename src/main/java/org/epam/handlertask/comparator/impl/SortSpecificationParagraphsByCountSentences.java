package org.epam.handlertask.comparator.impl;

import org.epam.handlertask.comparator.CompositeComparator;
import org.epam.handlertask.composite.Component;

public class SortSpecificationParagraphsByCountSentences implements CompositeComparator {
    @Override
    public int compare(Component firstComponent, Component secondComponent) {
        int result = 0;
        double firstSize = firstComponent.getComponentsSize();
        double secondSize = secondComponent.getComponentsSize();
        if (firstSize > secondSize) {
            result = 1;
        } else if (firstSize < secondSize) {
            result = -1;
        }
        return result;
    }
}
