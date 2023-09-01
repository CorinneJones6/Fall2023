//
//  main.cpp
//  BookAnalyzer
//
//  Created by Corinne Jones on 8/31/23.
//

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <cstdlib>
#include "AnalyzerFuncts.hpp"

int main(int argc, const char * argv[]) {
    //This is assigning a variable
    std::string fileName, stringWord, searchWord;
 
//    This is prompting the user for an input.
    std::cout<<"Please enter the file name you wish to open: ";
   
//    This stores the users input as the variable "fileName".
    std::cin>>fileName;
    std::ifstream inputFile (fileName);
    
    //If uploading the file fails, it will produce this text and exit the program.
    if (inputFile.fail()){
        std::cout<<"Failed to open file: "<<fileName<<"\n";
        exit(1);
    }
    
    std::cout<<"Please enter a word you want to search for: ";
    std::cin>>searchWord;
    
    std::vector<std::string>bookVector;
    while (inputFile >> stringWord){
        bookVector.push_back(stringWord);
    }

    
    std::cout<<std::endl<<"Here is some information about the book:"<<std::endl;
    printTitle(bookVector);
    printAuthor(bookVector);
    printNumWords(bookVector);
    printNumChars(bookVector);
    findShortWord (bookVector);
    findLongWord (bookVector);
    inputWordOccurrence (bookVector, searchWord);
    
    return 0;
}
