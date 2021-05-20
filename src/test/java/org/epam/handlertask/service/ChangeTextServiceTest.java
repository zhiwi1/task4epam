package org.epam.handlertask.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;
import org.epam.handlertask.composite.impl.TextComponent;
import org.epam.handlertask.exception.HandlerException;
import org.epam.handlertask.parser.impl.*;
import org.epam.handlertask.reader.TextReader;
import org.epam.handlertask.reader.impl.TextReaderImpl;
import org.epam.handlertask.service.impl.ChangeTextServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChangeTextServiceTest {
    private final static Logger logger = LogManager.getLogger();
    String RELATIVE_PATH = "src/test/resources/input.txt";
    TextReader reader = TextReaderImpl.getInstance();
    Component component;

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
        component = new TextComponent(ComponentType.TEXT);
        textParser.processData(content, component);
    }

    @Test
    public void deleteAllSentencesWithWordCountLessThenTest() throws HandlerException {
        ChangeTextServiceImpl service = new ChangeTextServiceImpl();
        service.deleteAllSentencesWithWordCountLessThen(component, 31);
        String expected = "The point of using 78 Ipsum is that it has a more or less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. ";
        String actual=component.getChild(1).toString();
        Assert.assertEquals(actual,expected);
    }

}
