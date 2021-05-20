package org.epam.handlertask.converter;

import org.epam.handlertask.interpreter.impl.InfixToPostfixConverterImpl;
import org.testng.annotations.Test;

public class ConvertInfixToPostfix {
    @Test
    public void convertTest() {
        String exp="1>>(2&3)";
        InfixToPostfixConverterImpl convertInfixToPostfix= InfixToPostfixConverterImpl.getInstance();
        String x=convertInfixToPostfix.convertInfixToPostfix(exp);
        System.out.println(x);
    }
}
