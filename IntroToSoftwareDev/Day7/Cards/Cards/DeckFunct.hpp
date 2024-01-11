//
//  DeckFunct.hpp
//  Cards
//
//  Created by Corinne Jones on 8/29/23.
//

#ifndef DeckFunct_hpp
#define DeckFunct_hpp
#include <stdio.h>
#include <string>
#include <vector>

struct playingCard {
    int rank;
    std::string suit;
};

std::vector<playingCard> createCardDeck ();
void printDeck (std::vector<playingCard> deck); 

#endif /* DeckFunct_hpp */
