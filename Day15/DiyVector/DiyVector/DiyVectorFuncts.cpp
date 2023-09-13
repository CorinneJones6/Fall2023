//
//  DiyVectorFuncts.cpp
//  DiyVector
//
//  Created by Corinne Jones on 9/13/23.
//

#include "DiyVectorFuncts.hpp"
#include <iostream>

MyVector::MyVector(){
    size_=0;
    capacity_=10;
    data_=new int[capacity_];
}

MyVector::MyVector (size_t capacity){
    if (capacity>0){
        size_=0;
        capacity_=capacity;
        data_=new int[capacity_];
    }
}

//Set the same as freeVector.
MyVector::~MyVector(){
    size_=0;
    capacity_=0;
    delete [] data_;
}

//This is a function that creates a vector.
MyVector MyVector::makeVector(size_t capacity){
    size_=capacity;
    capacity_=2*size_;
    data_=new int [capacity_];
    return *this;
}


void MyVector::freeVector(){
    size_=0;
    capacity_=0;
    delete [] data_;
    
}
//If the size reaches capacity, it will initiate the growVector. If not, it treats the pointer as if it was an array and puts it as value. Then it increments the size. This copies the stack val to the heap data.
void MyVector::pushBack (int val){
    if (size_+1 >= capacity_){
        growVector();
    }
    data_ [size_]=val;
    size_++;
}
//This has the check that size has to be over 0. Then, if so, it shrinks the size by one.
void MyVector::popBack(){
    if (size_>0) {
        size_--;
    }
}

size_t MyVector::getValue(size_t index){
        return data_ [index];
}

void MyVector::setValue(size_t index, int newValue){
    if (index>=0&&index<capacity_){
       data_ [index]=newValue;
    }
}

//returns the size of a vector
size_t MyVector::getSize() const{
    return size_;
}
//returns the capacity of a vector
size_t MyVector::getCapacity() const{
    return capacity_;
}
//If this function is called, typically as a check in another function where the size is approaching the capacity, this will increase the capacity. 1. create a new array on the heap twice the capacity of data 2. Copy data into temp 3. delete data 4. Point data at whatever temp is pointing at 5. Set temp to null 6. update size and capacity
void MyVector::growVector(){
    int* temp =new int [capacity_*2];
    for (int i=0; i<size_; i++){
        temp [i]=data_ [i];
    }
    delete[] data_;
    data_=temp;
    temp=nullptr;
    capacity_*=2 ;
    size_++;
}
