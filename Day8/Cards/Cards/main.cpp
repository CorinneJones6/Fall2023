//
//  main.cpp
//  Cards
//
//  Created by Sarah Bateman, Corinne Jones, and Lindsay Haslam.
//

#include <iostream>
#include "CardsFunctions.hpp"
#include <string>
#include <vector>
#include <cstdlib>

int main(int argc, const char * argv[]) {
    
    //create one vector with the suits of a deck of cards
    std::vector<std::string> suit {
        "hearts", "diamonds", "clubs", "spades"
    };
    //create another vector with the ranks
    std::vector<int> rank {
        2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14
    };
    
    

    //use the function to create a variable that is storing the completed deck of cards
    std::vector<cards>deck=deckOfCards(suit, rank);
   
    printDeck(deck);
    
    std::cout<<std::endl<<"After shuffled deck: "<<std::endl;
    
    shuffleDeck(deck);
    
    printDeck(deck);
    
    std::vector<cards>deckOfFive=dealFiveCards(deck);
    
    std::cout<<std::endl<<"Deck of five cards: "<<std::endl;
    
    printDeck(deckOfFive);
    
    isFlush(deckOfFive);
    
    isStraight(deckOfFive); 
    
    
    
    return 0;
}

