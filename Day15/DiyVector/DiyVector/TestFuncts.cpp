//
//  TestFuncts.cpp
//  DiyVector
//
//  Created by Corinne Jones on 9/13/23.
//

#include "TestFuncts.hpp"
#include "DiyVectorFuncts.hpp"
#include <cassert>

void TestMyVec(){
    //This creates a vector that it will put through the tests.
    MyVector v1 (10);
    
    v1.pushBack(0);
    v1.pushBack(1);
    v1.pushBack(10);
    v1.pushBack(25);
    v1.pushBack(105);
    
    //This tests the pushBack and get value.
    assert( (v1.getValue(0)==0)|| (v1.getValue(1)==1) || (v1.getValue(2)==10) || (v1.getValue(3)==25) || (v1.getValue(4)==105) && "Test 1 Failed");
    
    v1.popBack();
    
    //This tests the pop back.
    assert( (v1.getSize()==4) || (v1.getCapacity()==10) || (v1.getValue(4)==0) && "Test 2 Failed");

    

    v1.setValue(0,1);
    v1.setValue(1,2);
    v1.setValue(2,3);
    v1.setValue(3,4);
    
    //This testes the setValue.
    assert( (v1.getValue(0)==1)|| (v1.getValue(1)==2) || (v1.getValue(2)==3) || (v1.getValue(3)==4)&& "Test 3 Failed");
    
    
    v1.pushBack(5);
    v1.pushBack(6);
    v1.pushBack(7);
    v1.pushBack(8);
    v1.pushBack(9);

    //This tests the grow vector. 
    assert( (v1.getSize()==9) || (v1.getCapacity()==20) && "Test 4 Failed");
    

    
    
    
}
