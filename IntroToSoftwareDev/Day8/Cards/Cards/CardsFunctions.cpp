//
//  CardsFunctions.cpp
//  Cards
//
//  Created by Sarah Bateman on 8/29/23.
//

#include "CardsFunctions.hpp"
#include <string>
#include <iostream>
#include <vector>
#include <cstdlib>
#include <algorithm>
#include <cstdlib>
#include <cassert>



/*"deckOfCards" creates a card deck. First, a new vector is created to hold all of the cards. Next, it assigns ranks and suits to all 52 cards using a for loop. Once a card is created it is pushed back onto the empty deck. The new deck of cards is sent back to main. */
std::vector<Cards> createDeckOfCards(std::vector<std::string> suit,std::vector<int> rank){
    std::vector<Cards> deckOfCards;
    for(int s = 0; s < suit.size(); s++){
        for(int r = 0; r < rank.size(); r++){
            Cards c = {suit[s], rank[r]};
            deckOfCards.push_back(c);
        }
    }
    return deckOfCards;
}

/*"shuffleDeck" shuffles a card deck. It does this by using the rand() operator which pulls a random index.  You then temporarily swap the cards at those indexes, and return them to the new index place. */
void shuffleDeck(std::vector<Cards>&deckOfCards){
    srand(time(0));
    for(int i=0; i<52; i++){
        int j=std::rand()%52;
        Cards tmp;
        tmp=deckOfCards[i];
        deckOfCards[i]=deckOfCards[j];
        deckOfCards[j];
        }
}

/*"dealFiveCards" pulls the first five cards from any deck of cards and creates them as a new deck. It does this by creating an empty vector, then pushing back the cards in the first five indexes. */
std::vector<Cards> dealFiveCards(std::vector<Cards>deckOfCards){
    std::vector<Cards>V1;{
        V1.push_back(deckOfCards[0]);
        V1.push_back(deckOfCards[1]);
        V1.push_back(deckOfCards[2]);
        V1.push_back(deckOfCards[3]);
        V1.push_back(deckOfCards[4]);
    }
    return V1;
}

/*"isFlush" takes in a deck of cards and runs a for loop to check their suits. If at any point a suit is NOT equal to the suit next to it, then it will return false.*/
bool isFlush (std::vector<Cards>deckOfCards){
    for (int i=0; i<4; i++){
        if(deckOfCards[i].suit!=deckOfCards[i+1].suit){
            return false;
        }
    }
    return true;
}


bool sortOneCard (Cards card1, Cards card2){
    return (card1.rank<card2.rank);
}

void sortFiveCards(std::vector<Cards>&deckOfFive){
    std::sort(deckOfFive.begin(), deckOfFive.end(), sortOneCard);
}

                      
bool isStraight (const std::vector<Cards>&deckOfFive){
    for (int i=0; i<4; i++){
        if(deckOfFive[i+1].rank-deckOfFive[i].rank!=1) {
            return false;
        }
    }
    return true;
}


bool isStraightFlush(const std::vector<Cards>&deckOfFive){
    return (isFlush(deckOfFive)&&isStraight(deckOfFive));
    }
 

bool isRoyalFlush(const std::vector<Cards>&deckOfFive){
   return (isStraightFlush(deckOfFive) && deckOfFive.front().rank == 10);
}


bool isFullHouse (const std::vector<Cards>&deckOfFive){
    return (deckOfFive[0].rank==deckOfFive[1].rank&&deckOfFive[3].rank==deckOfFive[4].rank&&(deckOfFive[2].rank==deckOfFive[3].rank||deckOfFive[2].rank==deckOfFive[1].rank));
}

void printStatistics(std::vector<Cards> deckOfFive){
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
}

void printDeck(std::vector <Cards> deckOfCards){
    for (Cards c: deckOfCards){
        
        if(c.rank == 11){
            std::cout << "Jack";
        }
        
        else if(c.rank == 12){
            std::cout << "Queen";
        }
        
        else if(c.rank == 13){
            std::cout << "King";
        }
        else if(c.rank == 14){
            std::cout << "Ace";
        }
        else{
            std::cout << c.rank;
        }
        std::cout << " of " << c.suit << "\n";
    }
}

