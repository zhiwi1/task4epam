package org.epam.handlertask.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;
import org.epam.handlertask.composite.impl.TextComposite;
import org.epam.handlertask.exception.HandlerException;
import org.epam.handlertask.parser.impl.*;
import org.epam.handlertask.reader.TextReader;
import org.epam.handlertask.reader.impl.TextReaderImpl;
import org.epam.handlertask.service.impl.SearchInTextServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class SearchInTextServiceTest {
    private final static Logger logger= LogManager.getLogger();
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
        component = new TextComposite(ComponentType.TEXT);
        textParser.processData(content, component);
    }

    @Test
    public void findAllSentencesWithTheLongestWordTest ()throws HandlerException {
        SearchInTextServiceImpl service = new SearchInTextServiceImpl();
       List<Component> sentencesWithTheLongestWord= service.findAllSentencesWithTheLongestWord(component);
       String expected="The point of using 78 Ipsum is that it has a more or less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. ";
       String actual=sentencesWithTheLongestWord.get(0).toString();
        Assert.assertEquals(actual,expected);
    }
}
