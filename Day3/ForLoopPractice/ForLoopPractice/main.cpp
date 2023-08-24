//
//  main.cpp
//  ForLoopPractice
//
//  Created by Corinne Jones on 8/23/23.
//

#include <iostream>

int main(int argc, const char * argv[])





//"While" and "For" loop counting from 1-10

{
    int number;
    number = 0;

    // This is a "while" loop that prints the numbers 1-10
    while (number<10) {
        number = number +1;
        std::cout << number << '\n';
    }

    number = 1;

    //This is the "for" loop that prints the number 1-10
    for (number=1;number<=10;number=number+1){
        std::cout << number <<'\n';
    }

    /*For this example, I think that it doesn't really matter which loop that you use. You know how many times it is going to be done, so you could choose to do the "For" loop. However, the "while" loop is currently easier for me to understand.*/
}










//Enter two numbers and count every number between the two of them.
{

    int firstNumber, secondNumber;

    //prompts for first input
    std::cout<< "Please enter a whole number ";
    std::cin>> firstNumber;

    //prompts for second input
    std::cout << "Please enter another whole number ";
    std::cin >> secondNumber;


    std::cout << "\nSince I am sure you wnat to know, here are all the numbers between your two numbers\n";

    //first loop for when the first number is less than the second number
    if (firstNumber < secondNumber) {
        std::cout<< firstNumber << '\n';

        while (firstNumber<secondNumber) {
            firstNumber = firstNumber + 1;
            std::cout << firstNumber <<'\n';
        }}

    //second loop for when the second number is less than the second number
    else if (firstNumber > secondNumber) {
        std::cout<<secondNumber << '\n';

        while (firstNumber>secondNumber) {
            secondNumber = secondNumber + 1;
            std::cout <<secondNumber <<'\n';
        }

    }
}









//State all the odd numbers between 1-20
{
    int number;
    number=1;

    for (number=1;number<20;number=number+2) {
        std::cout << number << '\n';
    }


    {
        int number;
        number =1;

        //state all the odd numbers between 1-20 with an "if" statement
        if (number%2==1)
            for (number=1;number<20;number=number+2) {
                std::cout << number << '\n';
            }
    }
    /*using just a "for" is a lot easier because then it isn't imbedded in a "for"*/
}








//Enter positive numbers to add upp until one.
{
    int input = 0, sumAll = 0;

//output on the screen
    std::cout<<"Please enter positive numbers \n";

    // "do/while" loop - continue to do this thing while this other condition is being met.
    do {
    std::cin>>input;
        sumAll = sumAll + input;
    }

    while (input>0);

    //Based on how I wrote the code I was continuing add the negative number. I went ahead and subtracted the last input from the sum to ignore that number.
        sumAll = sumAll - input;

    std::cout<<"When you add these numbers together you get" << "\n" << sumAll;

}









//Print a multiplication table for the numbers 1 to 5
{

    
    for (int j=1; j<=5; j=j+1){
        std::cout << j << "x*: ";
        
        
        for (int i=1; i<=5; i=i+1){
            std::cout << i*j << " ";
            
        }
        std::cout << "\n";
    }


    }
    
    

