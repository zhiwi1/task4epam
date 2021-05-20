package org.epam.handlertask.service.impl;

import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;
import org.epam.handlertask.exception.HandlerException;
import org.epam.handlertask.service.CalculateTextService;
import org.epam.handlertask.service.SearchInTextService;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class CalculateTextServiceImpl implements CalculateTextService {
    private final static String CONSONANTS = "bcdfghjklmnpqrstvwxyz";
    private final static String VOWELS = "aeiou";

    @Override
    public int countWordsInSentences(Component sentence) throws HandlerException {
        ComponentType componentType = sentence.getTextType();
        if (componentType == ComponentType.SENTENCE) {
            int countOfWords = 0;
            for (int i = 0; i < sentence.getComponentsSize(); i++) {
                Component lexeme = sentence.getChild(i);
                for (int j = 0; j < lexeme.getComponentsSize(); j++) {
                    Component element = lexeme.getChild(j);
                    if (element.getTextType() == ComponentType.WORD)
                        ++countOfWords;
                }
            }

            return countOfWords;
        } else {
            throw new HandlerException("Composite type: " + componentType + " is not paragraph");
        }
    }

    @Override
    public int calculateWordLength(Component word) throws HandlerException {
        ComponentType componentType = word.getTextType();
        if (componentType == ComponentType.WORD) {
            int wordLength = word.getComponentsSize();
            return wordLength;
        } else {
            throw new HandlerException("Composite type: " + componentType + " is not paragraph");
        }
    }

    @Override
    public HashMap<String, Integer> countSameWords(Component text) throws HandlerException {
        SearchInTextService service = new SearchInTextServiceImpl();
        List<Component> words = service.findAllWords(text);
        HashMap<String, Integer> map = new HashMap<>();
        for (Component word : words) {
            int count = getCountOfCurrentWord(words, word.toString().toLowerCase());
            if (count > 1) {
                map.put(word.toString().toLowerCase(), count);
            }
        }
        return map;
    }

    @Override
    public int getCountOfCurrentWord(List<Component> terms, String searchTerm) throws HandlerException {
        if (terms != null) {
            int count = terms.stream()
                    .mapToInt(word -> Objects.equals(searchTerm.toLowerCase(), word.toString().toLowerCase()) ? 1 : 0)
                    .sum();
            return count;
        } else {
            throw new HandlerException("List of components is null");
        }
    }
@Override
public int calculateVowelCount(Component component) throws HandlerException {
        int countOfVowels = 0;
        SearchInTextService service = new SearchInTextServiceImpl();
        List<Component> words = service.findAllWordsInSentence(component);
        for (Component word : words) {
            for (Component symbol : word.getComponents()) {
                String stringSymbol = symbol.toString();
                if (VOWELS.contains(stringSymbol)) {
                    countOfVowels++;
                }
            }
        }
        return countOfVowels;
    }

    public int calculateConsonantsCount(Component component) throws HandlerException {

        int countOfVowels = 0;
        SearchInTextService service = new SearchInTextServiceImpl();
        List<Component> words = service.findAllWordsInSentence(component);
        for (Component word : words) {
            for (Component symbol : word.getComponents()) {
                String stringSymbol = symbol.toString();
                if (CONSONANTS.contains(stringSymbol)) {
                    countOfVowels++;
                }
            }
        }
        return countOfVowels;
    }
}
  /* @Override
    public int calculateConsonantCount(AbstractComponent component) {
        TextSearchService searchService = new TextSearchServiceImpl();
        List<AbstractComponent> symbols = searchService.findAllSymbols(component);
        int consonantCount = 0;
        for (AbstractComponent symbolComponent : symbols) {
            LeafElement leafElement = (LeafElement) symbolComponent;
            String stringToSearch = Character.toString(leafElement.getContent()).toLowerCase();
            if (CONSONANTS.contains(stringToSearch)) {
                consonantCount++;
            }
        }
        return consonantCount;
    }*/