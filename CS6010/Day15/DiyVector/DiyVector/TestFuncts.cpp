//
//  TestFuncts.cpp
//  DiyVector
//
//  Created by Corinne Jones on 9/13/23.
//

#include "TestFuncts.hpp"
#include "DiyVectorFuncts.hpp"
#include <cassert>
#include <string>

void TestMyVec(){
    
    //This creates a vector that it will put through the tests.
    MyVector<int> v1 (10);
    v1.pushBack(0);
    v1.pushBack(1);
    v1.pushBack(10);
    v1.pushBack(25);
    v1.pushBack(105);
    
    MyVector<int> v2(10);
    v2.pushBack(1);
    v2.pushBack(2);
    v2.pushBack(3);
    v2.pushBack(4);
    v2.pushBack(5);
    
    MyVector<int> v3(10);
    v3.pushBack(1);
    v3.pushBack(1);
    v3.pushBack(1);
    v3.pushBack(1);
    v3.pushBack(1);
    
    MyVector<int> v4(10);
    v4.pushBack(1);
    v4.pushBack(2);
    v4.pushBack(3);
    v4.pushBack(4);
    v4.pushBack(5);
    
    //This tests the pushBack and get value.
    assert( (v1.getValue(0)==0)|| (v1.getValue(1)==1) || (v1.getValue(2)==10) || (v1.getValue(3)==25) || (v1.getValue(4==105)) && "Test 1 Failed");
    
    v1.popBack();
    
    //This tests the pop back.
    assert( (v1.size()==4) || (v1.capacity()==10) || (v1.getValue(4)==0) && "Test 2 Failed");

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
    assert( (v1.size()==9) || (v1.capacity()==20) && "Test 4 Failed");
    

    //These test the overloaded operators.
    assert( (v2==v4) || (v2!=v3) || (v2>v3) || (v3<v2) || (v2<=v4) || (v2>=v4) || (v2<=v4) || (v2<v1) || (v1>v2) && "Test 5 failed");
    
    MyVector<double> v5 (2);
    v5.pushBack(1.1);
    v5.pushBack(1.2);
    v5.pushBack(1.3);
    
    /*This tests that MyVector works for doubles. It combines the size, capacity, and getValue.*/
    assert ( (v5.size()==3) || (v5.capacity()==8) || (v5.getValue(1)== 1.2) && "Test 6 Failed");

    MyVector<char> v6 (5);
    v6.pushBack('h');
    v6.pushBack('e');
    v6.pushBack('l');
    v6.pushBack('l');
    v6.pushBack('o');
    
    /*This tests that MyVector works for chars. It combines the size, capacity, and getValue.*/
    assert( (v6.size()==5) || (v6.capacity()==10) || (v6.getValue(4)== 'o') && "Test 7 Failed");
}
