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
        final String DOT_WITH_SPACE = " \\.";
        final String DOT = "\\.";
        final String COMMA_WITH_SPACE = " \\,";
        final String COMMA = "\\,";
        final String EXCLAMATION_POINT_WITH_SPACE = " !";
        final String EXCLAMATION_POINT = "!";
        final String QUESTION_MARK_WITH_SPACE = " \\?";
        final String QUESTION_MARK = "?";
        final String LINE_TRANSLATION = "\n";
        final String NOTHING = "";
        switch (componentType) {

            case TEXT: {
                for (Component component : components) {
                    stringBuilder.append("\n    ").append(component.toString());
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
                    stringBuilder.append(composite.toString()).append(" ");
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
        String result = text.replaceAll(DOT_WITH_SPACE, DOT).replaceAll(COMMA_WITH_SPACE, COMMA).
                replaceAll(EXCLAMATION_POINT_WITH_SPACE, EXCLAMATION_POINT).replaceAll(QUESTION_MARK_WITH_SPACE, QUESTION_MARK);

        return result.replaceFirst(LINE_TRANSLATION, NOTHING);
    }
}
