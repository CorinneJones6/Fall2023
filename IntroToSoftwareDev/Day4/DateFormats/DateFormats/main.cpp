//
//  main.cpp
//  DateFormats
//Created by Sarah Bateman and Corinne Jones

#include <iostream>
#include <string>

int main(int argc, const char * argv[]) {
//
//
    std::string inputDate;
//
//   //ask the user for a date
    
    std::cout << "Enter a date in format mm/dd/yyyy \n";
    std::cin >> inputDate;
    
    /*finds the month numbers, day numbers, and year numbers and assigns it as an integer.*/
    
    int monthPosition = inputDate.find("/");
    int monthNumber;
    
    int dayPosition = inputDate.find("/",monthPosition+1);
    int dayNumber;
    
    int yearPosition = inputDate.rfind ("/");
    int yearNumber;
    
    //creates the strings that will be used in the rest of the code
    
    std::string month, monthDates, day, dayDates, year, yearDates;
    
    //stores the month, days, and years as integers
    
    monthDates = inputDate.substr(0,monthPosition);
    monthNumber = stoi(monthDates);
    
    dayDates = inputDate.substr(monthPosition+1,dayPosition-monthPosition-1);
    dayNumber = stoi(dayDates);
    
    yearDates = inputDate.substr(dayPosition+1, inputDate.length()-dayPosition-1);
    yearNumber = stoi(yearDates);
    
    //produces as invalid month, day, year based on what they input
    
    if (monthNumber<1 || monthNumber>12){
        std::cout<< "Invalid month";
    }
    else if (dayNumber<1 ||dayNumber>31){
        std::cout<< "Invalid day";
    }
    else if (yearNumber<1000||yearNumber>9999){
        std::cout<< "Invalid year";
    }
    
    //writes out the month
    else {
        
        if(monthNumber == 1){
            month = "January";
        }
        else if(monthNumber == 2){
            month = "February";
        }
        else if(monthNumber == 3){
            month = "March";
        }
        else if(monthNumber == 4){
            month = "April";
        }
        else if(monthNumber == 5){
            month = "May";
        }
        else if(monthNumber == 6){
            month = "June";
        }
        else if(monthNumber == 7){
            month = "July";
        }
        else if(monthNumber == 8){
            month = "August";
        }
        else if(monthNumber == 9){
            month = "September";
        }
        else if(monthNumber == 10){
            month = "October";
        }
        else if(monthNumber == 11){
            month = "November";
        }
        else if(monthNumber == 12){
            month = "December";
        }
        
        //prints the month, day, and year
        std::cout << month << " " << dayDates << ", "<<yearDates<<std::endl;

    }
    
    
    
    return 0;
}
