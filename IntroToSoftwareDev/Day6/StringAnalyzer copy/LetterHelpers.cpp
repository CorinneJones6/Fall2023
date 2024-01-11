//
//  LetterHelpers.cpp
//  StringAnalyzer
//
//  Created by Corinne Jones on 8/28/23.
//
#include <iostream>
#include <cmath>
#include <string>
#include "LetterHelpers.hpp"


bool isEndOfSentence (char charInput){
    return (charInput=='.' ||charInput=='?' ||charInput=='!');
}

bool isVowel (char charInput){
    std::tolower(charInput);
    return (charInput=='a'||charInput=='e'||charInput=='i'||charInput=='o'||charInput=='u'||charInput=='y');
}

bool isSpace (char charInput){
    return (charInput==' ');
}

bool isConsonant (char charInput){
    return (isEndOfSentence(charInput)==false&&isVowel(charInput)==false&&isSpace(charInput)==false);
}
