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

/*This is a constructor for when the user does not specify a capacity. It assigns generic values.*/
template<typename T>
MyVector<T>::MyVector(){
    size_=0;
    capacity_=10;
    data_=new T[capacity_];
}

//This is a constructor for when they specify the capacity.
template<typename T>
MyVector<T>::MyVector (size_t capacity){
    if (capacity>0){
        size_=0;
        capacity_=capacity;
        data_=new T[capacity_];
    }
}

//This is a destructor.
template<typename T>
MyVector<T>::~MyVector(){
    size_=0;
    capacity_=0;
    delete [] data_;
    data_=nullptr;
}

//This is the copy constructor. It is part 1/3 in the "rule of three."
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

//This is a destructor that is part 2/3 in the "rule of three."
//template<typename T>
//void MyVector<T>::destructor(){
//    size_=0;
//    capacity_=0;
//    delete [] data_;
//
//}

//This is a function that creates a vector.
//template<typename T>
//MyVector<T> MyVector<T>::makeVector(size_t capacity){
//    size_=capacity;
//    capacity_=2*size_;
//    data_=new T[capacity_];
//    return *this;
//}

/*If the size reaches capacity, it will initiate the growVector. If not, it treats the pointer as if it was an array and puts it as value. Then it increments the size. This copies the stack val to the heap data.*/
template<typename T>
void MyVector<T>::pushBack (size_t val){
    if (size_+1 >= capacity_){
        growVector();
    }
    data_ [size_]=val;
    size_++;
}

/*This method checks that size has to be over 0. Then, if so, it shrinks the size by one.*/
template<typename T>
void MyVector<T>::popBack(){
    if (size_>0) {
        size_--;
    }
}

//This method gets the value at a specified index.
template<typename T>
size_t MyVector<T>::getValue(size_t index){
        return data_ [index];
}

//This method sets the value at a specified index.
template<typename T>
void MyVector<T>::setValue(size_t index, T newValue){
    if (index>=0&&index<capacity_){
       data_ [index]=newValue;
    }
}

//This method returns the size of a vector.
template<typename T>
size_t MyVector<T>::size() const{
    return size_;
}
//This method returns the capacity of a vector.
template<typename T>
size_t MyVector<T>::capacity() const{
    return capacity_;
}
/*If this function is called, typically as a check in another function where the size is approaching the capacity, this will increase the capacity. 1. create a new array on the heap twice the capacity of data 2. Copy data into temp 3. delete data 4. Point data at whatever temp is pointing at 5. Set temp to null 6. update size and capacity*/
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
}

//This is part 3/3 in the rule of three. ADD A CHECK FOR SIZE>0
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

/*This overloads the [] so that you can use them to get the value at a specified index WHEN you might be changing the value at that index.*/
template<typename T>
T& MyVector<T>::operator [] (size_t index){
    
    assert (index < size_&& "Out of bounds in operator[ ]!" );
    return data_[index];
}

/*This overloads the [] so that you can use them to get the value at a specified index WHEN you aren't going to change the value at that index.*/
template<typename T>
const T& MyVector<T>::operator [] (size_t index)const{
    assert (index < size_ && "Out of bounds in operator[]!" );
    return data_ [index];
}

/*This overloads the double equal operator ==. This enters the if statement, which says that if they are not the same size, return false. If they are the same size, it enters the for loop. If ever the values at the index are not the same, it returns false. If it runs through that whole loop it will go down and return true. */
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

//This is the not equal operator. If the two values are not equal, than they return true.
template<typename T>
bool MyVector<T>::operator!=(const MyVector& rhs)const{
    return !(*this==rhs);
}

/*This overloads the less than operator. It has a check to look at the sizes and returns less than if the size is smaller than rhs. Then, if the size is bigger, it returns false. If they are the same size then it eneters a for loop. If they are ever not the same size, then it checks if the falue is less than and returns true. If the value is greater than, it will return false. If it runs through the whole loop it will return false. ADD THE SAME CHECK STATEMENTS AS ABOVE WHERE YOU RETURN IF NOT THE SAME SIZE*/
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

/*This overloads the operator <=. If the two values are equal OR less than, it returns true.*/
template<typename T>
bool MyVector<T>::operator<=(const MyVector& rhs)const{
    return (*this==rhs||*this<rhs);
}

/*This overloads the operator >. If the two numbers are NOT equal AND NOT less than, then it returns true. */
template<typename T>
bool MyVector<T>::operator>(const MyVector& rhs)const{
    return (!(*this==rhs) && !(*this<rhs));
}

/*This overloads the operator >=. If the two values are equal OR greater than, it returns true.*/
template<typename T>
bool MyVector<T>::operator>=(const MyVector& rhs)const{
    return (*this==rhs||*this>rhs);
}

#endif /* DiyVectorFuncts_hpp */
