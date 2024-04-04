package com.example.assignment_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GameRunner {
    private final ArrayList<String> validWords;
    private final State gameState;

    public GameRunner() {

        validWords = loadValidWords();
        gameState = new State(new ArrayList<>(validWords)); // Deep copy

        if (validWords.isEmpty()) {
            System.err.println("No valid words loaded. Check file.");
        }
    }

    // Separate method for loading valid words
    private ArrayList<String> loadValidWords() {
        ArrayList<String> loadedWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("target/sgb-words.txt"))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                loadedWords.add(currentLine.strip());
            }
        } catch (IOException e) {
            System.err.println("Error loading valid words: " + e.getMessage());
        }
        return loadedWords;
    }

    public Guess processInput(String newWord)
    {
        Guess newestGuess;
        if((newWord == null)) {
            newestGuess = new Guess();

            return newestGuess;
        }else {
            newestGuess = gameState.makeGuess(newWord.toLowerCase());
            return newestGuess;
        }
    }

    public boolean wasGoodGuess()
    {
        return gameState.getLastResult() != GuessResult.INVALID;
    }


}

