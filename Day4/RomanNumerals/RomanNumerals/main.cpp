//
//  main.cpp
//  RomanNumerals
//
//  Created by Corinne Jones on 8/24/23.
//

#include <iostream>
#include <string>

int main(int argc, const char * argv[]) {
    
    int inputNumber;
    
    // This prompts for input and then stores that as an integer
    std::cout << "Enter a number: \n";
    std::cin >> inputNumber;
    
    //if the input is valid, it starts this loop.
    if (inputNumber<=9999) {
        
        std::cout<< "The Roman Numeral Version: ";

    //This starts the while loop. If the number is greater than or equal too the number listed, it eneters the loop. It then prints the letter and subtracts the input by a number. If it still meets the criteria, it runs the loop again. If not, it enters a new loop. 
    while (inputNumber>=1000){
        
        std::cout << "M";
        inputNumber-=1000;
        }
        
    while (inputNumber>=900){
            
            std::cout << "CM";
            inputNumber-=900;
        }
        
    while (inputNumber>=500){
                
                std::cout << "D";
                inputNumber-=500;
        }
        
    while (inputNumber>=400){
                    
                    std::cout << "CD";
                    inputNumber-=400;
        }
        
    while (inputNumber>=100){
                        
                        std::cout << "C";
                        inputNumber-=100;
        }
        
    while (inputNumber>=90){
                        
                        std::cout << "XC";
                        inputNumber-=90;
        }
        
    while (inputNumber>=50){
                        
                        std::cout << "L";
                        inputNumber-=50;
        }
        
    while (inputNumber>=40){
                            
                            std::cout << "XL";
                            inputNumber-=40;
        }
        
    while (inputNumber>=10){
                            
                            std::cout << "X";
                            inputNumber-=10;
        }
        
    while (inputNumber>=9){
                            
                            std::cout << "IX";
                            inputNumber-=9;
        }
        
    while (inputNumber>=5){
                            
                            std::cout << "V";
                            inputNumber-=5;
        }
        
    while (inputNumber>=4){
                            
                            std::cout << "IV";
                            inputNumber-=4;
        }
        
    while (inputNumber>=1){
                            
                            std::cout << "I";
                            inputNumber-=1;
        }
        
    }
    
    //if the input is anything else wacky, it prompts for re-entry
    else {
        std::cout<<"Invalid entry, try again"<<std::endl;
    }
    std::cout<< "\n";
    
    return 0;
}
