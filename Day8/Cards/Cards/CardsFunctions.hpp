//
//  CardsFunctions.hpp
//  Cards
//
//  Created by Sarah Bateman on 8/29/23.
//

#ifndef CardsFunctions_hpp
#define CardsFunctions_hpp

#include <stdio.h>
#include <string>
#include <iostream>

#endif /* CardsFunctions_hpp */

struct cards {
    std::string suit;
    int rank;
};

std::vector<cards> deckOfCards(std::vector<std::string> suit,std::vector<int> rank);
void printDeck(std::vector <cards> deckOfCards);
std::vector<cards> dealFiveCards(std::vector<cards>deckOfCards);
void sortFiveCards(std::vector<cards>&deckOfFive);
bool isFlush (std::vector<cards>deckOfCards); 
bool isStraight(std::vector<cards>deckOfCards);
void shuffleDeck(std::vector<cards>&deckOfCards);




