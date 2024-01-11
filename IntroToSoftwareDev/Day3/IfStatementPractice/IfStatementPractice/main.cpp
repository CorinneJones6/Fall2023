//
//  main.cpp
//  IfStatementPractice
//
//  Created by Corinne Jones on 8/23/23.
//

#include <iostream>

int main(int argc, const char * argv[])

{

    int userAge;
    // Prompt for age + user input
    std::cout << "What is your age?\n";
    std::cin >> userAge;

    // if statement for voting age
    if (userAge >=18) {
        std::cout << "Congrats, you are voting age!\n";
    }
    else {
        std::cout << "You are not yet voting age :(\n";
    }

    //if statement for senate age
    if (userAge >=30) {
        std::cout << "Congrats, you are able to join the Senate!\n";
    }
    else {
        std::cout << "You are not able to join the Senate :(\n";
    }

    //if statement for stating generation they are a part of
    if (userAge >=80){
        std::cout << "You are a part of the greatest Generation :(\n";
    }
    else if (userAge <80 && userAge>= 60) {
        std::cout <<"You are a baby boomer :(\n";
    }
    else if (userAge <60 && userAge>= 40) {
        std::cout <<"You are a part of GenX :(\n";
    }
    else if (userAge <40 && userAge>= 20) {
        std::cout <<"Congrats, you are a millennial!\n";
    }
    else {
        std::cout << "You are an iKid :)\n";
    }

    //Start of part two, defining characters and bools
   char weekdayResponse, holidayResponse, youngKids;
    bool isWeekday, isHoliday, hasYoungKids;
    
    //Weekday prompt
    std::cout << "\nIs it a weekday? type y/n ";
    std::cin >> weekdayResponse;
    
    //assigns bool to weekdayResponse
    if (weekdayResponse=='y'||weekdayResponse=='Y') {
        isWeekday=true;
    }
    else {
        isWeekday=false;
    }
    //holiday prompt
    std::cout << "\nIs it a holiday? type y/n ";
    std::cin >> holidayResponse;
    
    //assigns bool to holidayResponse
    if (holidayResponse=='y'||holidayResponse=='Y') {
        isHoliday=true;
    }
    else {
        isHoliday=false;
    }
    //kids prompt
    std::cout << "\nDo  you have young kids? type y/n ";
    std::cin >> youngKids;
    
    //assigns bool to youngKids
    if (youngKids=='y'||youngKids=='Y') {
       hasYoungKids=true;
    }
    else {
        hasYoungKids=false;
    }
    
    //starts the if statement for letting them know if they can sleep in
    if (hasYoungKids) {
        std::cout << "\nYou can't sleep in";
    }
    
    else if (isHoliday) {
        std::cout << "\n You can sleep in";
    }
    
    else if (isWeekday&&isHoliday) {
        std::cout << "/n You can sleep in";
    }
    
    else if (isWeekday) {
        std::cout << "/n You can't sleep in";
    }
        
        
        
        //end of program YAYAYAYAYAY
        return 0;
    }




