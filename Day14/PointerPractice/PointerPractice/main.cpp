//
//  main.cpp
//  PointerPractice
//
//  Created by Corinne Jones on 9/11/23.
//

#include <iostream>

struct MyVector {
  double* data;
  int size;
  int capacity;
};

int arrayModSum (MyVector& someVar) {
      int count =0;
      for (int i=0; i<someVar.size; i++){
      someVar.data[i] = someVar.data[i]+1;


      count+=someVar.data[i];
      }
      return count;
};


void growMyVector (MyVector& someVar) {
if (someVar.size==someVar.capacity){
      MyVector tempVec;
      int newVecSize =(someVar.capacity*2);
      tempVec.data=new double [newVecSize];
      tempVec.size=0;
      tempVec.capacity=newVecSize;

      for (int i=0; i<tempVec.capacity; i++){
      if(i<someVar.size){
        tempVec.data[i]=someVar.data[i];
        tempVec.size++;
      }
      else {
      tempVec.data[i]= -1.0;
      }
    }
      delete [] someVar.data;
      someVar= tempVec;
      tempVec.data=nullptr;
  }
  std::cout<< "New Capacity: "<<someVar.capacity<<std::endl;
  std::cout<< "New Size: "<<someVar.size<<std::endl;
};


int main(int argc, const char * argv[]) {

    MyVector vec1;

    int vecSize=5;

    vec1.data= new double [vecSize];
    vec1.size=0;
    vec1.capacity=vecSize;


    for (int i=0; i<vec1.capacity; i++){

      vec1.data[i]=1.0;
      vec1.size++;

  };

std::cout << "Sum: " << arrayModSum (vec1) <<std::endl;

std::cout << "Old Capacity: " <<vec1.capacity<<std::endl;


growMyVector (vec1);


delete[] vec1.data;
vec1.data=nullptr;












    return 0;
}
