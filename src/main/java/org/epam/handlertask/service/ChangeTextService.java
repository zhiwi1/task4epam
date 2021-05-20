package org.epam.handlertask.service;

import org.epam.handlertask.composite.Component;
import org.epam.handlertask.exception.HandlerException;

import java.util.List;

public interface ChangeTextService {
    void deleteAllSentencesWithWordCountLessThen(Component text, int wordCount) throws HandlerException;

    List<Component> sortParagraphsBySentences(Component composite) throws HandlerException;
}


