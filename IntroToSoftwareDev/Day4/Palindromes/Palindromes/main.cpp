//
//  main.cpp
//  Palindromes
//
//  Created by Corinne Jones and Sarah Bateman
//

#include <iostream>
#include<string>

int main(int argc, const char * argv[]) {
    
    std::string userInput;
    std::string userReverseInput="";
    
    // Prompt to enter a string and store that as the string "userInput"
    std::cout << "Please enter a word\n";
    std::cin >> userInput;
    
    //if the first and end letter are the same it starts this loop.
    if(userInput.front() == userInput.back()) {
        //this tells the computer to start at the first character, go until the last character, and then go backwards one at a time, assigning each character to the reverseinput string
        for (int i = userInput.length()-1; i>=0; i--) {
            
            userReverseInput+=userInput[i];
        
        }//if the string forward and backward is the same, it prints this is a palindrome
        if (userInput==userReverseInput) {
            
            std::cout << "This is a palindrome"<<std::endl;
        }
        //if the start and end letter are not the same, it goes to here. This is where it prompts the user that it is not a palindrome.
        else {
            std::cout<<"This is not a palindrome"<<std::endl;
        }
    }
    
    
    else {
        std::cout <<"This is not a palindrome" << std::endl;

    }
}
