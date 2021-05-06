package org.epam.handlertask.composite.impl;

import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
    private List<Component> components;
    private ComponentType componentType;

    public Composite(ComponentType componentType) {
        this.componentType = componentType;
        components = new ArrayList<>();
    }


    @Override
    public void operation() {
        int size = components.size();
        for (Component component : components) {
            component.operation();
        }
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
        StringBuilder stringBuilder=new StringBuilder();
        switch (componentType) {
            case PARAGRAPH:
                for (Component composite : components) {
                    stringBuilder.append(composite.toString()).append("\n");
                }
                break;
            case SENTENCE:
                for (Component composite : components) {
                    stringBuilder.append(composite.toString()).append(" ");
                }
                break;

            default:
                for (Component composite : components) {
                    stringBuilder.append(composite.toString());
                }
                break;
        }
        return stringBuilder.toString().trim();
    }
}
