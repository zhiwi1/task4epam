package org.epam.handlertask.interpreter.impl;

import org.epam.handlertask.interpreter.Expression;
import org.epam.handlertask.interpreter.context.Context;

public class NumberNonTerminalExpression implements Expression {
    private int number;

    public NumberNonTerminalExpression(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context c) {
        c.pushValue(number);
    }
}
