package org.epam.handlertask.service;

import org.epam.handlertask.composite.Component;
import org.epam.handlertask.exception.HandlerException;

import java.util.HashMap;
import java.util.List;

public interface CalculateTextService {
    int countWordsInSentences(Component sentence) throws HandlerException;

    int calculateWordLength(Component word) throws HandlerException;

    HashMap<String, Integer> countSameWords(Component text) throws HandlerException;

    int getCountOfCurrentWord(List<Component> terms, String searchTerm) throws HandlerException;

    public int calculateVowelCount(Component component) throws HandlerException;
}
