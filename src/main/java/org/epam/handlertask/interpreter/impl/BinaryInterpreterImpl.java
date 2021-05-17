package org.epam.handlertask.interpreter.impl;

import org.epam.handlertask.interpreter.BinaryInterpreter;
import org.epam.handlertask.interpreter.Expression;
import org.epam.handlertask.interpreter.context.Context;

import java.util.ArrayList;
import java.util.Scanner;

public class BinaryInterpreterImpl implements BinaryInterpreter {
    private ArrayList<Expression> listExpression;
    private String BLANK = "\\p{Blank}+";

    public BinaryInterpreterImpl(String expression) {
        this.listExpression = new ArrayList<>();
        parse(expression);
    }

    public ArrayList<Expression> getListExpression() {
        return listExpression;
    }

    public void setListExpression(ArrayList<Expression> listExpression) {
        this.listExpression = listExpression;
    }



    public void parse(String expression) {
        String[] lexemeExpressions=expression.split(BLANK);

        for (int i=0;i<lexemeExpressions.length;i++) {
            if (lexemeExpressions[i].isEmpty()) {
                continue;
            }
            char temp = lexemeExpressions[i].charAt(0);
            switch (temp) {
                case '~':
                    listExpression.add(new NotTerminalExpression());
                    break;
                case '|':
                    listExpression.add(new OrTerminalExpression());
                    break;
                case '^':
                    listExpression.add(new XorTerminalExpression());
                    break;
                case '&':
                    listExpression.add(new AndTerminalExpression());
                    break;
                case '<':
                    listExpression.add(new LeftShiftTerminalExpression());

                    break;
                case '>':
                    listExpression.add(new RightShiftTerminalExpression());

                    break;
                default:
                    Scanner scan = new Scanner(lexemeExpressions[i]);
                    if (scan.hasNextInt()) {
                        listExpression.add(new NumberNonTerminalExpression(scan.nextInt()));
                    }
            }
        }
    }
    @Override
    public int calculate() {
        Context context = new Context();
        for (Expression expression: listExpression) {
            expression.interpret(context);
        }
        return context.popValue();
    }
}