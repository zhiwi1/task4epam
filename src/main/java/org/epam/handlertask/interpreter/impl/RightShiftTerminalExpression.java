package org.epam.handlertask.interpreter.impl;

import org.epam.handlertask.interpreter.Expression;
import org.epam.handlertask.interpreter.context.Context;

public class RightShiftTerminalExpression implements Expression {
    @Override
    public void interpret(Context c) {
        int a=c.popValue();
        int b=c.popValue();
        c.pushValue(b >> a);
    }
}
