package me.aandster.seng2050a2.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GtwGame implements Serializable {

    private final static int MAX_RECENT_GUESSES_DISPLAYED = 3;
    private final static float MAX_INITIAL_MASKED_LETTERS_FACTOR = 2f / 3f;
    private final static Character OBFUSCATION_CHAR = '_';
    private final static String INVALID_CHAR_REGEX = "[^a-zA-Z]";
    private final List<Character> lettersHidden = new ArrayList<>();
    private final List<Character> lettersGuessed = new ArrayList<>();
    private final String secretWord;
    private final String owner;

    public GtwGame(String owner, String secretWord) throws IOException {

        /* Validate and set the owner */
        if (owner == null) throw new IllegalArgumentException("Invalid owner.");
        this.owner = owner;

        /* Validate and set the secret word */
        if (secretWord == null || secretWord.isEmpty()) throw new IllegalArgumentException("Invalid secret word.");
        if (Pattern.compile(INVALID_CHAR_REGEX).matcher(secretWord).find())
            throw new IllegalArgumentException("Invalid secret word.");
        this.secretWord = secretWord;

        /* Do the initial masking of the letters */
        final int maxMaskedLetters = Math.round(MAX_INITIAL_MASKED_LETTERS_FACTOR * secretWord.length());
        while (true) {
            final List<Character> maskedWord = getMaskedWord();

            /* Choose the next letter to obfuscate */
            List<Character> charsWeCanHide = maskedWord.stream().distinct().filter(s -> !OBFUSCATION_CHAR.equals(s)).collect(Collectors.toUnmodifiableList());

            Random rng = new Random();
            Character nextCharToHide = charsWeCanHide.get(rng.nextInt(charsWeCanHide.size()));

            /* Generate the word with occurrences of that char now obfuscated */
            List<Character> nextMaskedWord = maskedWord.stream().map(s -> (s.equals(nextCharToHide)) ? OBFUSCATION_CHAR : s).collect(Collectors.toList());

            /* Check that this new matched word does not have mre masked chars than specified in maxMaskedWords */
            int numberOfLettersMasked = (int) nextMaskedWord.stream().filter(s -> s.equals(OBFUSCATION_CHAR)).count();

            /* Stop looping if we're going to mask too many letters (more than 2/3 of total letters in theSecretWord) */
            if (numberOfLettersMasked > maxMaskedLetters) break;

            /* Store the new value for maskedWord */
            this.lettersHidden.add(nextCharToHide);
        }

        /* Check that at least one letter is hidden */
        if (this.lettersHidden.isEmpty()) throw new InternalError("No letters are hidden: " + lettersHidden);
    }

    private static int scoreFormula(int wordLength, int roundNumber) {
        return (2 * wordLength - roundNumber) * 10;
    }

    public static boolean isInvalidGuess(String guess) {
        return guess.length() > 1 || Pattern.matches(INVALID_CHAR_REGEX, guess);
    }

    private synchronized List<Character> getMaskedWord() {

        /* Determine which letters have not been guessed */
        List<Character> lettersNotGuessedYet = lettersHidden.stream().filter(e -> !lettersGuessed.contains(e)).collect(Collectors.toList());

        /* Return secret word but obfuscate letters still needing to be guessed */
        List<Character> collect = Arrays.stream(secretWord.split("")).map(s -> s.charAt(0)).map(c -> (lettersNotGuessedYet.contains(c)) ? OBFUSCATION_CHAR : c).collect(Collectors.toUnmodifiableList());
        return collect;
    }

    public synchronized boolean isSolved() {
        return !getMaskedWord().contains(OBFUSCATION_CHAR);
    }

    public synchronized void makeGuess(Character letter) {

        Character guessedLetter = Character.toUpperCase(letter);

        /* Throw exception if guess is invalid */
        if (Pattern.compile(INVALID_CHAR_REGEX).matcher(guessedLetter.toString()).find())
            throw new IllegalArgumentException("Submitted guess was invalid.");

        /* If guessed already */
        if (this.lettersGuessed.contains(guessedLetter))
            throw new IllegalArgumentException("Letter already guessed this round: '" + guessedLetter + "'.");

        /* Commit guess by updating state of this object */
        this.lettersGuessed.add(guessedLetter);
    }

    private synchronized List<Character> getRecentGuesses() {
        List<Character> output = new ArrayList<>();
        for (int i = 0; i < MAX_RECENT_GUESSES_DISPLAYED && i < lettersGuessed.size(); i++)
            output.add(lettersGuessed.get(lettersGuessed.size() - 1 - i));
        return Collections.unmodifiableList(output);
    }

    private synchronized int getNextRoundNumber() {
        return lettersGuessed.size() + 1;
    }

    private synchronized int getPrevRoundNumber() {
        return lettersGuessed.size();
    }

    private String getOwner() {
        return owner;
    }

    public GameBean getBean() {
        GameBean bean = new GameBean();
        bean.setUsername(this.getOwner());
        bean.setRoundNumber((this.isSolved()) ? getPrevRoundNumber() : getNextRoundNumber());
        bean.setMaskedWord(this.getMaskedWord());
        bean.setRecentGuesses(this.getRecentGuesses());
        bean.setSolved(this.isSolved());
        bean.setTotalGuesses(this.lettersGuessed.size());
        bean.setScore((this.isSolved()) ? this.getScore() : -1);
        return bean;
    }

    private int getScore() {
        int totalRounds = getPrevRoundNumber();
        int wordLength = secretWord.length();
        return scoreFormula(wordLength, totalRounds);
    }

    public int hasLetterBeenGuessedAlready(Character letter) {
        return this.lettersGuessed.indexOf(letter);
    }
}
