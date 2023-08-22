//
//  main.cpp
//  GiveChange
//
//  Created by Corinne Jones on 8/22/23.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    // insert code here...
    int itemPrice, paidAmount, differenceAmount, differenceQuarters, differenceDimes,differenceNickels, differencePennies;
    
    std::cout << "Enter item price \n";
    std::cin >> itemPrice;
    
    std::cout << "Enter amount paid \n";
    std::cin >> paidAmount;
    
    std::cout << "Change: ";
    differenceAmount = paidAmount-itemPrice;
    std::cout << differenceAmount;
    
    //calculate the quarters
    differenceQuarters = differenceAmount/25;
    std::cout << "\nQuarters " << differenceQuarters << "\n" ;
    
    //calculate the dimes
    differenceDimes = (differenceAmount-(differenceQuarters*25))/10;
    std::cout << "Dimes " <<differenceDimes << "\n";
    
    //calculate the nickels
    differenceNickels = (differenceAmount-(differenceQuarters*25)-(differenceDimes*10))/5;
    std::cout << "Nickels " <<differenceNickels << "\n";
    
    //calculate the pennies
    differencePennies = differenceAmount-(differenceQuarters*25)-(differenceDimes*10)-(differenceNickels*5);
    std::cout << "Pennies " <<differencePennies << "\n";
    
    //end prompt
    std::cout << "Thank you and have a great day!\n";
    return 0;
}
