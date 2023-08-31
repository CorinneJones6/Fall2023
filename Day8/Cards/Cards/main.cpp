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
    
    if (isFlush(deckOfFive)){
        std::cout<<"This is a flush \n";
    }
    else {
        std::cout<<"This is not a flush \n";
    }
    
    
    if (isStraight(deckOfFive)){
        std::cout<<"This is a straight \n";
    }
    else {
        std::cout<<"This is not a straight \n";
    }
    
    if (isStraightFlush(deckOfFive)){
        std::cout<<"This is a straight flush \n";
    }
    else {
        std::cout<<"This is not a straight flush \n";
    }
    
    
    if (isRoyalFlush(deckOfFive)){
        std::cout<<"This is a royal flush \n";
    }
    else {
        std::cout<<"This is not a royal flush \n";
    }
    
    if (isFullHouse(deckOfFive)){
        std::cout<<"This is a full house \n";
    }
    else {
        std::cout<<"This is not a full house \n";
    }
    
//    std::cout<<"Practice decks:"<<std::endl;
    
//    cards c1 {"hearts", 3};
//    cards c2 {"hearts", 3};
//    cards c3 {"hearts", 4};
//    cards c4 {"hearts", 4};
//    cards c5 {"hearts", 4};
//
//    std::vector<cards> practiceDeck1 {c1, c2, c3, c4, c5};
//
//    if (isFlush(practiceDeck1)){
//        std::cout<<"This is a flush \n";
//    }
//    else {
//        std::cout<<"This is not a flush \n";
//    }
//
//
//    if (isStraight(practiceDeck1)){
//        std::cout<<"This is a straight \n";
//    }
//    else {
//        std::cout<<"This is not a straight \n";
//    }
//
//    if (isStraightFlush(practiceDeck1)){
//        std::cout<<"This is a straight flush \n";
//    }
//    else {
//        std::cout<<"This is not a straight flush \n";
//    }
//
//
//    if (isRoyalFlush(practiceDeck1)){
//        std::cout<<"This is a royal flush \n";
//    }
//    else {
//        std::cout<<"This is not a royal flush \n";
//    }
//
//    if (isFullHouse(practiceDeck1)){
//        std::cout<<"This is a full house \n";
//    }
//    else {
//        std::cout<<"This is not a full house \n";
//    }
    
    return 0;
}

