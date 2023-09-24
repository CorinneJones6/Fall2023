//
//  main.cpp
//  NumberRepresentations
//
//  Created by Corinne Jones on 9/6/23.
//

#include <iostream>
#include <cstdint>
#include <iomanip>
#include "Functs.hpp"
#include <fstream>

int main(int argc, const char * argv[]) {
    
    uint8_t uint8min=0x00;
    uint8_t uint8max=0xff;
    uint16_t uint16min=0x0000;
    uint16_t uint16max=0xffff;
    uint64_t uint64min=0x0000000000000000;
    uint64_t uint64max=0xffffffffffffffff;
    
    int8_t int8min=0x80;
    int8_t int8max=0x7f;
    int16_t int16min=0x8000;
    int16_t int16max=0x7fff;
    int64_t int64min=0x8000000000000000;
    int64_t int64max=0x7fffffffffffffff;
    
    uint8_t uint8minsub=0x00-1;
    uint8_t uint8maxadd=0xff+1;
    int8_t int8minsub=0x80-1;
    int8_t int8maxadd=0x7f+1;
    
    
    std::cout<<"Part 1"<<std::endl;

    std::cout<<"Size of char: "<<sizeof(char)<<std::endl;

    std::cout<<"Size of int: "<<sizeof(int)<<std::endl;

    std::cout<< "Size of float: "<<sizeof(float)<<std::endl;

    std::cout<<"Size of double: "<<sizeof(double)<<std::endl;

    std::cout<<"Size of int8_t: "<<sizeof(int8_t)<<std::endl;

    std::cout<<"Size of int16_t: "<<sizeof(int16_t)<<std::endl;

    std::cout<<"Size of uint8_t: "<<sizeof(uint8_t)<<std::endl;

    std::cout<<"Size of uint16_t: "<<sizeof(uint16_t)<<std::endl;

    std::cout<<"Min value of uint8_t: "<<+uint8min<<std::endl;

    std::cout<<"Max value of uint8_t: "<<+uint8max<<std::endl;

    std::cout<<"Min value of uint16_t: "<<+uint16min<<std::endl;

    std::cout<<"Max value of uint16_t: "<<+uint16max<<std::endl;

    std::cout<<"Min value of uint64_t: "<<+uint64min<<std::endl;

    std::cout<<"Max value of uint64_t: "<<+uint64max<<std::endl;

    std::cout<<"Min value of int8_t: "<<+int8min<<std::endl;

    std::cout<<"Max value of int8_t: "<<+int8max<<std::endl;

    std::cout<<"Min value of int16_t: "<<+int16min<<std::endl;

    std::cout<<"Max value of int16_t: "<<+int16max<<std::endl;

    std::cout<<"Min value of int64_t: "<<+int64min<<std::endl;

    std::cout<<"Max value of int64_t: "<<+int64max<<std::endl;



    /*The following code is when you subtract one from the minimum value and add one to the maximum value. In all cases, it swaps the minimum and maximum values. The 'undefined behavior sanitizer' didn't do anything when it was checked. */
    std::cout<<"Min value -1 of uint8_t: "<<+uint8minsub<<std::endl;

    std::cout<<"Max value +1 of uint8_t: "<<+uint8maxadd<<std::endl;

    std::cout<<"Min value -1 of int8_t: "<<+int8minsub<<std::endl;

    std::cout<<"Max value +1of int8_t: "<<+int8maxadd<<std::endl;
    
    std::cout<<"Part 2"<<std::endl;
    
    double result1=.1+.2;

    std::cout<<std::setprecision(18)<<result1<<std::endl;
    
//    assert((result1==.3) && ".1 + .2 IS NOT .3 sucks to suck");
    
    /* When you add .1+.2 and store the result as a double, the last two numbers are NOT 00 as they should be. When you store .1+.2 as a float, all the numbers after the first 8 0's are random numbers, making it less accurate*/
    float result2=.1+.2;
    
    std::cout<<result2<<std::endl;
    
//    assert((result2==.3) && ".1 + .2 IS NOT .3 sucks to suck");
    
    bool approxEqualsAns;
  
    
    approxEqualsAns=approxEquals (.1, .2, .31);
    
    
    std::cout<<"Part 3"<<std::endl;
    
    char c;
    
    std::ifstream inputFile ("UTF-8-demo.txt");
    
    if (inputFile.fail()){
        std::cout<<"Failed to open file \n";
        exit(1);
    }
    int count1=0;
    int count2=0;
    
    while( inputFile >> c ){
        if (c>=0&&c<=127){
            count1++;
        }
        else{
            count2++;
        }

        std::cout<<c<<std::endl;

    }
    std::cout<< "ASCII number: "<<count1<<std::endl;
    std::cout<< "Unicode number: "<<count2<<std::endl;
    
/* I see that each of the characters are listed on a line after the input. The integers are on their own line and those seem . This does match my expectations. */
    
    
    return 0;
}
