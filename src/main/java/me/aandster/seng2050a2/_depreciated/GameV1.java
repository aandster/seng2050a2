/*
package me.aandster.seng2050a2._depreciated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GameV1 implements Serializable {

    private final static int ROUNDS_TO_LOOK_BACK = 3;
    private final static String INVALID_CHAR_REGEX = "[^a-zA-Z]";
    private final List<String> guessedLetters = new ArrayList<>();
//  private final String secretWord;
    */
/*private final String owner;
    public GameV1(String secretWord, String owner) {
        *//*
*/
/*
        *//*
*/
/**//*
*/
/* Validate and set the secret word *//*
*/
/**//*
*/
/*
        if (isInvalidWord(secretWord)) throw new IllegalArgumentException();
        this.secretWord = secretWord;*//*
*/
/*

        *//*
*/
/* Validate and set the owner *//*
*/
/*
        if (owner.isEmpty()) throw new IllegalArgumentException();
        this.owner = owner;

        *//*
*/
/* Initialise the revealed letters *//*
*/
/*
//        initialise();
    }*//*


*/
/*    public static boolean isInvalidWord(String word) {
        Matcher invalidMatcher = Pattern.compile(INVALID_CHAR_REGEX).matcher(word);
        return word.length() < MINIMUM_WORD_LENGTH || invalidMatcher.find();
    }

    public static boolean isInvalidLetter(String letter) {
        Matcher invalidMatcher = Pattern.compile(INVALID_CHAR_REGEX).matcher(letter);
        return letter.length() != 1 || invalidMatcher.find();
    }*//*


    */
/*public synchronized String getOwner() {
        return owner;
    }*//*


*/
/*    public synchronized void submitGuess(String letter) {

        *//*
*/
/* Validate the guess *//*
*/
/*
        if (isInvalidGuess(letter)) throw new IllegalArgumentException("Invalid guess: " + letter);

        *//*
*/
/* Commit the guess to the guessed list if not already there *//*
*/
/*
        if (isNotAlreadyGuessed(letter)) this.guessedLetters.add(letter);
    }*//*


*/
/*    public synchronized List<String> getHint() {
        return Arrays.stream(secretWord.split("[.]")).map(s -> isNotAlreadyGuessed(s) ? s : OBFUSCATION_CHAR).collect(Collectors.toList());
    }*//*


*/
/*    public synchronized boolean isGameSolved() {
        return guessedLetters.stream().noneMatch(this::isNotAlreadyGuessed);
    }*//*


    */
/*public synchronized int nextRound() {
        return guessedLetters.size() + 1;
    }

    public synchronized int lastRound() {
        return guessedLetters.size();
    }*//*


    */
/*private synchronized boolean isInvalidGuess(String letter) {
        return isInvalidLetter(letter);
    }*//*


    */
/*private synchronized boolean isNotAlreadyGuessed(String letter) {
        return guessedLetters.stream().noneMatch(l -> l.equals(letter));
    }*//*


    */
/*private synchronized void initialise() {

        *//*
*/
/* Determine how many (n) letters to reveal *//*
*/
/*

        *//*
*/
/* Randomly pick (n) letters to reveal *//*
*/
/*

        *//*
*/
/* Reveal the letters *//*
*/
/*
    }*//*


}
*/
