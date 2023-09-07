//
//  NumConvFuncts.cpp
//  NumberConverter
//
//  Created by Corinne Jones on 9/5/23.
//

#include "NumConvFuncts.hpp"
#include <cctype>
#include <iostream>
#include <vector>
#include <iomanip>
#include <string>



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

std::vector<int> intToHexaString (int n){
    std::vector<int>v1;
    int r=0;
    while (n>=1) {
        r=n%16;
        v1.push_back(r);
        n/=16;
    }
    return v1;
}

void printHex(std::vector<int>hexResult){
    for (int i=hexResult.size()-1; i>=0; i--) {
        if (hexResult[i]==10) {
            std::cout<<"A";
        }
        else if (hexResult[i]==11){
            std::cout<<"B";
        }
        else if (hexResult[i]==12){
            std::cout<<"C";
        }
        else if (hexResult[i]==13){
            std::cout<<"D";
        }
        else if (hexResult[i]==14){
            std::cout<<"E";
        }
        else if (hexResult[i]==15){
            std::cout<<"F";
        }
        else{
            std::cout<<hexResult[i];
        }
        
    }
}





