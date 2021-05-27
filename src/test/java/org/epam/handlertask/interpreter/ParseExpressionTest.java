package org.epam.handlertask.interpreter;

import org.epam.handlertask.interpreter.impl.InfixToPostfixConverterImpl;
import org.epam.handlertask.interpreter.impl.BinaryInterpreterImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParseExpressionTest {


    @Test
    public void testParseLexeme() {
        String[] expressions = {
                "1|2|~4<<2|6^45",
                "13 << 2",
                "~6 & 9 | (3 & 4)",
                " (~71 & (2 & 3 | (3 | (2 & 1 >> 2 | 2) ^ 2) | 10 & 2)) | 78",
                "2|5>>2&71",
                "(7^5|1&2<<(2|5>>2&71))|1200"
        };
        int[] expectedResults = {
                1 | 2 | ~4 << 2 | 6 ^ 45,
                13 << 2,
                ~6 & 9 | (3 & 4),
                (~71 & (2 & 3 | (3 | (2 & 1 >> 2 | 2) ^ 2) | 10 & 2)) | 78,
                2 | 5 >> 2 & 71,
                (7 ^ 5 | 1 & 2 << (2 | 5 >> 2 & 71)) | 1200
        };
        InfixToPostfixConverter converter = InfixToPostfixConverterImpl.getInstance();
        int[] actualResults = new int[expectedResults.length];
        for (int i = 0; i < expectedResults.length; i++) {
            String postfix = converter.convertInfixToPostfix(expressions[i]);
            BinaryInterpreter expressionInterpreter = new BinaryInterpreterImpl(postfix);
            actualResults[i] = expressionInterpreter.calculate();
        }
        Assert.assertEquals(actualResults, expectedResults);

    }
}
