//
//  main.cpp
//  PointerPractice
//
//  Created by Corinne Jones on 9/12/23.
//

#include <iostream>

struct MyVector {
    double* data;
    int size;
    int capacity;
};

int arrayModSum (MyVector& someVar){
    int count=0;
    for (int i=0; i<someVar.size; i++) {
        someVar.data[i]=someVar.data[i]+1;
        count+=someVar.data[i];
    }
    return count;
};

void growMyVector (MyVector& someVar){
    if (someVar.size==someVar.capacity){
        int newSize=someVar.size*2;
        double *newData =new double [newSize];
        for (int i=0; i<newSize; i++) {
            if (i<someVar.size){
                newData[i]=someVar.data[i];
            }
            else{
                newData[i]=-1;
            }
        }
        someVar.capacity=newSize;
        delete [] someVar.data;
        someVar.data=newData;
    }
};

int main(int argc, const char * argv[]) {
    MyVector vec1;
    int vecSize;
    
    std::cout<<"Enter the number of contents in an array: \n";
    std::cin>>vecSize;
    
    vec1.data=new double [vecSize];
    vec1.size=vecSize;
    vec1.capacity=vec1.size;
    
    for (int i=0; i<vecSize; i++) {
        vec1.data[i]=1.0;
    }
    
    std::cout<<"\nHere are the contents of the array: ";
    for (int i=0; i<vecSize; i++) {
        std::cout<<vec1.data[i];
    }
    
    std::cout<<"\n"<<arrayModSum(vec1);
    
    std::cout<<"\nSize: "<<vec1.size;
    std::cout<<"\nCapacity: "<<vec1.capacity;
    
    growMyVector(vec1);
    
    std::cout<< "\nSize after growth: "<<vec1.size;
    std::cout<< "\nCapacity after growth: "<<vec1.capacity<<"\n";
    
    
    
    
    return 0;
}
