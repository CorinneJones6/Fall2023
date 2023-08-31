//
//  WordHelpers.cpp
//  StringAnalyzer
//
//  Created by Corinne Jones on 8/28/23.
//
#include <iostream>
#include <cmath>
#include <string>
#include "WordHelpers.hpp"
#include "LetterHelpers.hpp"

int countSpace (std::string sentenceInput){
    std::cout<< sentenceInput;
    int spaceCount=0;
    for (int i=0; i<sentenceInput.length(); ++i) {
        if (isSpace(sentenceInput[i])==true) {
            spaceCount+=1;
        }
    }
    return spaceCount;
}
int countSentence (std::string sentenceInput){
    int punctuationCount=0;
    for (int i=0; i<sentenceInput.length(); ++i) {
        if (sentenceInput[i]=='!'||sentenceInput[i]=='?'||sentenceInput[i]=='.') {
            punctuationCount+=1;
        }
    }
    return punctuationCount;
}
int countVowel (std::string sentenceInput){
    int vowelCount=0;
    for (int i=0; i<sentenceInput.length(); ++i) {

        if (isVowel(sentenceInput [i])==true) {
            vowelCount+=1;
        }
    }
    return vowelCount;
}
int countConsonant (std::string sentenceInput){
    int consonantCount=0;
    for (int i=0; i<sentenceInput.length(); ++i) {

        if (isConsonant(sentenceInput [i])==true) {
            consonantCount+=1;
        }
    }
    return consonantCount;
}
/*This counts the vowels+consonants in a word, once it gets to a space it pushes that into a count for the string. */
double vowelAndConsonantCount (std::string sentenceInput){
    int stringVowelConsonantCount=0;
    int wordVowelConsonantCount=0;
    for (int i=0; i<sentenceInput.length(); ++i) {
        if (isConsonant(sentenceInput [i])==true||isVowel(sentenceInput[i])==true) {
            wordVowelConsonantCount+=1;
        }
        else if (isSpace(sentenceInput [i])==true) {
            stringVowelConsonantCount+=wordVowelConsonantCount;
            wordVowelConsonantCount=0;
        }
    }
    /*This wasn't added the last wordVowelConsonantCount since it wasn't entering that else-if loop the last time through.*/
    stringVowelConsonantCount+=wordVowelConsonantCount;
    return stringVowelConsonantCount;
}

double vowelCount (std::string sentenceInput){
        int stringVowelCount=0;
        int wordVowelCount=0;
        for (int i=0; i<sentenceInput.length(); ++i) {
            if (isVowel(sentenceInput[i])==true) {
                wordVowelCount+=1;
            }
            else if (isSpace(sentenceInput [i])==true) {
                stringVowelCount+=wordVowelCount;
                wordVowelCount=0;
            }
        }
        stringVowelCount+=wordVowelCount;
        return stringVowelCount;
    }

