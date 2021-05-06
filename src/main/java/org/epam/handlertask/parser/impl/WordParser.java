package org.epam.handlertask.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;
import org.epam.handlertask.composite.impl.Composite;
import org.epam.handlertask.composite.impl.Leaf;
import org.epam.handlertask.parser.ParserChain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordParser implements ParserChain {
//    private ParserChain next;
    private final static Logger logger=LogManager.getLogger();


    @Override
    public void setNext(ParserChain nextChain) {
        throw new UnsupportedOperationException();
//        next = nextChain;
    }

    @Override
    public void processData(String text, Component composite) {

        char[] characters = text.toCharArray();
        for (char character : characters) {
//          logger.info(character);
            composite.add(new Leaf(ComponentType.LETTER, String.valueOf(character)));
        }
    }
}
//    @Override
//    public void parseText(IComposite component, String input) {
//        if (component == null || StringUtils.isEmpty(input)) {
//            logger.info("IN parseText() IN Word: We got a problem : component==null||StringUtils.isEmpty(input) !");
//        } else {
//
//            char[] characters = input.toCharArray();
//            for (char ch : characters) {
//                component.add(new Symbol(ch, LETTER));
//            }
//        }
//    }