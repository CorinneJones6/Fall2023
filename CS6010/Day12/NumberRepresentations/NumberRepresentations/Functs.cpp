//
//  Functs.cpp
//  NumberRepresentations
//
//  Created by Corinne Jones on 9/6/23.
//

#include "Functs.hpp"
#include <cmath>
#include <fstream>

bool approxEquals( double a, double b, double tolerance ){
    return (std::abs(a + b)<tolerance);
}

