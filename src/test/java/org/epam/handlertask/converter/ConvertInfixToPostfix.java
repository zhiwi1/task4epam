package org.epam.handlertask.converter;

import org.epam.handlertask.interpreter.InfixToPostfixConverter;
import org.epam.handlertask.interpreter.impl.InfixToPostfixConverterImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ConvertInfixToPostfix {
    @Test
    public void convertTest() {
        String exp="1>>(2&3)";
        InfixToPostfixConverter convertInfixToPostfix= InfixToPostfixConverterImpl.getInstance();
        String actual=convertInfixToPostfix.convertInfixToPostfix(exp);
        String expected="1 2 3 & >>";
        Assert.assertEquals(actual,expected);
    }
}
