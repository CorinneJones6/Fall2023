//
//  main.cpp
//  Palindromes
//
//  Created by Corinne Jones on 8/24/23.
//

#include <iostream>
#include<string>

int main(int argc, const char * argv[]) {
    
    std::string userInput;
    std::string userReverseInput=" ";
    
    // Prompt to enter a string and store that as the string "userInput"
    std::cout << "Please enter a word\n";
    std::cin >> userInput;
    
    if(userInput.front() == userInput.back()) {
        
        for (int i = userInput.length()-1; i>=0; i--) {
            
            userReverseInput+=userInput[i];
            
        }
        
        std::cout << "This is a palindrome";
    }
    
    
    else {
        std::cout <<"This is not a palindrome" << std::endl;

    }
}
