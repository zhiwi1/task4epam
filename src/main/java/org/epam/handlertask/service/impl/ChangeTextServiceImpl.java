package org.epam.handlertask.service.impl;

import org.epam.handlertask.comparator.CompositeComparator;
import org.epam.handlertask.comparator.impl.SortSpecificationParagraphsByCountSentences;
import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;
import org.epam.handlertask.exception.HandlerException;
import org.epam.handlertask.service.CalculateTextService;
import org.epam.handlertask.service.ChangeTextService;


import java.util.List;

public class ChangeTextServiceImpl implements ChangeTextService {
    @Override
    public void deleteAllSentencesWithWordCountLessThen(Component text, int wordCount) throws HandlerException {
        CalculateTextService calculateTextService = new CalculateTextServiceImpl();
        for (int i = 0; i < text.getComponentsSize(); i++) {
            Component paragraph = text.getChild(i);
            for (int j = paragraph.getComponentsSize() - 1; j >= 0; j--) {
                Component sentence = paragraph.getChild(j);
                int countOfWords = calculateTextService.countWordsInSentences(sentence);
                if (countOfWords < wordCount) {
                    paragraph.remove(sentence);
                }
            }
        }
    }

    @Override
    public List<Component> sortParagraphsBySentences(Component composite) throws HandlerException {
        ComponentType elementType = composite.getTextType();
        if (elementType == ComponentType.TEXT) {
            List<Component> paragraphs = composite.getComponents();
            CompositeComparator comparator = new SortSpecificationParagraphsByCountSentences();
            paragraphs.sort(comparator);
            return paragraphs;
        } else {
            throw new HandlerException("Composite type: " + elementType + " is not paragraph");
        }
    }
}

