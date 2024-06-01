/*
package me.aandster.seng2050a2._depreciated;

import me.aandster.seng2050a2._depreciated.SecretWord;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class GameBeanV2 implements Serializable {

    private final static int MAX_RECENT_GUESSES_DISPLAYED = 3;
    private String username = "";
    private SecretWord secretWord = null;

    public GameBeanV2() throws IOException {

    }

    public String getSecretWord() {
        return secretWord.toString();
    }

    private synchronized String getMaskedWord() {


    }


    public synchronized void setGuess(Character letter) {

 Throw exception if guess is invalid

        boolean isLetterInvalid = Pattern.compile(INVALID_CHAR_REGEX)
                .matcher(letter.toString())
                .find();
        if (isLetterInvalid) throw new IllegalArgumentException("Submitted guess was invalid.");

 Commit guess by updating state of this object

        this.lettersGuessed.add(letter);
    }

    public synchronized List<Character> getRecentGuesses() {
        List<Character> output = new ArrayList<>();
        for (int i = 0; i < MAX_RECENT_GUESSES_DISPLAYED; i++)
            output.add(lettersGuessed.get(lettersGuessed.size() - 1 - i));
        return output;
    }

    public synchronized int getNextRoundNumber() {
        return lettersGuessed.size() + 1;
    }

    public synchronized int getPrevRoundNumber() {
        return lettersGuessed.size();
    }

    private synchronized boolean isInvalidGuess(Character letter) {
        return Pattern.compile(INVALID_CHAR_REGEX)
                .matcher(letter.toString())
                .find();
    }


    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        if (!this.username.isEmpty())
            throw new IllegalArgumentException("Username was already set.");
        if (username == null)
            throw new IllegalArgumentException("Invalid owner.");
        this.username = username;
    }

*
     * Validate and set the secret word


    public void setTheSecretWord(String word) {
        if (this.secretWord != null) throw new IllegalArgumentException("Secret word was already set.");
        this.secretWord = new SecretWord(word);
    }
}
*/
