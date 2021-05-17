package org.epam.handlertask.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;
import org.epam.handlertask.composite.impl.TextComponent;
import org.epam.handlertask.composite.impl.TextLeaf;
import org.epam.handlertask.converter.InfixToPostfixConverter;
import org.epam.handlertask.converter.impl.InfixToPostfixConverterImpl;
import org.epam.handlertask.exception.HandlerException;
import org.epam.handlertask.interpreter.BinaryInterpreter;
import org.epam.handlertask.interpreter.impl.BinaryInterpreterImpl;
import org.epam.handlertask.parser.ParserChain;

import java.util.regex.Pattern;

public class ExpressionParser implements ParserChain {
    private final static Logger logger = LogManager.getLogger();

    private final static String EXPRESSION = "^([~0-9|&()<>^]+)$";


    @Override
    public void setNext(ParserChain nextChain) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void processData(String text, Component composite) {
        ComponentType componentName = composite.getTextType();
        if (componentName == ComponentType.EXPRESSION) {
            if (Pattern.matches(EXPRESSION, text)) {
                InfixToPostfixConverter converter = InfixToPostfixConverterImpl.getInstance();
                String postfixNotation = converter.convertInfixToPostfix(text);
                BinaryInterpreter interpreter = new BinaryInterpreterImpl(postfixNotation);
                int result = interpreter.calculate();
                logger.info(result);

                String resultString = String.valueOf(result);
                for (int i = 0; i < resultString.length(); i++) {
                  //  logger.info(resultString.charAt(i));
                    Component numberComposite = new TextLeaf(resultString.charAt(i));
                    composite.add(numberComposite);
                }

            }
        }
    }
}

