package me.aandster.seng2050a2.persistence;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class SecretWordService {

    private final String filePath;

    public SecretWordService(String filePath) {
        if (filePath == null) throw new NullPointerException("No path provided.");
        this.filePath = filePath;
    }

    public synchronized String getSecretWord() throws IOException {
        List<String> secretWords = Files.readAllLines(Path.of(filePath));
        int randomNumber = (new Random()).nextInt(secretWords.size());
        return secretWords.get(randomNumber).toUpperCase();
    }
}
