package org.epam.handlertask.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;
import org.epam.handlertask.composite.impl.TextComposite;
import org.epam.handlertask.exception.HandlerException;
import org.epam.handlertask.parser.impl.*;
import org.epam.handlertask.reader.TextReader;
import org.epam.handlertask.reader.impl.TextReaderImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ParserTest {
    private final static Logger logger = LogManager.getLogger();
    private final String RELATIVE_PATH = "src/test/resources/input.txt";
    private final TextReader reader = TextReaderImpl.getInstance();
    private Component component;

    @BeforeTest
    public void readAndParse() {
        TextParser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();
        ExpressionParser expressionParser = new ExpressionParser();
        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(wordParser);
        wordParser.setNext(expressionParser);
        String content = reader.readFromFile(RELATIVE_PATH);
        component = new TextComposite(ComponentType.TEXT);
        textParser.processData(content, component);
    }

    @Test
    public void parseTest() throws HandlerException {

        String expected = "    It has survived not only five centuries, but also the leap into 52 electronic typesetting, remaining 0 essentially 9 unchanged. It was popularised in the 5 with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum... \n" +
                "    It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using 78 Ipsum is that it has a more or less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. \n" +
                "    It is a 1202 established fact that a reader will be of a page when looking at its layout. \n" +
                "    Bye. ";
        String actual = component.toString();
        Assert.assertEquals(actual, expected);
    }
}
