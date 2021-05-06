package org.epam.handlertask.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;
import org.epam.handlertask.composite.impl.Composite;
import org.epam.handlertask.parser.ParserChain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextParser implements ParserChain {
    private final static Logger logger = LogManager.getLogger();
    private final static String REGEXP_PARAGRAPH = "([ ]{3})";
    private ParserChain nextChain;


    @Override
    public void setNext(ParserChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void processData(String text, Component composite) {
        ComponentType componentType = composite.getTextType();
        if (componentType == ComponentType.TEXT) {
            List<String> paragraphs = Stream.of(text.split(REGEXP_PARAGRAPH))
                    .map(String::new).map(p->p.replaceAll("\n"," ")).collect(Collectors.toList());

            for (String paragraph : paragraphs) {

                if (!paragraph.equals("")) {
                    Composite paragraphComposite = new Composite(ComponentType.PARAGRAPH);
                    composite.add(paragraphComposite);
                    nextChain.processData(paragraph, paragraphComposite);
                }
            }
        } else {
            nextChain.processData(text, composite);
        }
    }
}
