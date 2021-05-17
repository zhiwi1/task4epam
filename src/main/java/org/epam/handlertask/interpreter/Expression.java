package org.epam.handlertask.interpreter;

import org.epam.handlertask.interpreter.context.Context;

@FunctionalInterface
public interface Expression {
    void interpret(Context context);
}