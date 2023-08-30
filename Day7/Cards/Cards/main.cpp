//
//  main.cpp
//  Cards
//
//  Created by Corinne Jones on 8/29/23.
//
#include "DeckFunct.hpp"
#include <iostream>
#include <string>
#include <vector>
#include <cmath>

int main(int argc, const char * argv[]) {
    // insert code here...
    
    std::vector<playingCard>cardDeckAns=createCardDeck ();
 
    printDeck (cardDeckAns);

    
    return 0;
}
