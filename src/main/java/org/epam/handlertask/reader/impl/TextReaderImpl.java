package org.epam.handlertask.reader.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.handlertask.reader.TextReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextReaderImpl implements TextReader {
    private static TextReaderImpl instance;
    private final static Logger logger = LogManager.getLogger(TextReaderImpl.class);

    private TextReaderImpl() {
    }

    public static TextReaderImpl getInstance() {
        if (instance == null) {
            instance = new TextReaderImpl();
        }
        return instance;
    }


    public String readFromFile(String pathToFile) {

        logger.info("In readFromFile() : File are reading...");
        StringBuilder builder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToFile))) {
            Stream<String> stream = bufferedReader.lines();
            stream.forEach(o -> builder.append(o).append("\n"));
//            stream.forEach(o->builder.append(o).append(" "));
        } catch (IOException e) {
            logger.error("Wrong filepath: " + pathToFile);
        }
        return builder.toString();
    }
}
