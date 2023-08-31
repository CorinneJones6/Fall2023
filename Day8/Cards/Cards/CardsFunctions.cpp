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



/*"deckOfCards" creates a card deck. First, a new vector is created to hold all of the cards. Next, it assigns ranks and suits to all 52 cards using a for loop. Once a card is created it is pushed back onto the empty deck. The new deck of cards is sent back to main. */
std::vector<cards> deckOfCards(std::vector<std::string> suit,std::vector<int> rank){
    std::vector<cards> deckOfCards;
    for(int s = 0; s < suit.size(); s++){
        for(int r = 0; r < rank.size(); r++){
            cards c = {suit[s], rank[r]};
            deckOfCards.push_back(c);
        }
    }
    return deckOfCards;
}

/*"shuffleDeck" shuffles a card deck. It does this by using the rand() operator which pulls a random index.  You then temporarily swap the cards at those indexes, and return them to the new index place. */
void shuffleDeck(std::vector<cards>&deckOfCards){
    for(int i=0; i<52; i++){
        int j=std::rand()%52;
        cards tmp;
        tmp=deckOfCards[i];
        deckOfCards[i]=deckOfCards[j];
        deckOfCards[j];
        }
}

/*"dealFiveCards" pulls the first five cards from any deck of cards and creates them as a new deck. It does this by creating an empty vector, then pushing back the cards in the first five indexes. */
std::vector<cards> dealFiveCards(std::vector<cards>deckOfCards){
    std::vector<cards>V1;{
        V1.push_back(deckOfCards[0]);
        V1.push_back(deckOfCards[1]);
        V1.push_back(deckOfCards[2]);
        V1.push_back(deckOfCards[3]);
        V1.push_back(deckOfCards[4]);
    }
    return V1;
}

/*"isFlush" takes in a deck of cards and runs a for loop to check their suits. If at any point a suit is NOT equal to the suit next to it, then it will return false.*/
bool isFlush (std::vector<cards>deckOfCards){
    for (int i=0; i<4; i++){
        if(deckOfCards[i].suit!=deckOfCards[i+1].suit){
            return false;
        }
    }
    return true;
}


bool sortOneCard (cards card1, cards card2){
    return (card1.rank<card2.rank);
}

void sortFiveCards(std::vector<cards>&deckOfFive){
    std::sort(deckOfFive.begin(), deckOfFive.end(), sortOneCard);
}

                      
bool isStraight (std::vector<cards>deckOfFive){
    sortFiveCards(deckOfFive);
    for (int i=0; i<4; i++){
        if(deckOfFive[i+1].rank-deckOfFive[i].rank!=1) {
            return false;
        }
    }
    return true;
}


bool isStraightFlush(std::vector<cards>deckOfFive){
    return (isFlush(deckOfFive)&&isStraight(deckOfFive));
    }
 

bool isRoyalFlush(std::vector<cards>deckOfFive){
   return (isStraightFlush(deckOfFive) && deckOfFive.front().rank == 10);
}

bool isFullHouse (std::vector<cards>deckOfFive){
    return (deckOfFive[0].rank==deckOfFive[1].rank&&deckOfFive[3].rank==deckOfFive[4].rank&&(deckOfFive[2].rank==deckOfFive[3].rank||deckOfFive[2].rank==deckOfFive[1].rank));
}

void printDeck(std::vector <cards> deckOfCards){
    for (cards c: deckOfCards){
        
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

