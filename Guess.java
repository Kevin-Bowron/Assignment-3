package com.example.assignment_3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Guess {
    private String secretWord;
    private int[] letterStatus;
    private final String DEFAULT_WORD = "START";

    public enum LetterStates
    {
        NO_MATCH,
        IN_WORD,
        EXACT_MATCH;
    }

    public Guess()
    {
        secretWord = new String(DEFAULT_WORD);
        letterStatus = new int[secretWord.length()];
        for (int i = 0; i < secretWord.length();i++)
        {
            letterStatus[i] = LetterStates.NO_MATCH.ordinal();
        }

    }

    public Guess(String newGuess, String secretWord)
    {
        this.secretWord = secretWord;
        letterStatus = new int[secretWord.length()];
        // Hash map to avoid usedCharacters effecting results
        Set<Character> usedCharacters = new HashSet<>();
        for (int i = 0; i < secretWord.length(); i++) {
            char secretChar = secretWord.charAt(i);
            char guessChar = newGuess.charAt(i);
            // if Exact match
            if (secretChar == guessChar) {
                letterStatus[i] = LetterStates.EXACT_MATCH.ordinal();
                usedCharacters.add(newGuess.charAt(i));
            }else{
                // if character is in word and is not a used character
                if (!usedCharacters.contains(guessChar) && secretWord.contains(String.valueOf(newGuess.charAt(i))) ) {
                    letterStatus[i] = LetterStates.IN_WORD.ordinal();
                    usedCharacters.add(newGuess.charAt(i));


                }
                // If character is not found in word
                else{
                    letterStatus[i] = LetterStates.NO_MATCH.ordinal();
                }
                }

            } usedCharacters.clear(); // Reset for next guess

        System.out.println(secretWord);
        }



    // Copy constructor
    public Guess(Guess otherGuess) {
        this.secretWord = otherGuess.secretWord;
        this.letterStatus = new int[otherGuess.letterStatus.length];
        System.arraycopy(otherGuess.letterStatus, 0, this.letterStatus, 0, otherGuess.letterStatus.length);
    }

    public boolean hasWin()
    {
        boolean winState = true;
        for (int i = 0; i < secretWord.length(); i++)
        {
            if (letterStatus[i] != LetterStates.EXACT_MATCH.ordinal())
                winState = false;
        }
        return winState;
    }

    public int[] getLetterStatus() {
        return letterStatus;
    }


    @Override
    public String toString() {
        return "State{" +
                "secretWord='" + secretWord + '\'' +
                ", letterStatus=" + Arrays.toString(letterStatus) +
                '}';
    }
}

