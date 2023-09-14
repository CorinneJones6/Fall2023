//
//  DiyVectorFuncts.hpp
//  DiyVector
//
//  Created by Corinne Jones on 9/13/23.
//

#ifndef DiyVectorFuncts_hpp
#define DiyVectorFuncts_hpp

#include <stdio.h>
#include <iostream>
#include <cassert>

template<typename T>    
class MyVector {
public:
    //these are constructors and destructors
    MyVector(); //constructor
    MyVector (size_t capacity); //constructor
    ~MyVector(); //destructor
    MyVector (const MyVector& rhs); //copy constructor
    
    MyVector<T> makeVector(size_t capacity);
    void pushBack (size_t val);
    void popBack();
    size_t getValue(size_t index);
    void setValue(size_t index, T newValue);
    size_t size() const;
    size_t capacity() const;
    
    //these overload
    MyVector<T>& operator=(const MyVector& rhs);
    T& operator [] (size_t index);
    const T& operator [] (size_t index)const;
    bool operator==(const MyVector& rhs)const;
    bool operator!=(const MyVector& rhs)const;
    bool operator<(const MyVector& rhs)const;
    bool operator<=(const MyVector& rhs)const;
    bool operator>(const MyVector& rhs)const;
    bool operator>=(const MyVector& rhs)const;
    
    
private:
    void destructor(); //renamed freeVector
    void growVector();
    T *data_;
    size_t size_, capacity_;
};

template<typename T>
MyVector<T>::MyVector(){
    size_=0;
    capacity_=10;
    data_=new T[capacity_];
}

template<typename T>
MyVector<T>::MyVector (size_t capacity){
    if (capacity>0){
        size_=0;
        capacity_=capacity;
        data_=new T[capacity_];
    }
}

//Set the same as freeVector, is a destructor.
template<typename T>
MyVector<T>::~MyVector(){
    size_=0;
    capacity_=0;
    delete [] data_;
}

//copy constructor, part 1/3
template<typename T>
MyVector<T>::MyVector (const MyVector& rhs){
    if (this != &rhs) {
        delete [] data_;
        size_=rhs.size();
        capacity_ =2*size_;
        if (capacity_>0) {
        data_ = new T[capacity_];
        for (size_t i=0; i<rhs.size(); i++) {
            data_[i]=rhs[i];
            }
        }
    }
}

//part 2/3
template<typename T>
void MyVector<T>::destructor(){
    size_=0;
    capacity_=0;
    delete [] data_;
    
}

//This is a function that creates a vector.
template<typename T>
MyVector<T> MyVector<T>::makeVector(size_t capacity){
    size_=capacity;
    capacity_=2*size_;
    data_=new T[capacity_];
    return *this;
}

//If the size reaches capacity, it will initiate the growVector. If not, it treats the pointer as if it was an array and puts it as value. Then it increments the size. This copies the stack val to the heap data.
template<typename T>
void MyVector<T>::pushBack (size_t val){
    if (size_+1 >= capacity_){
        growVector();
    }
    data_ [size_]=val;
    size_++;
}
//This has the check that size has to be over 0. Then, if so, it shrinks the size by one.
template<typename T>
void MyVector<T>::popBack(){
    if (size_>0) {
        size_--;
    }
}

template<typename T>
size_t MyVector<T>::getValue(size_t index){
        return data_ [index];
}

template<typename T>
void MyVector<T>::setValue(size_t index, T newValue){
    if (index>=0&&index<capacity_){
       data_ [index]=newValue;
    }
}

//returns the size of a vector
template<typename T>
size_t MyVector<T>::size() const{
    return size_;
}
//returns the capacity of a vector
template<typename T>
size_t MyVector<T>::capacity() const{
    return capacity_;
}
//If this function is called, typically as a check in another function where the size is approaching the capacity, this will increase the capacity. 1. create a new array on the heap twice the capacity of data 2. Copy data into temp 3. delete data 4. Point data at whatever temp is pointing at 5. Set temp to null 6. update size and capacity
template<typename T>
void MyVector<T>::growVector(){
    T* temp =new T [capacity_*2];
    for (int i=0; i<size_; i++){
        temp [i]=data_ [i];
    }
    delete[] data_;
    data_=temp;
    temp=nullptr;
    capacity_*=2 ;
    size_++;
}

//part 3/3
template<typename T>
MyVector<T>& MyVector<T>::operator=(const MyVector& rhs){
    if (this != &rhs) {
    delete [] data_;
    size_=rhs.size();
    capacity_ =2*size_;
    if (capacity_>0) {
    data_ = new T[capacity_];
    for (size_t i=0; i<rhs.size(); i++) {
        data_[i]=rhs[i];
            }
        }
    }
return *this;
}

template<typename T>
T& MyVector<T>::operator [] (size_t index){
    
    assert (index < size_&& "Out of bounds in operator[ ]!" );
    return data_[index];
}

template<typename T>
const T& MyVector<T>::operator [] (size_t index)const{
    assert (index < size_ && "Out of bounds in operator[]!" );
    return data_ [index];
}

template<typename T>
bool MyVector<T>::operator ==(const MyVector<T>& rhs)const{
    if (size_!=rhs.size()){
        return false;
    }
    else {
        for (size_t i=0; i<rhs.size(); i++){
            if (data_[i]!=rhs[i]){
                return false;
            }
        }
    }
    return true;
}

template<typename T>
bool MyVector<T>::operator!=(const MyVector& rhs)const{
    return !(*this==rhs);
}

//put in a check if they have different sizes.
template<typename T>
bool MyVector<T>::operator<(const MyVector &rhs)const{
    if (size_<rhs.size()) {
        return true;
    }
    if (size_>rhs.size()){
        return false;
    }
    for (size_t i=0; i<rhs.size(); i++){
        if (data_[i]!=rhs[i]){
            if (data_[i]<rhs[i]) {
                return true;
            }
            else{
                return false;
            }
        }
    }
    return false;
}

template<typename T>
bool MyVector<T>::operator<=(const MyVector& rhs)const{
    return (*this==rhs||*this<rhs);
}

//put in a check
template<typename T>
bool MyVector<T>::operator>(const MyVector& rhs)const{
    return (!(*this==rhs) && !(*this<rhs));
}

template<typename T>
bool MyVector<T>::operator>=(const MyVector& rhs)const{
    return (*this==rhs||*this>rhs);
}

#endif /* DiyVectorFuncts_hpp */
