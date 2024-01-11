package assignment06;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpellCheckerTest {

    @Test
    void addToDictionary() {
        // Create a SpellChecker with an empty dictionary
        SpellChecker spellChecker = new SpellChecker();

        // Add words to the dictionary
        spellChecker.addToDictionary("apple");
        spellChecker.addToDictionary("banana");
        spellChecker.addToDictionary("cherry");

        // Test if words are present in the dictionary
        assertTrue(spellChecker.getDictionary().contains("apple"));
        assertTrue(spellChecker.getDictionary().contains("banana"));
        assertTrue(spellChecker.getDictionary().contains("cherry"));

        // Test if non-existing word is not present in the dictionary
        assertFalse(spellChecker.getDictionary().contains("grape"));
    }

    @Test
    void removeFromDictionary() {

        // Create a SpellChecker with an empty dictionary
        SpellChecker spellChecker = new SpellChecker();

        // Add words to the dictionary
        spellChecker.addToDictionary("apple");
        spellChecker.addToDictionary("banana");
        spellChecker.addToDictionary("cherry");

        // Remove words from the dictionary
        spellChecker.removeFromDictionary("banana");
        spellChecker.removeFromDictionary("grape"); // Try removing a non-existing word

        // Test if removed word is not present in the dictionary
        assertFalse(spellChecker.getDictionary().contains("banana"));

        // Test if other words are still present in the dictionary
        assertTrue(spellChecker.getDictionary().contains("apple"));
        assertTrue(spellChecker.getDictionary().contains("cherry"));
    }

    @Test
    void spellCheck() {
        SpellChecker spellChecker = new SpellChecker();

        //Add words to the dictionary
        spellChecker.addToDictionary("apple");
        spellChecker.addToDictionary("banana");
        spellChecker.addToDictionary("cherry");
        spellChecker.addToDictionary("and");
        spellChecker.addToDictionary("a");

        //Create a file
        List<String> misspelledWords = spellChecker.spellCheck(new File("test.txt"));

        //Make an array with the expected misspeled words
        List<String> expectedMisspelledWords = Arrays.asList("aple", "banan", "chery");

        //Test if the expected misspelled and actually misspelled are the same
        assertEquals(expectedMisspelledWords, misspelledWords);
    }

}