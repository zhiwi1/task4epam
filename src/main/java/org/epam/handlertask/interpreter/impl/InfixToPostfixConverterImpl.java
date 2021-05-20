package org.epam.handlertask.interpreter.impl;

import org.epam.handlertask.interpreter.InfixToPostfixConverter;

import java.util.ArrayDeque;
import java.util.Stack;

public class InfixToPostfixConverterImpl implements InfixToPostfixConverter {
    private final static char OPEN_BRACKET = '(';
    private final static char CLOSE_BRACKET = ')';
    private final static char LEFT_SHIFT = '<';
    private final static char RIGHT_SHIFT = '>';
    private final static char AND = '&';
    private final static char XOR = '^';
    private final static char OR = '|';
    private final static char UNARY_NOT = '~';

    private static InfixToPostfixConverterImpl instance;

    private InfixToPostfixConverterImpl() {
    }

    public static InfixToPostfixConverterImpl getInstance() {
        if (instance == null) {
            instance = new InfixToPostfixConverterImpl();
        }
        return instance;
    }

    @Override
    public String convertInfixToPostfix(String infix) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayDeque<Character> operator = new ArrayDeque<>();
        infix = replaceShifts(infix);
        char top;
        boolean flag = true;
        char previous = 1;
        for (int i = 0; i < infix.length(); i++) {
            char character = infix.charAt(i);
            if (i != 0) {
                previous = infix.charAt(i - 1);
            }
          /*  if (character==UNARY_NOT) {
            operator.push(UNARY_NOT);
              }*/
            if (!isOperator(character)) {
                if (!isOperator(previous)) {
                    stringBuilder.append(character);
                } else {
                    stringBuilder.append(" ").append(character);
                }

            } else if (character == CLOSE_BRACKET) {

                while ((top = operator.pop()) != OPEN_BRACKET) {
                    stringBuilder.append(" ").append(top).append(" ");
                }
            } else {

                while (!operator.isEmpty() && character != OPEN_BRACKET && precedence(operator.peek()) >= precedence(character)) {
                    char poppedChar = operator.pop();
                    stringBuilder.append(" ").append(poppedChar).append(" ");
                }
                operator.push(character);
            }

        }
        // pop any remaining operator
        while (!operator.isEmpty()) {
            stringBuilder.append(" ").append(operator.pop());
        }
        String postfixWithoutShifts = stringBuilder.toString();
        String postfix = addShiftsAndSpaces(postfixWithoutShifts);
        return postfix;
    }


    private boolean isOperator(char symbol) {
        return precedence(symbol) > 0;
    }

    private String replaceShifts(String expression) {
        String x = expression.replaceAll("<<", "<");
        return x.replaceAll(">>", ">");
    }

    private String addShiftsAndSpaces(String expression) {
        String x = expression.replaceAll("<", "<<");
        String y = x.replaceAll(" {2,}", " ");
        return y.replaceAll(">", ">>");
    }

    private int precedence(char symbol) {
        switch (symbol) {
            case OPEN_BRACKET:
            case CLOSE_BRACKET: {
                return 1;
            }
            case OR: {
                return 2;
            }
            case XOR: {
                return 3;
            }
            case AND: {
                return 4;
            }
            case RIGHT_SHIFT: {
                return 5;
            }
            case LEFT_SHIFT: {
                return 6;
            }
            case UNARY_NOT:
                return 7;
            default: {
                return 0;
            }
        }
    }
    /*
switch(symbol) {
		case NEW_LEFT_SHIFT:
			return 6;
		case NEW_RIGHT_SHIFT:
			return 5;
		case NEW_UNSIGNED_RIGHT_SHIFT:
			return 4;
		case AND:
			return 3;
		case XOR:
			return 2;
		case OR:
			return 1;
		default:
			return -1;
		}
*/
}
