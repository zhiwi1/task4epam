package org.epam.handlertask.service;

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

import java.util.List;

public class SortingParagraphsServiceTest {
    String RELATIVE_PATH="src/test/resources/input.txt";
    String RELATIVE_PATH_SORTED="src/test/resources/sortedParagraphs.txt";
    TextReader reader = TextReaderImpl.getInstance();
    Component component;
    Component sortedComponent;


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
        String sortedContent=reader.readFromFile(RELATIVE_PATH_SORTED);
        sortedComponent = new TextComponent(ComponentType.TEXT);
        component = new TextComponent(ComponentType.TEXT);
        textParser.processData(sortedContent,sortedComponent);
        textParser.processData(content, component);
    }
    @Test
    public void sortingParagraphsTest() throws HandlerException {
        ChangeTextServiceImpl service=new ChangeTextServiceImpl();
        List<Component> actualParagraphs =service.sortParagraphsBySentences(component);
        List<Component> expectedParagraphs=sortedComponent.getComponents();
        Assert.assertEquals(actualParagraphs.toString(),expectedParagraphs.toString());
    }
}
