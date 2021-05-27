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
import org.epam.handlertask.service.impl.CalculateTextServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

public class CalculateTextServiceTest {
    private final String RELATIVE_PATH = "src/test/resources/input.txt";
    private final TextReader reader = TextReaderImpl.getInstance();
    private Component component;
    private CalculateTextServiceImpl service;

    @BeforeTest
    public void readAndParse() {
        service = new CalculateTextServiceImpl();
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
    public void deleteAllSentencesWithWordCountLessThenTest() throws HandlerException {
        HashMap<String, Integer> hashMap = service.countSameWords(component);
        String expected = "{here=2, fact=2, be=2, reader=2, when=2, content=3, lorem=2, that=3, of=6, has=2, established=2, readable=2, a=7, using=2, like=2, will=2, more=2, its=2, is=3, it=6, the=5, layout=2, with=2, at=2, ipsum=3, looking=2, page=2}";
        String actual = hashMap.toString();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void calculateVowelCount() throws HandlerException {
        int actualCount = service.calculateVowelCount(component.getChild(0).getChild(0));
        int expectedCount = 38;
        Assert.assertEquals(actualCount, expectedCount);
    }

    @Test
    public void calculateConsonantsCount() throws HandlerException {
        int actualCount = service.calculateConsonantsCount(component.getChild(0).getChild(0));
        int expectedCount = 62;
        Assert.assertEquals(actualCount, expectedCount);
    }

}

