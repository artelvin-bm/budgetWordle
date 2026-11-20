/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.game;

/**
 *
 * @author Art Elvin
 */
public class guessChecker {

    public static String colorizeGuess(String word, String guess) {
        StringBuilder coloredGuess = new StringBuilder("<html><center>");

        // store unique letters in word
        char[] uniqueLetters = new char[8];
        int uniqueLettersCount = 0;
        for (int i = 0; i < word.length(); i++) {
            boolean repeats = false;
            for (int j = i; j >= 0; j--) {
                if (word.charAt(i) == word.charAt(j) && i != j) {
                    repeats = true;
                }
            }
            if (!repeats) {
                uniqueLetters[uniqueLettersCount] = word.charAt(i);
                uniqueLettersCount++;
            }
        }

        // store how many time a letter is in word
        int[] occurenceCount = new int[8];
        for (int i = 0; i < uniqueLettersCount; i++) {
            for (int j = i; j < word.length(); j++) {
                if (uniqueLetters[i] == word.charAt(j)) {
                    occurenceCount[i]++;
                }
            }
        }

        // store guessed and correctly positioned letters in guess
        char[] correctPosition = new char[8];
        int correctPlaceCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess.charAt(i)) {
                correctPosition[correctPlaceCount] = guess.charAt(i);
                correctPlaceCount++;
            }
        }

        // store guessed letters that are in word but not in correct position
        char[] inWord = new char[8];
        int inWordCount = 0;
        for (int i = 0; i < guess.length(); i++) {
            for (int j = 0; j < word.length(); j++) {
                if (guess.charAt(i) == word.charAt(j)) {
                    inWord[inWordCount] = guess.charAt(i);
                    inWordCount++;
                    break;
                }
            }
        }

        // store how many times a unique letter has been colorized yellow to account for repeating letters
        int[] yellowCount = new int[8];

        for (int i = 0; i < guess.length(); i++) {
            char guessChar = guess.charAt(i);
            char wordChar = i < word.length() ? word.charAt(i) : ' '; // To handle words of different lengths

            if (word.indexOf(guessChar) != -1) { // If the guessed letter is in the word
                if (guessChar == wordChar) { // If the guessed letter is in the correct position
                    coloredGuess.append("<font color=\"#6aaa64\">").append(guessChar).append("</font>");
                } else if (word.indexOf(guessChar) != i) { // If the guessed letter is in the word but not in correct position
                    
                    // from this to LINE 107 is to account for repeating letters
                    int greenCount = 0;
                    boolean allGood = false;                    
                    for (int j = 0; j < uniqueLettersCount; j++) {
                        if (uniqueLetters[j] == guessChar) {
                            for (int k = 0; k < correctPosition.length; k++) {
                                if (correctPosition[k] == guessChar) {
                                    greenCount++;
                                }
                            }
                        }
                    }
                    
                    for (int j = 0; j < uniqueLettersCount; j++) {
                        if (guessChar == uniqueLetters[j]) {
                            if (greenCount == occurenceCount[j]) {
                                allGood = true;
                            }
                        }
                    }
                    
                    if (allGood) {
                        coloredGuess.append(guessChar);
                    } else {
                        for (int k = 0; k < uniqueLettersCount; k++) {
                            if (guessChar == uniqueLetters[k]) {
                                if (yellowCount[k] + greenCount < occurenceCount[k]) {
                                    coloredGuess.append("<font color=\"#d1b036\">").append(guessChar).append("</font>");
                                    yellowCount[k]++;
                                } else {
                                    coloredGuess.append(guessChar);
                                }
                            }
                        }
                    }
                } 
            } else { // If the guessed letter is not in the word
                coloredGuess.append(guessChar);
            }
        }

        coloredGuess.append("</html>");
        return coloredGuess.toString();
    }
}
