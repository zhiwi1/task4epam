package org.epam.handlertask.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;
import org.epam.handlertask.composite.impl.TextComposite;
import org.epam.handlertask.parser.ParserChain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SentenceParser implements ParserChain {
    private final static Logger logger = LogManager.getLogger();
    private final static String REGEXP_LEXEME = "[ \\r]+";
    private ParserChain nextChain;


    @Override
    public void setNext(ParserChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void processData(String text, Component composite) {

        ComponentType componentType = composite.getTextType();
        if (componentType == ComponentType.SENTENCE) {
            List<String> lexemes = Stream.of(text.split(REGEXP_LEXEME))
                    .map(String::new).collect(Collectors.toList());
            for (String lexeme : lexemes) {
                if (!lexeme.isEmpty()) {
                 //  logger.info(lexeme);
                    TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);
                    composite.add(lexemeComposite);
                    nextChain.processData(lexeme, lexemeComposite);
                }
            }
        } else {
            nextChain.processData(text, composite);
        }
    }
}
