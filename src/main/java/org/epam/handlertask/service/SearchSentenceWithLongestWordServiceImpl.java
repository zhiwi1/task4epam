package org.epam.handlertask.service;

import org.epam.handlertask.composite.Component;

import java.util.ArrayList;
import java.util.List;

public class SearchSentenceWithLongestWordServiceImpl {

    public List<Integer> findSentencesWithLongestWord(Component textComposite) {
        List<Integer> sentencesNumbers = new ArrayList<>();
        int currentSentence = 0;
        int maxWord = 0;
       // TextCalculationService calculationService = new TextCalculationServiceImpl();
        for (Component component: textComposite.) {
            ComponentName componentName = component.getComponentName();
            if (componentName == ComponentName.SENTENCE) {
                currentSentence++;
            }
            if (componentName == ComponentName.WORD) {
                int currentLength = calculationService.calculateWordLength(component);
                if (currentLength > maxWord) {
                    maxWord = currentLength;
                    sentencesNumbers = new ArrayList<>();
                    sentencesNumbers.add(currentSentence);
                } else if (currentLength == maxWord) {
                    sentencesNumbers.add(currentSentence);
                }
            }
        }
        return sentencesNumbers;
    }
}
