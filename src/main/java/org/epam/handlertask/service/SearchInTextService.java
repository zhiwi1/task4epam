package org.epam.handlertask.service;

import org.epam.handlertask.composite.Component;
import org.epam.handlertask.exception.HandlerException;

import java.util.List;

public interface SearchInTextService {
    List<Component> findAllWords(Component textComposite) throws HandlerException;
    List<Component> findAllWordsInSentence(Component sentence) throws HandlerException;
    List<Component> findAllSentences(Component text) throws HandlerException;
    List<Component> findAllSentencesWithTheLongestWord(Component text) throws HandlerException;
}
