//
//  main.cpp
//  StringAnalyzer
//
//  Created by Corinne Jones on 8/26/23.
//

#include <iostream>
#include <cmath>
#include <string>
#include "LetterHelpers.hpp"
#include "WordHelpers.hpp"


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
