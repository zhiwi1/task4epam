package org.epam.handlertask.service.impl;

import org.epam.handlertask.composite.Component;
import org.epam.handlertask.composite.ComponentType;
import org.epam.handlertask.exception.HandlerException;
import org.epam.handlertask.service.CalculateTextService;
import org.epam.handlertask.service.SearchInTextService;


import java.util.ArrayList;
import java.util.List;

public class SearchInTextServiceImpl implements SearchInTextService {
    @Override
    public List<Component> findAllWords(Component textComposite) throws HandlerException {
        List<Component> sentences = findAllSentences(textComposite);
        List<Component> words = new ArrayList<>();
        for (Component sentence : sentences) {
            words.addAll(findAllWordsInSentence(sentence));
        }
        return words;
    }

    @Override
    public List<Component> findAllWordsInSentence(Component sentence) throws HandlerException {
        ComponentType elementType = sentence.getTextType();
        if (elementType == ComponentType.SENTENCE) {
            List<Component> words = new ArrayList<>();
            for (int j = 0; j < sentence.getComponentsSize(); j++) {
                Component lexeme = sentence.getChild(j);
                for (int k = 0; k < lexeme.getComponentsSize(); k++) {
                    Component word = lexeme.getChild(k);
                    if (word.getTextType() == ComponentType.WORD) {
                        words.add(word);
                    }
                }
            }
            return words;
        } else {
            throw new HandlerException("Composite type: " + elementType + " is not sentence");
        }

    }

    @Override
    public List<Component> findAllSentences(Component text) throws HandlerException {
        ComponentType elementType = text.getTextType();
        List<Component> sentences = new ArrayList<>();
        if (elementType == ComponentType.TEXT) {
            for (int i = 0; i < text.getComponentsSize(); i++) {
                Component paragraph = text.getChild(i);
                for (int j = 0; j < paragraph.getComponentsSize(); j++) {
                    Component sentence = paragraph.getChild(j);
                    sentences.add(sentence);
                }
            }
            return sentences;
        } else {
            throw new HandlerException("Composite type: " + elementType + " is not paragraph");
        }
    }

    @Override
    public List<Component> findAllSentencesWithTheLongestWord(Component text) throws HandlerException {
        List<Component> sentences = findAllSentences(text);
        int countOfLengthOfWord = 0;
        CalculateTextService service = new CalculateTextServiceImpl();
        List<Component> sentencesWithTheLongestWord = new ArrayList<>();
        for (Component sentence : sentences) {
            List<Component> wordsInSentence = findAllWordsInSentence(sentence);
            for (int j = 0; j < wordsInSentence.size(); j++) {
                Component word = wordsInSentence.get(j);
                int currentLength = service.calculateWordLength(word);
                if (currentLength > countOfLengthOfWord) {
                    countOfLengthOfWord = currentLength;
                    sentencesWithTheLongestWord = new ArrayList<>();
                    sentencesWithTheLongestWord.add(sentence);
                } else if (currentLength == countOfLengthOfWord) {
                    sentencesWithTheLongestWord.add(sentence);
                }
            }
        }
        return sentencesWithTheLongestWord;
    }
//Deprecated
  /*  public List<Component> findAllSentencesWithTheLongestWord(Component text) throws HandlerException {
        ComponentType elementType = text.getTextType();
        if (elementType == ComponentType.TEXT) {
            int countOfLengthOfWord = 0;
            CalculateTextServiceImpl service = new CalculateTextServiceImpl();
            List<Component> sentences = findAllSentences(text);
            List<Component> sentencesWithTheLongestWord = new ArrayList<>();
            for (Component sentence : sentences) {
                for (int j = 0; j < sentence.getComponentsSize(); j++) {
                    Component lexeme = sentence.getChild(j);
                    for (int k = 0; k < lexeme.getComponentsSize(); k++) {
                        Component word = lexeme.getChild(k);
                        if (word.getTextType() == ComponentType.WORD) {
                            int currentLength = service.calculateWordLength(word);
                            if (currentLength > countOfLengthOfWord) {
                                countOfLengthOfWord = currentLength;
                                sentencesWithTheLongestWord = new ArrayList<>();
                                sentencesWithTheLongestWord.add(sentence);
                            } else if (currentLength == countOfLengthOfWord) {
                                sentencesWithTheLongestWord.add(sentence);
                            }
                        }
                    }
                }
            }
            return sentencesWithTheLongestWord;
        } else {
            throw new HandlerException("Composite type: " + elementType + " is not paragraph");
        }
    }*/
}