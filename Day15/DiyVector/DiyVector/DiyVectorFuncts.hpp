//
//  DiyVectorFuncts.hpp
//  DiyVector
//
//  Created by Corinne Jones on 9/13/23.
//

#ifndef DiyVectorFuncts_hpp
#define DiyVectorFuncts_hpp

#include <stdio.h>

class MyVector {
public:
    MyVector(); //constructor
    MyVector (size_t capacity); //constructor
    ~MyVector(); //destructor
    
    
    
    MyVector makeVector(size_t capacity);
    void pushBack (int val);
    void popBack();
    size_t getValue(size_t index);
    void setValue(size_t index, int newValue);
    size_t getSize() const;
    size_t getCapacity() const;
    
    
    
private:
    void freeVector();
    void growVector();
    int *data_;
    size_t size_, capacity_;
};


#endif /* DiyVectorFuncts_hpp */
