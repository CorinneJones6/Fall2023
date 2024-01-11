//
//  PoliFuncts.cpp
//  PoliStructs
//
//  Created by Corinne Jones on 8/29/23.
//

#include "PoliFuncts.hpp"
#include <stdio.h>
#include <string>
#include <vector>
#include <cmath>
#include <iostream>

//This puts the strings as constants throughout the coding document.
const std::string JavacanString="Javacan";
const std::string CpluserString="Cpluser";
const std::string FederalString="Federal";

//Start with an empty vector. Go through the input vector, determine if javacan, if so add that onto the new vector. Return the new vector.

std::vector<Politician> Javacans (std::vector<Politician> MyInput)
{   std::vector<Politician>V1;
    for (Politician politician: MyInput) {
        if (politician.partyAffiliation==JavacanString) {
            V1.push_back(politician);
        }
    }
    return V1;
}

//This is the same as the forementioned vector except it checks if Cpluser and if Federal. 
std::vector<Politician> federalCplusers (std::vector<Politician> MyInput)
{   std::vector<Politician>V1;
    for (Politician politician: MyInput){
        if (politician.partyAffiliation==CpluserString&&politician.stateFederal==FederalString) {
            V1.push_back(politician);
        }
    }
    return V1;
}
