//
//  main.cpp
//  FunctionPractice
//
//  Created by Corinne Jones on 8/25/23.
//
#include <iostream>
#include <cmath>
#include <string>

//This float calculates "C*2" within the hypotenuse equation.

float hypotenuseSideLength(float a,float b) {
    
    return (a*a + b*b);
}

float xVelocity (float a, float b) {
    return a * std::cos(b);
}

float yVelocity (float a, float b) {
    
    return a * std::sin(b);
}


bool isCapitolized(std::string sentence) {
    
    if (sentence[0]>=65&&sentence[0]<=90) {
        return true;
    }
    
    else {
        return false;
    }
}
    
    
    
std::string boolToString(bool userInput) {

    if (userInput==true) {
        
        return "true";
    }

    else {
        return "false";
    }

}
    
bool isPrime (int numberToCheck) {
    
    for (int count=1; count<numberToCheck/2; count++) {
        
        if (numberToCheck%count==0)
            
        return false;
    }
    return true;
        
}


int main(int argc, const char * argv[]) {
    
    //asigns the floats
    float a, b, c, d;
    std::string userInputSentence;
    bool startsWithCapitol;
    

    //PART 1
    //Start of part a
    std::cout << "Please enter one side of a right-angle side length: ";
    std::cin >> a;

    std::cout << "Please enter another side of a right-angle side length: ";
    std::cin>> b;

    c = std::sqrt(hypotenuseSideLength(a,b));

    std::cout << "The hypotenuse of your triangle is: ";
    std::cout << c << std::endl;




    //Start of part b
    std::cout << "Please input the speed you are going: ";
    std::cin >> a;

    std::cout << "Please input the angle you are going in radians: ";
    std::cin >> b;

    c= xVelocity (a,b);

    d= yVelocity (a,b);

    std::cout << "The X Velocity is: "<<c<<std::endl;
    std::cout<< "The Y Velocity is: "<<d<<std::endl;



    //Start of part c; there are two functions being called
    {
        std::time_t result = std::time(nullptr);
        std::cout << std::asctime(std::localtime(&result))
                  << result << " seconds since the Epoch\n";
    }


    //PART 2

    /* If you refer to part a, I already put in a function that allows you not to read from std::cin */

    /* It would be difficult to write one function that does everything for this because it is difficult */

    
    
    std::cout << "Please enter a sentence"<<std::endl;
    std::cin>> userInputSentence;
    
    if (isCapitolized (userInputSentence)) {
      
        std::cout << "This sentence starts with a capitol letter"<<std::endl;
    }
    
    else {
        
        std::cout << "This sentence does not start with a capitol letter\n";
    }
    

    //boolToString
    bool b2 = isCapitolized(userInputSentence);
        
        std::string result = boolToString(b2);
        
        std::cout<< "This sentence is "<<result<<std::endl;
    
    
    //isPrime
    
    bool isPrimeNumber;
    int i;
    
    std::cout<< "Please enter a whole number "<< std::endl;
    std::cin>> i;
    
    isPrimeNumber=isPrime(i);
    
    if (isPrimeNumber == true) {
        
        std::cout<< i <<" is a prime number\n";
    }
    
    else {
        std::cout<< i <<" is not a prime number\n";
    }

    return 0;
        
    
 
}
