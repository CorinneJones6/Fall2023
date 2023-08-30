//
//  DeckFunct.cpp
//  Cards
//
//  Created by Corinne Jones on 8/29/23.
//

#include "DeckFunct.hpp"
#include <stdio.h>
#include <string>
#include <vector>
#include <cmath>
#include <iostream>

const std::string diamString="Diamond";
const std::string spadString="Spade";
const std::string clubString="Club";
const std::string heartString="Heart";

const std::string aceString="Ace";
const std::string jackString="Jack";
const std::string queenString="Queen";
const std::string kingString="King";



std::vector<playingCard> createCardDeck (){
    std::vector<playingCard>V1;
    int rank []={1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    std::string suit []={diamString, spadString, clubString, heartString};
    for (int i=0; i<4; i++) {
        for (int j=0; j<13; j++) {
            playingCard c;
            c.suit=suit[i];
            c.rank=rank[j];
            V1.push_back(c);
        }
    }
    return V1;
}

void printDeck (std::vector<playingCard> deck){

    for (playingCard p:deck){
    if (p.rank=11) {
        std::cout<<jackString;
    }
    std::cout<<p.rank<<" ";
    std::cout<<p.suit<<" "<<std::endl;
}
    
}

   




