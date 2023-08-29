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

bool determineUserInput (std::string stringInput){
    if (stringInput=="done"){
        return false;
    }
    else {
        return true;
    }
}
bool isPunctuation (char charInput){
    if (charInput=='.' ||charInput=='?' ||charInput=='!') {
        return true;
    }
    else {
        return false;
    }
}

bool isVowel (char charInput){
    if (charInput=='A' ||charInput=='a' ||charInput=='E'||charInput=='e'||charInput=='I'||charInput=='i'||charInput=='O'||charInput=='o'||charInput=='U'||charInput=='u'||charInput=='Y'||charInput=='y') {
        return true;
    }
    else {
        return false;
    }
}
bool isSpace (char charInput){
    if (charInput==' ')
        return true;
    else {
        return false;
    }
}
bool isConsonant (char charInput){
    if (isPunctuation(charInput)==false&&isVowel(charInput)==false&&isSpace(charInput)==false){
        return true;
    }
    else {
        return false;
    }
}
