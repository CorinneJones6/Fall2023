//
//  main.cpp
//  VolumeConvert
//
//  Created by Sarah Bateman on 8/22/23.
//  Partner with Corinne Jones

#include <iostream>

int main(){
    //name all the variables and their types
    float ounces, cups, pints, gallons, liters, cubicInches;
    
    // ask the user how many ounces to calculate
    std::cout << "How many ounces?\n";
    std::cin >> ounces;
    
    //convert ounces into all the other measurements
    //cups, pints, and gallons are easiest to divide
    cups = ounces / 8;
    pints = ounces / 16;
    gallons = ounces /128;
    //liters and cubic inches are easiest to multiply
    liters = ounces * 0.0296;
    cubicInches = ounces * 1.8;
    
    //print all the calculations
    std::cout << "Ounces:" << ounces << "\n";
    std::cout << "Cups:" << cups << "\n";
    std::cout << "Pints:" << pints << "\n";
    std::cout << "Gallons:" << gallons << "\n";
    std::cout << "Liters:" << liters << "\n";
    std::cout << "Cubic Inches:" << cubicInches << "\n";
    
    return 0;
}
