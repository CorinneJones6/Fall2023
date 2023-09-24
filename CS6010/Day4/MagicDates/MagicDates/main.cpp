//
//  main.cpp
//  MagicDates
//
//  Created by Corinne Jones on 8/24/23.
//

#include <iostream>
#include <string>


int main(int argc, const char * argv[]) {
    
    // insert code here...
    std::string inputDate;
    //user puts an input and it stores it
    std::cout << "Enter a date in format mm/dd/yyyy \n";
    std::cin >> inputDate;
    
    int monthPosition = inputDate.find("/");
    int monthNumber;
    
    int dayPosition = inputDate.find("/",monthPosition+1);
    int dayNumber;
    
    int yearPosition = inputDate.rfind ("/");
    int yearNumber;
    
    std::string month, monthDates, day, dayDates, year, yearDates, lastTwoDates, lastTwoNumber;
    
    
    monthDates = inputDate.substr(0,monthPosition);
    monthNumber = stoi(monthDates);
    
    dayDates = inputDate.substr(monthPosition+1,dayPosition-monthPosition-1);
    dayNumber = stoi(dayDates);
    
    yearDates = inputDate.substr(dayPosition+1, inputDate.length()-dayPosition-1);
    yearNumber = stoi(yearDates);
    
    lastTwoDates = inputDate.substr(dayPosition+3, inputDate.length()-dayPosition-1);
    lastTwoNumber = stoi(lastTwoDates);
    
    
    if (monthNumber<1 || monthNumber>12){
        std::cout<< "Invalid month";
    }
    else if (dayNumber<1 ||dayNumber>31){
        std::cout<< "Invalid day";
    }
    else if (yearNumber<1000||yearNumber>9999){
        std::cout<< "Invalid year";
    }
    
   

    
    else {

        if (stoi(lastTwoDates) == stoi(monthDates) * stoi(dayDates)){
            std::cout<< "This is a magic date";
        }
        else {
            std::cout<< "This is not a magic date"<<std::endl;
        }

        }
        return 0;

}
