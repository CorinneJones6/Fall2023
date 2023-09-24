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

struct Cards {
    std::string suit;
    int rank;
};

std::vector<Cards> createDeckOfCards(std::vector<std::string> suit,std::vector<int> rank);
void shuffleDeck(std::vector<Cards>&deckOfCards);
std::vector<Cards> dealFiveCards(std::vector<Cards>deckOfCards);
void sortFiveCards(std::vector<Cards>&deckOfFive);
bool isFlush (std::vector<Cards>deckOfCards); 
bool isStraight (const std::vector<Cards>&deckOfFive); 
bool isStraightFlush(const std::vector<Cards>&deckOfFive);
bool isRoyalFlush(const std::vector<Cards>&deckOfFive);
bool isFullHouse (const std::vector<Cards>&deckOfFive);
void printDeck(std::vector <Cards> deckOfCards);
void printStatistics(std::vector<Cards> deckOfFive);




