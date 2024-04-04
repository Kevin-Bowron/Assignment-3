package com.example.assignment_3;


import java.util.ArrayList;
import java.util.Random;


public class State {
    ArrayList <Guess> allGuesses;
    ArrayList <String> gameDictionary;
    String secretWord;
    GuessResult lastResult;


    public State()
    {
        allGuesses = new ArrayList<Guess>();
        gameDictionary = new ArrayList<String>();
        secretWord = "NULLS";
        lastResult = GuessResult.NONE;
    }

    public State(ArrayList<String> validWords)
    {
        allGuesses = new ArrayList<Guess>();
        gameDictionary = validWords;
        Random rng = new Random();
        secretWord = (String)gameDictionary.get(rng.nextInt(gameDictionary.size() - 1));

        lastResult = GuessResult.NONE;
    }



    public int getSecretWordLength() {
        return secretWord.length();
    }
    public boolean isWordInDictionary(String word) {
        return gameDictionary.contains(word);
    }


    public Guess makeGuess(String newWord)
    {
        Guess newGuess;
        if (this.gameDictionary.contains(newWord))
        {
            newGuess = new Guess(newWord, this.secretWord);
            this.lastResult = GuessResult.VALID;
            if (newGuess.hasWin())
            {
                this.lastResult = GuessResult.MATCH;
            }
        }
        else if (newWord.length() >= this.secretWord.length())
        {
            newGuess = new Guess(newWord.substring(0,this.secretWord.length()), this.secretWord);
        }
        else
        {
            String paddedWord = String.format("%1$5s",newWord);
            newGuess = new Guess(paddedWord, this.secretWord);
            this.lastResult = GuessResult.INVALID;
        }
        if (newWord.length() != this.secretWord.length()){
            this.lastResult = GuessResult.INVALID;
        }


        allGuesses.add(newGuess);

        return newGuess;
    }

    public boolean checkWin(int maxTurns)
    {
        boolean hasWin = false;

        if ((allGuesses.size() - 1 <= maxTurns) && (this.lastResult == GuessResult.MATCH))
        {
            hasWin = true;
        }

        return true;
    }



    public GuessResult getLastResult() {
        return this.lastResult;
    }
}
