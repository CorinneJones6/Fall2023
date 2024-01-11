//
//  main.cpp
//  Road Trip Calculator
//
//  Created by Corinne Jones on 8/22/23.
//

#include <iostream>

int main(int argc, const char * argv[])
{
   
    std::cout << "Enter driving distance in miles \n"; // Writes the prompt in quotations
    float drivingDistance; //assigns the driving distance as a float
    std::cin >> drivingDistance ; //has the user input a number
    std::cout << "The user entered the distance in miles as " <<drivingDistance; //User knows what they put
    
    std::cout << "\nEnter vehicle's miles per gallon efficiency \n";
    float milesPerGallonEffeciency;
    std::cin >> milesPerGallonEffeciency;
    std::cout << "The user entered the miles/gallon efficiency as " <<milesPerGallonEffeciency;
    
    std::cout << "\nEnter cost of gas in dollars per gallon \n";
    float costGasInDollarsPerGallon;
    std::cin >> costGasInDollarsPerGallon;
    std::cout << "The user entered the gas in dollars per gallon as " <<costGasInDollarsPerGallon;
    
    float numberOfGallonsUsed; //Assigns a float
    numberOfGallonsUsed = drivingDistance/milesPerGallonEffeciency; //Assigns a number to the float
    std::cout << "\nThe number of gallons used is " << numberOfGallonsUsed; //Prints this number in the output
    
    float cost;
    cost = numberOfGallonsUsed*costGasInDollarsPerGallon;
    std::cout << "\nThe total cost of the trip is " << cost;
    std::cout << " dollars\n";
}
