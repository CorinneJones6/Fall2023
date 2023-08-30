//
//  PoliFuncts.hpp
//  PoliStructs
//
//  Created by Corinne Jones on 8/29/23.
//

#ifndef PoliFuncts_hpp
#define PoliFuncts_hpp
#include <stdio.h>
#include <string>
#include <vector>

//Here we have the formation of my struct with the name, party affiliation, and state federal all as strings.
struct Politician {
    std::string name;
    std::string partyAffiliation;
    std::string stateFederal;
};

//Here are the headers for both of the functions. They both pass in a vector and pass out a vector. 
std::vector<Politician> Javacans (std::vector<Politician> MyInput);

std::vector<Politician> federalCplusers (std::vector<Politician> MyInput);

#endif /* PoliFuncts_hpp */

