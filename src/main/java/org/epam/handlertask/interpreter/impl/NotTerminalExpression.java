package org.epam.handlertask.interpreter.impl;

import org.epam.handlertask.interpreter.Expression;
import org.epam.handlertask.interpreter.context.Context;

public class NotTerminalExpression implements Expression {
    @Override
    public void interpret(Context c) {
        c.pushValue(~c.popValue());
    }
}
