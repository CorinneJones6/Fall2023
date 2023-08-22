//
//  main.cpp
//  Average
//
//  Created by Corinne Jones on 8/22/23.
// Partnered with Sarah Bateman

#include <iostream>

int main(int argc, const char * argv[])
{
    int assignment1, assignment2, assignment3, assignment4, assignment5; //assigns integer
    float averageAssignment; //assigns float
    
    std::cout << "Enter score for assignment #1\n"; // Prompts to enter a score
    std::cin >> assignment1; //assigns that score to the integer
    
    std::cout << "\nEnter score for assignment #2\n";
    std::cin >> assignment2;
    
    std::cout << "\nEnter score for assignment #3\n";
    std::cin >> assignment3;
    
    std::cout << "\nEnter score for assignment #4\n";
    std::cin >> assignment4;
    
    std::cout << "\nEnter score for assignment #5\n";
    std::cin >> assignment5;
    
    averageAssignment = (assignment1+assignment2+assignment3+assignment4+assignment5)/5.0;//have to divide by 5.0 to keep it in the integer
    std::cout << "\nThe average score for your assignments is\n" << averageAssignment << "\n";
}
