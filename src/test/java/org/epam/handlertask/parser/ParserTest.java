package org.epam.handlertask.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;
import org.epam.handlertask.composite.impl.Composite;
import org.epam.handlertask.exception.HandlerException;
import org.epam.handlertask.parser.impl.*;
import org.epam.handlertask.reader.TextReader;
import org.epam.handlertask.reader.impl.TextReaderImpl;
import org.testng.annotations.Test;

public class ParserTest {
    private final static Logger logger = LogManager.getLogger();

    @Test
    public void parseTest() throws HandlerException {
        TextParser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();
        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(wordParser);
        TextReader reader = TextReaderImpl.getInstance();
        String content = reader.readFromFile("src/test/resources/input.txt");
//       logger.info(line);
        Component rootComponent = new Composite(ComponentType.TEXT);
        textParser.processData(content, rootComponent);
     System.out.print(rootComponent);

    }
}

////public class ParserTest {public void parseTest() throws URISyntaxException, TextException {
//    TextParser textParser = new TextParser();
//    ParagraphParser paragraphParser = new ParagraphParser();
//    SentenceParser sentenceParser = new SentenceParser();
//    LexemeParser lexemeParser=new LexemeParser();
////
//
//    textParser.setNextChain(paragraphParser);
//    paragraphParser.setNextChain(sentenceParser);
//    sentenceParser.setNextChain(elementParser);
//    elementParser.setNextChain(wordParser);
//    wordParser.setNextChain(wordWitchCharactersOutsideParser);
//    wordWitchCharactersOutsideParser.setNextChain(expressionParser);
//
//    URI uri = getClass().getResource("/data/data.txt").toURI();
//    String absolutePath = new File(uri).getAbsolutePath();
//    TextReader reader = new TextReaderImpl();
//    List<String> content = reader.readAllLines(absolutePath);
//    String stringContent = content.stream().map(Object::toString).collect(Collectors.joining(""));
//
//    Component rootComponent = new TextComposite(ComponentName.TEXT);
//    textParser.parse(rootComponent, stringContent);
//    System.out.println(rootComponent);
//}
//
//}
