package org.epam.handlertask.composite.impl;

import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements Component {
    private List<Component> components;
    private ComponentType componentType;

    public TextComposite(ComponentType componentType) {
        this.componentType = componentType;
        components = new ArrayList<>();
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public int getComponentsSize() {
        return components.size();
    }


    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public ComponentType getTextType() {
        return componentType;
    }

    @Override
    public Component getChild(int index) {
        return components.get(index);
    }

    @Override
    public String toString() {
        final var stringBuilder = new StringBuilder();
        switch (componentType) {

            case TEXT: {
                for (Component component : components) {
                    stringBuilder.append(ToStringExpression.LINE_TRANSLATION_WITH_SPACES).append(component.toString());
                }
                break;
            }

            case PARAGRAPH:
                for (Component composite : components) {
                    stringBuilder.append(composite.toString());
                }
                break;


            case LEXEME: {
                for (Component composite : components) {
                    stringBuilder.append(composite.toString()).append(ToStringExpression.SPACE);
                }
                break;
            }
            case SENTENCE:
            case WORD:
            case EXPRESSION:
            case SYMBOL: {
                for (Component composite : components) {
                    stringBuilder.append(composite.toString());
                }
                break;
            }
        }
        String text = stringBuilder.toString();
        return ToStringExpression.replaceAllForToString(text);
    }

    private static class ToStringExpression {
        final static String SPACE = " ";
        final static String LINE_TRANSLATION_WITH_SPACES = "\n    ";
        final static String DOT_WITH_SPACE = " \\.";
        final static String DOT = "\\.";
        final static String COMMA_WITH_SPACE = " \\,";
        final static String COMMA = "\\,";
        final static String EXCLAMATION_POINT_WITH_SPACE = " !";
        final static String EXCLAMATION_POINT = "!";
        final static String QUESTION_MARK_WITH_SPACE = " \\?";
        final static String QUESTION_MARK = "?";
        final static String LINE_TRANSLATION = "\n";
        final static String NOTHING = "";

        static String replaceAllForToString(String text) {
            String result = text.replaceAll(DOT_WITH_SPACE, DOT).replaceAll(COMMA_WITH_SPACE, COMMA).
                    replaceAll(EXCLAMATION_POINT_WITH_SPACE, EXCLAMATION_POINT).replaceAll(QUESTION_MARK_WITH_SPACE, QUESTION_MARK);

            return result.replaceFirst(LINE_TRANSLATION, NOTHING);
        }
    }
}
