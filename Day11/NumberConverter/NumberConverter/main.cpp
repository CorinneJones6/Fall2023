//
//  main.cpp
//  NumberConverter
//
//  Created by Corinne Jones on 9/5/23.
//

#include <iostream>
#include "NumConvFuncts.hpp"

int main(int argc, const char * argv[]) {
    std::string userInputString, binaryNumber;
    int baseNumber, intString, userInputInt;
    
    
    std::cout <<"Please enter a number to convert to decimals: ";
    std::cin>>userInputString;
    
    std::cout<< "Please enter the base that number uses: ";
    std::cin>>baseNumber;
    
    intString= stringToInt(userInputString, baseNumber);
    
    std::cout << "Result is: " << intString<<std::endl;
    
    std::cout<<"Please enter a number to convert to a string: ";
    std::cin>>userInputInt;
    
    std::cout<<"Here is that number as a string: "<<intToDecimalString(userInputInt)<<std::endl;
 
    binaryNumber=intToBinaryString (userInputInt);
    
    std::cout<<"Here is that number as a binary number: "<<
    binaryNumber<<std::endl; 
    

    
    return 0;
}
