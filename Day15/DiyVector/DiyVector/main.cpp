//
//  main.cpp
//  DiyVector
//
//  Created by Corinne Jones on 9/12/23.
//

#include <iostream>
#include "DiyVectorFuncts.hpp"
#include "TestFuncts.hpp"


void testDestructor () {
    MyVector v2;
    v2.pushBack(5);
    v2.pushBack(20);
    
}

int main(int argc, const char * argv[]) {
    
    TestMyVec(); 
    
    
    return 0;
}
