package org.epam.handlertask.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;
import org.epam.handlertask.composite.impl.Composite;
import org.epam.handlertask.parser.ParserChain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements ParserChain {
    private final static Logger logger=LogManager.getLogger();
    private final static String REGEXP_DELIMETER ="[\\.{3}!?\\.] ";
    private ParserChain nextChain;


    @Override
    public void setNext(ParserChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void processData(String text, Component composite) {
        ComponentType componentType = composite.getTextType();
        if (componentType == ComponentType.PARAGRAPH) {
        Pattern wordPattern = Pattern.compile(REGEXP_DELIMETER);
        Matcher wordMatcher = wordPattern.matcher(text);
        int index=0;
        while (wordMatcher.find()) {
            String sentence=text.substring(index,wordMatcher.end());
            index=wordMatcher.end();
//            String sentence = text.substring(wordMatcher.start(), wordMatcher.end());
//            logger.info(sentence);
            Composite sentenceComposite = new Composite(ComponentType.SENTENCE);
            composite.add(sentenceComposite);
            nextChain.processData(sentence, sentenceComposite);
        }

        } else {
            nextChain.processData(text, composite);
        }
    }
}



