package org.epam.handlertask.parser;

import org.epam.handlertask.composite.Component;

public interface ParserChain {
    void setNext(ParserChain nextChain);
    void processData(String text, Component composite);
}
