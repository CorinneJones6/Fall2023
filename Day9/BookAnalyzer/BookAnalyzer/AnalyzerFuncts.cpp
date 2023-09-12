//
//  AnalyzerFuncts.cpp
//  BookAnalyzer
//
//  Created by Corinne Jones on 8/31/23.
//

#include "AnalyzerFuncts.hpp"
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <cstdlib>


void printTitle(std::vector<std::string>inputVector){
    int i=0;
    while (inputVector[i]!="Title:"&&i<inputVector.size()) {
        i++;
    }
    while (inputVector[i]!="Author:"&&i<inputVector.size()) {
        std::cout<<inputVector[i]<< " ";
        i++;
        }
    std::cout<<std::endl; 
}

void printAuthor (std::vector<std::string>inputVector){
    int i=0;
    while (inputVector[i]!="Author:"&&i<inputVector.size()) {
        i++;
    }
    while (inputVector[i]!="Release"&&i<inputVector.size()) {
        std::cout<<inputVector[i]<< " ";
        i++;
    }
    std::cout<<std::endl;
}

void printNumWords (std::vector<std::string>inputVector){
        std::cout<<"Number of words: "<<inputVector.size()<<std::endl;
}

//int findNumbChars (std::vector<std::string>inputVector){
//    int characters=0;
//    for(std::string word: inputVector)
//    {
//        characters += word.size();
//    }
//    return count;
//}

void printNumChars (std::vector<std::string>inputVector){
    int count=0;
    for(std::string word: inputVector)
    {
        count += word.size();
    }
    std::cout<<"Number of characters: "<<count;
    std::cout<<std::endl;
    }


void inputWordOccurrence (std::vector<std::string>inputVector, std::string lookFor){
    int count=0;

    for(std::string word: inputVector){
        if (word==lookFor) {
            count ++;
        }
    }
    std::cout<<"The word "<<lookFor<<" occurs "<<count<<" times:";
    std::cout<<std::endl;
    }

void inputWordPlace (std::vector<std::string>inputVector, std::string lookFor){
    int count=0;
    int i=0;
    float percent =0;
    for(std::string word: inputVector) {
        count+=word.size();
        i++;
        percent=count/inputVector.size(); 
        if (word==lookFor) {
            std::cout<<"at "<<count<<"%: "<<inputVector[i-2]<<" "<<lookFor<<" "<<inputVector[i];
            std::cout<<std::endl;
        }
    }
    }

void findShortWord (std::vector<std::string>inputVector){
    if (inputVector.size()==0) {
        return;
    }
    std::string smallWord=inputVector[0];
    for(std::string word: inputVector){
        if (word.size()<smallWord.size()) {
            smallWord=word;
        }
    }
    std::cout<<"Shortest word: "<<smallWord<<" ";
    std::cout<<std::endl;
    }

void findLongWord (std::vector<std::string>inputVector){
    if (inputVector.size()==0) {
        return;
    }
    std::string bigWord=inputVector[0];
    for(std::string word: inputVector){
        if (word.size()>bigWord.size()) {
            bigWord=word;
        }
    }
    std::cout<<"Long word: "<<bigWord<<" ";
    std::cout<<std::endl;
    }






