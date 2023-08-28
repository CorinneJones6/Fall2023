//
//  main.cpp
//  StringAnalyzer
//
//  Created by Corinne Jones on 8/26/23.
//

#include <iostream>
#include <cmath>
#include <string>
//this returns the user input as either true or false to assist in running the do/while loop.
bool determineUserInput (std::string stringInput){
    if (stringInput=="done"){
        return false;
    }
    else {
        return true;
    }
}
//this determines if a character input is a punctuation mark.
bool isPunctuation (char charInput){
    if (charInput=='.' ||charInput=='?' ||charInput=='!') {
        return true;
    }
    else {
        return false;
    }
}
//This determines if something is a vowel. I saw that there is a command that will convert it to a lower case, but I had already written this out.
bool isVowel (char charInput){
    if (charInput=='A' ||charInput=='a' ||charInput=='E'||charInput=='e'||charInput=='I'||charInput=='i'||charInput=='O'||charInput=='o'||charInput=='U'||charInput=='u'||charInput=='Y'||charInput=='y') {
        return true;
    }
    else {
        return false;
    }
}
//This determines if a character input is a space.
bool isSpace (char charInput){
    if (charInput==' ')
        return true;
    else {
        return false;
    }
}
//This determines if a character input is a consonant. It does this by saying if it isn't a punctuation, vowel, or space it must be a consonant.
bool isConsonant (char charInput){
    if (isPunctuation(charInput)==false&&isVowel(charInput)==false&&isSpace(charInput)==false){
        return true;
    }
    else {
        return false;
    }
}
//This counts the spaces within a string.
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
//This counts the number of sentences within a string by counting the number of punctuation marks. We are assuming that they always have correct punctuation .
int countSentence (std::string sentenceInput){
    int punctuationCount=0;
    for (int i=0; i<sentenceInput.length(); ++i) {
        if (sentenceInput[i]=='!'||sentenceInput[i]=='?'||sentenceInput[i]=='.') {
            punctuationCount+=1;
        }
    }
    return punctuationCount;
}
//This counts the number of vowels within a string.
int countVowel (std::string sentenceInput){
    int vowelCount=0;
    for (int i=0; i<sentenceInput.length(); ++i) {

        if (isVowel(sentenceInput [i])==true) {
            vowelCount+=1;
        }
    }
    return vowelCount;
}
//This counts the number of consonants within a string.
int countConsonant (std::string sentenceInput){
    int consonantCount=0;
    for (int i=0; i<sentenceInput.length(); ++i) {

        if (isConsonant(sentenceInput [i])==true) {
            consonantCount+=1;
        }
    }
    return consonantCount;
}
//This counts the vowels and consonants within a string and returns it as a double.
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
    stringVowelConsonantCount+=wordVowelConsonantCount;
    return stringVowelConsonantCount;
}
//This counts the number of vowels within a string and returns it as a double.
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
   
int main(int argc, const char * argv[]) {
    
    int numWordResult, numSentenceResult, numVowelResult, numConsonantResult;
    std::string userInput, done;
    //    bool punctuationResult, vowelResult, spaceResult, consonantResult;
    double averageWordLengthResult, averageVowelsPerWordResult;
    bool userInputResult;
    //I have a do/while loop that will end the code of the user types in "done." Otherwise, it will keep loop through asking for an input.
    do {
        std::cout << "Enter a string containing one or more sentences: ";
        /* This asks the user for an input and then stores the input as a string*/
        std::getline (std::cin, userInput);
        userInputResult=determineUserInput(userInput);
        {
            //This is all of my called functions.
            numWordResult=countSpace(userInput)+1;
            numSentenceResult=countSentence(userInput);
            numVowelResult=countVowel(userInput);
            numConsonantResult=countConsonant(userInput);
            averageWordLengthResult=vowelAndConsonantCount(userInput)/numWordResult;
            averageVowelsPerWordResult=vowelCount(userInput)/numWordResult;
            //This is all of the outputs with the results. 
            std::cout << "Number of words: " <<numWordResult<<std::endl;
            
            std::cout << "The number of sentences: " <<numSentenceResult
            <<std::endl;
            
            std::cout << "The number of vowels: " <<numVowelResult <<std::endl;
            
            std::cout << "The number of consonants: " <<numConsonantResult <<std::endl;
            
            std::cout << "The average word length: " <<averageWordLengthResult <<std::endl;
            
            std::cout << "The average vowels per word: " <<averageVowelsPerWordResult <<std::endl<<std::endl;
        }
    }
    while (userInputResult==true);
    std::cout<<"Goodbye"<<std::endl;
        return 0;
    
}
