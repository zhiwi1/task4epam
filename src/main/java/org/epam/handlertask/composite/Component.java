package org.epam.handlertask.composite;

public interface Component {

        void add(Component component);
        void remove(Component component);
        String toString();
        ComponentType getTextType();
        Object getChild(int index);

}
