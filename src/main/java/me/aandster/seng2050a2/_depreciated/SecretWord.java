/*
package me.aandster.seng2050a2._depreciated;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SecretWord implements Serializable {

    public final static Character OBFUSCATION_CHAR = '_';
    private final static Float MAX_INITIAL_MASKED_LETTERS_FACTOR = 2f / 3f;
    private final static String INVALID_CHAR_REGEX = "[^a-zA-Z]";
    private final List<Character> lettersHidden = new ArrayList<>();
    private final List<Character> lettersGuessed = new ArrayList<>();
    private Character nextGuess;
    private String secretWord;

    public SecretWord(String word) {

        */
/* Check if the provided secret word if not invalid. *//*

        if (Pattern.compile(INVALID_CHAR_REGEX).matcher(word).find())
            throw new IllegalArgumentException("Invalid secret word.");

        */
/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~ INITIALISE ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *//*

        */
/* Do the initial masking of the letters *//*

        final int maxMaskedLetters = Math.round(MAX_INITIAL_MASKED_LETTERS_FACTOR * word.length());
        while (true) {
            final String maskedWord = this.toString();

            */
/* Choose the next letter to obfuscate *//*

            char nextCharToHide = Arrays.stream(maskedWord.split(""))
                    .distinct()
                    .filter(s -> !OBFUSCATION_CHAR.toString().equals(s))
                    .findAny()
                    .orElseThrow()
                    .charAt(0);

            */
/* Generate the word with occurrences of that char now obfuscated *//*

            String nextMaskedWord = maskedWord.replace(nextCharToHide, OBFUSCATION_CHAR);

            */
/* Check that this new matched word does not have more masked chars than specified in maxMaskedWords *//*

            int numberOfLettersMasked = Pattern.compile("[" + OBFUSCATION_CHAR.toString() + "]")
                    .matcher(nextMaskedWord)
                    .groupCount();

            */
/* Stop looping if we're going to mask too many letters (more than 2/3 of total letters in theSecretWord) *//*

            if (numberOfLettersMasked > maxMaskedLetters)
                break;

            */
/* Store the new value for maskedWord *//*

            this.lettersHidden.add(nextCharToHide);
        }

        */
/* Check that at least one letter is hidden *//*

        if (this.lettersHidden.isEmpty())
            throw new InternalError("No letters are hidden: " + lettersHidden);
    }

    */
/**
     * @return Masked representation of the word based on letters currently known. e.g. "NEWCASTLE" might be "N_W___TL_"
     *//*

    public synchronized String toString() {

        */
/* Determine which letters have not been guessed *//*

        List<Character> lettersNotGuessedYet = lettersHidden.stream()
                .filter(e -> !lettersGuessed.contains(e))
                .collect(Collectors.toUnmodifiableList());

        */
/* Return secret word but obfuscate letters still needing to be guessed *//*

        return Arrays.stream(secretWord.split("[.]"))       //  Split string into strings of each char
                .map(s -> s.charAt(0))                             // Effectively convert to char
                .map(c -> (lettersNotGuessedYet.contains(c)) ? OBFUSCATION_CHAR : c)  // Substitute obfuscation chars
                .toString();                                       // Make it stringy
    }

    public synchronized void makeGuess(Character guess) throws GuessException {

        */
/* See guess is an invalid char *//*

        boolean isGuessInvalid = Pattern.matches(INVALID_CHAR_REGEX, String.valueOf(guess));
        if (isGuessInvalid) throw new GuessException("Invalid guess....");

        */
/* See if guess has already been made *//*

        boolean isAlreadyGuessed = lettersGuessed.stream().anyMatch(prevGuess -> prevGuess.equals(guess));
        if (isAlreadyGuessed) {
            int prevGuessRound = lettersGuessed.indexOf(guess);
            throw new GuessException("Letter '" + guess + "' was already guessed in round " + prevGuessRound + ".");
        }

        */
/* Commit the guess to guesses *//*

        this.lettersGuessed.add(guess);
    }

    public synchronized boolean isSolved() {
        return lettersGuessed.containsAll(lettersHidden);
    }

    public synchronized Map<Integer, Character> previousGuesses(int maxRoundsToInclude) {
        Map<Integer, Character> recentGuesses = new HashMap<>();
        for (int i = 0; i < maxRoundsToInclude && i < lettersGuessed.size(); i++)
            recentGuesses.put((lettersGuessed.size() - i), (lettersGuessed.get(lettersGuessed.size() - 1)));
        return recentGuesses;
    }

    public static class GuessException extends IllegalArgumentException {

        public GuessException(String message) {
            super(message);
        }

    }


}
*/
