package org.epam.handlertask.composite;

import java.util.List;

public interface Component {

        void add(Component component);
        void remove(Component component);
        List<Component> getComponents();
        void setComponents(List<Component> components);
        int getComponentsSize();
        String toString();
        ComponentType getTextType();
        Component getChild(int index);

}
