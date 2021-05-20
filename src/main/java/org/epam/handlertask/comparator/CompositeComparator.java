package org.epam.handlertask.comparator;

import org.epam.handlertask.composite.Component;

import java.util.Comparator;

public interface CompositeComparator extends Comparator<Component> {
    int compare(Component firstComponent, Component secondComponent);
}
