package org.epam.handlertask.composite.impl;

import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;


public class Leaf implements Component {
    private ComponentType leafType;
    private String value;

    public Leaf(ComponentType leafType, String value) {
        this.leafType = leafType;
        this.value = value;
    }

    public ComponentType getCompositeType() {
        return leafType;
    }

    public void setLeafType(ComponentType leafType) {
        this.leafType = leafType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public void operation() {

    }

    @Override
    public ComponentType getTextType() {
        return leafType;
    }

    @Override
    public Object getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return value;
    }
}
