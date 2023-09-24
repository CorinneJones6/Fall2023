//
//  main.cpp
//  PoliStructs
//
//  Created by Corinne Jones on 8/29/23.
//
#include "PoliFuncts.hpp"
#include <iostream>
#include <string>
#include <vector>
#include <cmath>
void ErrorExit( std::string message )
{
  std::cerr << "Failed test: " << message << std::endl;
  exit(1); // Causes the entire program to exit.
}

int main(int argc, const char * argv[]) {
  
    //This is where I form a struct vector that I pass through the function to test it.
    Politician p1 {"Name1", "Cpluser", "Federal"};
    Politician p2 {"Name2", "Cpluser", "Federal"};
    Politician p3 {"Name3", "Cpluser", "State"};
    Politician p4 {"Name4", "Javacan", "State"};
    Politician p5 {"Name5", "Javacan", "Federal" };
    std::vector <Politician> allPoliticians = {p1, p2, p3, p4, p5};
    
    //Here I create some variables by assigning the answer of the functions to them.
    std::vector<Politician>javacanAns;
    std::vector<Politician>federalCplusersAns;
    javacanAns = Javacans(allPoliticians);
    federalCplusersAns = federalCplusers(allPoliticians);
    
    //This prints out the Javacans
    std::cout<<"Javacans: ";
    for (Politician p:javacanAns){
        std::cout<<p.name<<" ";
    }
    //This prints out the cplusers + federal
    std::cout<<std::endl<<"Cplusers and federal: ";
    for (Politician p:federalCplusersAns){
        std::cout<<p.name<<" ";
    }
    
    //This is one of my tests. It goes in if the size is equal to two and makes sure that all of the part affiliations are "Javacan." If it is not Javacan it sends an error test.
    if (javacanAns.size()==2) {
        for (Politician p:javacanAns) {
            if (p.partyAffiliation!="Javacan") {
                ErrorExit("Contains ()-test 1");
            }
        }
    }
    
    //This is my other test. It goes in and says if it is not a cpluser then it sents an exit code.
    if (federalCplusersAns.size()==2) {
        for (Politician p:federalCplusersAns) {
            if (p.partyAffiliation!="Cpluser") {
                ErrorExit("Contains ()-test 2");
            }
        }
    }
    
    //If all tests pass, this is what comes up. 
    std::cout << std::endl<<"All tests passed!\n";
    
    return 0;
}
