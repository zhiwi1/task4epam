package org.epam.handlertask.composite.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;


public class TextLeaf implements Component {
    private static final Logger logger = LogManager.getLogger(TextLeaf.class);
    private final ComponentType leafType = ComponentType.SYMBOL;
    private char value;

    public TextLeaf(char value) {
        this.value = value;
    }


    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }


    @Override
    public ComponentType getTextType() {
        return leafType;
    }

    @Override
    public Object getChild(int index) {
        logger.log(Level.ERROR, "UnsupportedOperationException");
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(Component component) {
        logger.log(Level.ERROR, "UnsupportedOperationException");
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component component) {
        logger.log(Level.ERROR, "UnsupportedOperationException");
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
