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


struct Politician {
    std::string name;
    std::string partyAffiliation;
    std::string stateFederal;
};

std::vector<Politician> Javacans (std::vector<Politician> MyInput);

std::vector<Politician> federalCplusers (std::vector<Politician> MyInput);

#endif /* PoliFuncts_hpp */

