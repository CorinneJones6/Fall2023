//
//  NumConvFuncts.cpp
//  NumberConverter
//
//  Created by Corinne Jones on 9/5/23.
//

#include "NumConvFuncts.hpp"
#include <cctype>
#include <iostream>



int charToDigit (char c) {
    int numericValue=0;
    c = tolower(c);
    if (c>='0'&& c<='9') {
        numericValue=c-'0';
        //std:: cout <<numericValue;
    }
    if (c>='a'&& c<='f') {
        numericValue=c-'a'+10;
        //std:: cout <<numericValue;
    }
    return numericValue;
}

int stringToInt (std::string c, int base){
    int result =0;
    for (int i = c.length()-1; i >=0; i--) {
        result += charToDigit(c[i]) * pow(base, (c.length()-1 - i)) ;
    }
    return result;
}

std::string intToDecimalString (int c){
    std::string s1;
    s1=std::to_string(c);
    return s1;
}

std::string intToBinaryString (int n){
    std::string s1=" ";
    while (n>=1) {
      s1=std::to_string(n % 2) + s1;
        n/= 2;
    }
    return s1; 
}

//std::string intToHexadecimalString (int n){
//    intToBinaryString(n);
//
//
//}
