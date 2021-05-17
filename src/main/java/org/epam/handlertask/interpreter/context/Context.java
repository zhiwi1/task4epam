package org.epam.handlertask.interpreter.context;

import java.util.ArrayDeque;

public class Context {
    private  ArrayDeque<Integer> contextValues = new ArrayDeque<>();

    public Integer popValue() {
        return this.contextValues.pop();
    }

    public void pushValue(Integer value) {
        this.contextValues.push(value);
    }
}

