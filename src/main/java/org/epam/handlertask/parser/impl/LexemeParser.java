package org.epam.handlertask.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;
import org.epam.handlertask.composite.impl.TextComponent;
import org.epam.handlertask.composite.impl.TextLeaf;
import org.epam.handlertask.parser.ParserChain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements ParserChain {
    private final static Logger logger = LogManager.getLogger();
    private final static String WORD_REGEXP = "[a-zA-Z]+";
    private final static String PUNCTUATION = "[\\.,\\.+]";
    private final static String EXPRESSION_REGEXP="^([~0-9|&()<>^]+)$";

    private ParserChain nextChain;

    @Override
    public void setNext(ParserChain nextChain) {
        this.nextChain = nextChain;

    }


    @Override
    public void processData(String text, Component composite) {
        ComponentType componentType = composite.getTextType();
        if (componentType == ComponentType.LEXEME) {
            Pattern wordPattern = Pattern.compile(WORD_REGEXP);
            Matcher wordMatcher = wordPattern.matcher(text);
            while (wordMatcher.find()) {

                String word = text.substring(wordMatcher.start(), wordMatcher.end());

//              logger.info(word);
                TextComponent wordComposite = new TextComponent(ComponentType.WORD);
                composite.add(wordComposite);
                nextChain.processData(word, wordComposite);
            }
            Pattern expressionPattern = Pattern.compile(EXPRESSION_REGEXP);
            Matcher expressionMatcher = expressionPattern.matcher(text);
            while (expressionMatcher.find()) {
                String expression = text.substring(expressionMatcher.start(), expressionMatcher.end());
             //  logger.info(expression);
                TextComponent expressionComposite = new TextComponent(ComponentType.EXPRESSION);
                composite.add(expressionComposite);
                nextChain.processData(expression, expressionComposite);
            }

            char[] charArray = text.toCharArray();
            for (char character : charArray) {
                if (Pattern.matches(PUNCTUATION, String.valueOf(character))) {
//                   logger.info(character);
                    composite.add(new TextLeaf(character));
                }
            }


        } else {
            nextChain.processData(text, composite);
        }
    }
}
