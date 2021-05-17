package org.epam.handlertask.interpreter;

import org.epam.handlertask.converter.impl.InfixToPostfixConverterImpl;
import org.epam.handlertask.interpreter.impl.BinaryInterpreterImpl;
import org.testng.annotations.Test;

public class ParseExpressionTest {


    @Test
    public void testParseLexeme() {
        String[] expressions = {
                "1|2|~4<<2|6^45",
                "13 << 2",
                "2|5>>2&71",
                "(7^5|1&2<<(2|5>>2&71))|1200"
        };
        Integer[] results = {
                1|2|~4<<2|6^45,
                13 << 2,
                ~6 & 9 | (3 & 4),
                (~71 & (2 & 3 | (3 | (2 & 1 >> 2 | 2) ^ 2) | 10 & 2)) | 78,
                2|5>>2&71,
                (7 ^ 5 | 1 & 2 << (2 | 5 >> 2 & 71)) | 1200
        };
        InfixToPostfixConverterImpl converter = InfixToPostfixConverterImpl.getInstance();
        String postfix = converter.convertInfixToPostfix(expressions[0]);
        System.out.println(postfix);
        System.out.println(results[0]);

        BinaryInterpreter expressionInterpreter = new BinaryInterpreterImpl(postfix);
        Number v = expressionInterpreter.calculate();
        //    ExpressionCalculator expressionCalculator = new ExpressionCalculator();

        //  x = expressionCalculator.calculate(expressionInterpreter.getListExpression()).toString();
        System.out.println(v);
    }
}
