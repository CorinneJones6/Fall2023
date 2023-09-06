//
//  NumConvFuncts.hpp
//  NumberConverter
//
//  Created by Corinne Jones on 9/5/23.
//

#ifndef NumConvFuncts_hpp
#define NumConvFuncts_hpp

#include <stdio.h>
#include <cctype>
#include <iostream>


int stringToInt (std::string c, int base);
std::string intToDecimalString (int c);
std::string intToBinaryString (int n);
std::string intToHexadecimalString (int n); 

#endif /* Num Functs_hpp */
