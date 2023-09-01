//
//  AnalyzerFuncts.hpp
//  BookAnalyzer
//
//  Created by Corinne Jones on 8/31/23.
//

#ifndef AnalyzerFuncts_hpp
#define AnalyzerFuncts_hpp
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <cstdlib>

void printTitle(std::vector<std::string>inputVector);
void printAuthor (std::vector<std::string>inputVector);
void printNumWords (std::vector<std::string>inputVector);
void printNumChars (std::vector<std::string>inputVector);
void inputWordOccurrence (std::vector<std::string>inputVector, std::string lookFor);
void findShortWord (std::vector<std::string>inputVector);
void findLongWord (std::vector<std::string>inputVector);

#endif /* AnalyzerFuncts_hpp */
