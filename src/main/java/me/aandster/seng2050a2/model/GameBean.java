package me.aandster.seng2050a2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameBean implements Serializable {

    private final String UID = UUID.randomUUID().toString();
    private String username = null;
    private List<Character> maskedWord = null;
    private Character nextGuess = null;
    private int roundNumber = -1;
    private List<Character> recentGuesses = new ArrayList<>();
    private boolean isSolved = false;
    private int totalGuesses = -1;
    private int score = -1;

    public int getTotalGuesses() {
        return totalGuesses;
    }

    public void setTotalGuesses(int totalGuesses) {
        this.totalGuesses = totalGuesses;
    }

    public GameBean() { }

    public String getUID() {
        return UID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Character> getMaskedWord() {
        return maskedWord;
    }

    public void setMaskedWord(List<Character> maskedWord) {
        this.maskedWord = maskedWord;
    }

    public Character getNextGuess() {
        return nextGuess;
    }

    public void setNextGuess(Character nextGuess) {
        this.nextGuess = nextGuess;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public List<Character> getRecentGuesses() {
        return recentGuesses;
    }

    public void setRecentGuesses(List<Character> recentGuesses) {
        this.recentGuesses = recentGuesses;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
