package org.epam.handlertask.composite.impl;

import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;

import java.util.ArrayList;
import java.util.List;

public class TextComponent implements Component {
    private List<Component> components;
    private ComponentType componentType;

    public TextComponent(ComponentType componentType) {
        this.componentType = componentType;
        components = new ArrayList<>();
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
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
    public Object getChild(int index) {
        return components.get(index);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

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
        String result = text.replaceAll(" \\.", "\\.").replaceAll(" ,", ",").
                replaceAll(" !", "!").replaceAll(" \\?", "?");

        return result.replaceFirst("\n", "");
    }
           /* default:
                for (Component composite : components) {
                    stringBuilder.append(composite.toString());
                }*/
             /*   break;
        }*/
         /*   return stringBuilder.toString().trim();
        }*/
}
