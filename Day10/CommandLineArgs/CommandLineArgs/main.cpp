//
//  main.cpp
//  CommandLineArgs
//
//  Created by Corinne Jones on 9/1/23.
//

#include <iostream>

// arg[0] is./main

int main(int argc, const char * argv[]) {
    
    for (int i=0; i < argc; i++) {
        std::cout<<argv[i] << std::endl;
    }
    
    return 0;
}
