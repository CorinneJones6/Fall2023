/*
  Author: Daniel Kopta, Ben Jones
  
  CS 6010
  Bit puzzles
  * The set of functions below are exercises to help you practice accessing
  * and manipulating individual bits in program data, and to help understand
  * how a computer represents numbers.
  * Replace the TODO statements below with correct implementations of the functions.
  * Each function's purpose is described in a comment.
  * main() runs many tests on your functions and reports which ones passed or failed.
  * Make sure you pass all tests.
*/

#include <iostream>
#include <fstream>
#include <vector>

using std::cout;
using std::endl;
using std::string;

/*
 * Determines whether or not the specified bit is set in the input int
 *
 * Parameters:
 *   input -- The input int
 *   b -- The bit to retreive (0 = least significant, 31 = most significant)
 *
 * Returns:
 *   true if the specified bit is set, false otherwise.
 *
 * Examples:
 *   GetBit(5, 0) -> returns true, because the bit pattern for 5 is 101, we are looking at the rightmost bit
 *   GetBit(5, 1) -> returns false
 *   GetBit(-42, 31) -> returns true
 */



/*This checks the bit at "b"of the input and returns "true" if it is set to 1. It creates a variable "a" that shifts the bits of one to the left "b" times.*/
bool GetBit(uint32_t input, int b) {
    uint32_t a=1<<b;
    return (input&a);
}


/*
 * Determines whether or not an int is negative
 * 
 * Do not use the < or > operators
 *
 * Parameters:
 *   input -- The input int
 *
 * Returns:
 *   Whether or not the int is negative
 */

/* This takes the integer input and moves it to the righ 31 times. The only number that would be left would be the number in the first position, so it will return true if it is 1, since that means a number is negative. */
bool IsNegative( int input) {

    return (input>>31);
    
}

/*
 * Determines the number of bits set (to 1) in a number
 *
 * Parameters:
 *   input -- The input int
 *
 * Returns:
 *   The number of set bits.
 *
 * Examples:
 *   NumBitsSet(5) -> returns 2
 *   NumBitsSet(64) -> returns 1
 *   NumBitsSet(-1) -> returns 32
 */

/*This inputs a unsigned 32 bit int and ocunts how many times "1" is present. It does this by initiating a count to zero, looping through the input, and anytime there is a "1" the count is increased. It then shifts to the right one so that the processed number is disgarded.  */

int NumBitsSet( uint32_t input) {
    int count=0;
    while (input){
        count+=input&1;
        input>>=1;
    }
  return count;
}


/*
 * GetByte() returns a specified byte from a 4-byte variable.
 *
 * Parameters:
 *   input -- The input value
 *   b -- The byte to retreive (0 = least significant, 3 = most significant)
 *
 * Returns:
 *   The specified byte within the input.
 *
 * Examples:
 *   GetByte( 5, 0 )          -> returns 5
 *   GetByte( 5, 1 )          -> returns 0
 *   GetByte( 0x1234abcd, 0 ) -> returns 0xcd (205 as unsigned char)
 *   GetByte( 0x1234abcd, 3 ) -> returns 0x12 (18 as an unsigned char)
 */

/*This effectively returns the specified bit (int b) by shifting to the right that number of bits*8. (You times it by 8 since that is how many bits are in a byte). Then, you just return that number. Since you are returned a char, which is 8 bits, it will automatically disregard all of the numbers to the left of the bit you are sending back. */
unsigned char GetByte( uint32_t input, int b ) {
    return input>>=(b*8);

}


/*
 * SetByte() sets the specified byte in the input to the specified value, and returns the result.
 *
 * Parameters:
 *   input -- The input value
 *   value -- The value to set the input's specified byte to
 *   b -- The byte to set (0 = least significant, 3 = most significant)
 *
 * Returns:
 *   The modified result
 *
 * Examples:
 *   SetByte( 0, 5, 0 )                -> returns 5
 *   SetByte( 0, 5, 1 )                -> returns 0x500 (1280 as unsigned int)
 *   SetByte( 0xffffffff, 0, 2 )       -> returns 0xff00ffff (4278255615 as unsigned int)
 *   SetByte( (unsigned int)-1, 0, 2 ) -> returns 0xff00ffff (4278255615 as unsigned int)
 *   SetByte( 0xabcd, 7, 1 )           -> returns 0x7cd (1997 as unsigned int)
 */

/*The first part of this creates a vector of masks. The "00" effectively masks a corresponding byte. Based on where you want to input the new value, you would utilize the corresponding mask to the input. Then, you would left shift the new value b*8 to get the new value to the corresponding position of the input. Then, you would assign the input to the new value with the (|) "or operator" which will put the new value into the blank spot of the input. */
uint32_t SetByte( uint32_t input, uint8_t value, int b ){
    uint32_t newValue=value;
    std::vector<uint32_t> masks = {0xFFFFFF00, 0xFFFF00FF, 0xFF00FFFF, 0x00FFFFFF};
    input=(input&masks[b]);
    newValue<<=b*8;
    input=input | newValue;
    return input;
}


/*
 * Negate() computes the negation of an integer.
 *
 * Do NOT use any of the arithmetic operators including:
 *     * or *= operators
 *     / or /= operators
 *     + or += operators
 *     - or -= operators
 *     - (unary minus operator)
 *
 * You may (and probably should) use the unary bitwise ~ operator.
 *
 * Assumes that input is not equal to INT_MIN (a bit pattern of 100...0, or the minimum integer)
 *
 * Parameters:
 *   input -- The input int
 *
 * Returns:
 *   -i (negative i)
 *
 * Examples:
 *   Negate(5) -> returns -5
 *   Negate(-1) -> returns 1
 */


/*This makes a numbe negative by fliping all of the numbers and then adding one. */
int Negate( int input )
{
    int a=~input+1;
    return a;
}


/*
 * Challenge Question
 *
 * Increment
 * This function should return x + 1 but should only make use of bitwise operators and == or !=
*/

/* This mask, 0b1, is the binary representation of decimal 1. It then entires a while loop that keeps going until you find the least significant "1". Once it finds a 1, the x=(x^mask) essentially flips that 1 to a zero, clearing it out. We then shift this over so that we can add one with the mask. */
int Increment( uint32_t x ){
    uint32_t mask=0b1;
    while (mask & x){
        x=(x^mask);
        mask=mask<<1;
    }
    return x | mask;
}

/************************************************/
/* End bit puzzles. Below are the provided tests. */
/************************************************/


/*
 * Three forms of helper functions for the tests in main().
 * These functions compare a given value to an expected value, 
 * and report an error if they don't match.
 */

/*
 * Compare two 32-bit values
 */
void Test32Bit( int a, int b, const string & message )
{
  if( a != b ) {
    cout << "FAIL: " << message << ", expected " << b << ", got " << a << endl;
  }
  else {
    cout << "PASS: " << message << endl;
  }
}

/*
 * Compare two 8-bit values
 */
void Test8Bit( unsigned char a, unsigned char b, const string & message )
{
  if( a != b ) {
    cout << "FAIL: " << message << ", expected " << std::hex << (unsigned int)b << ", got " << std::hex << (unsigned int)a << endl;
  }
  else {
    cout << "PASS: " << message << endl;
  }
}

/*
 * Compare two boolean values
 */
void TestBool( bool a, bool b, const string & message )
{
  if( a != b ) {
    cout << "FAIL: " << message << ", expected " << b << ", got " << a << endl;
  }
  else {
    cout << "PASS: " << message << endl;
  }
}


int main()
{  
  /*
   * These tests exercise your bit puzzle solutions.
   * Many of the tests rely on hexadecimal instead of decimal.
   * Since hexadecimal maps much more easily to binary, it's  more useful 
   * when we want to specify a number with a specific bit pattern, 
   * such as alternating 10101010... (0xaa), or 01010101... (0x55), 
   * or all ones in a certain byte: 0x00ff0000
   */
  
  TestBool( GetBit( 0, 0 ), false, "GetBit1" );
  TestBool( GetBit( 0, 1 ), false, "GetBit2" );
  TestBool( GetBit( 0, 31 ), false, "GetBit3" );
  TestBool( GetBit( -1, 0 ), true, "GetBit4" );
  TestBool( GetBit( -1, 1 ), true, "GetBit5" );
  TestBool( GetBit( -1, 31 ), true, "GetBit6" );
  TestBool( GetBit( 0xAAAAAAAA, 0 ), false, "GetBit7" );
  TestBool( GetBit( 0xAAAAAAAA, 16 ), false, "GetBit8" );
  TestBool( GetBit( 0xAAAAAAAA, 17 ), true, "GetBit9" );
  TestBool( GetBit( 0xAAAAAAAA, 31 ), true, "GetBit10" );

  TestBool( IsNegative( 0 ), false, "IsNegative1" );
  TestBool( IsNegative( 1 ), false, "IsNegative2" );
  TestBool( IsNegative( -1 ), true, "IsNegative3" );
  TestBool( IsNegative( 0xAAAAAAAA ), true, "IsNegative4" );
  TestBool( IsNegative( 0x55555555 ), false, "IsNegative5" );
  TestBool( IsNegative( INT_MIN ), true, "IsNegative6" );
  TestBool( IsNegative( INT_MAX ), false, "IsNegative7" );

  Test32Bit( NumBitsSet( 0 ), 0, "NumBitsSet1" );
  Test32Bit( NumBitsSet( 1 ), 1, "NumBitsSet2" );
  Test32Bit( NumBitsSet( 2 ), 1, "NumBitsSet3" );
  Test32Bit( NumBitsSet( 3 ), 2, "NumBitsSet4" );
  Test32Bit( NumBitsSet( 5 ), 2, "NumBitsSet5" );
  Test32Bit( NumBitsSet( -1 ), 32, "NumBitsSet6" );
  Test32Bit( NumBitsSet( 0xAAAAAAAA ), 16, "NumBitsSet7" );
  Test32Bit( NumBitsSet( 0x55555555 ), 16, "NumBitsSet8" );

  Test8Bit( GetByte( 0, 0 ), 0, "GetByte1" );
  Test8Bit( GetByte( 0, 1 ), 0, "GetByte2" );
  Test8Bit( GetByte( 0, 2 ), 0, "GetByte3" );
  Test8Bit( GetByte( 0, 3 ), 0, "GetByte4" );
  Test8Bit( GetByte( 0xAAAAAAAA, 0 ), 0xaa, "GetByte4" );
  Test8Bit( GetByte( 0xAAAAAAAA, 1 ), 0xaa, "GetByte5" );
  Test8Bit( GetByte( 0xAAAAAAAA, 2 ), 0xaa, "GetByte6" );
  Test8Bit( GetByte( 0xAAAAAAAA, 3 ), 0xaa, "GetByte7" );
  Test8Bit( GetByte( 0x12345678, 0 ), 0x78, "GetByte8" );
  Test8Bit( GetByte( 0x12345678, 1 ), 0x56, "GetByte9" );
  Test8Bit( GetByte( 0x12345678, 2 ), 0x34, "GetByte10" );
  Test8Bit( GetByte( 0x12345678, 3 ), 0x12, "GetByte11" );

  Test32Bit( SetByte( 0, 0xFF, 0 ), 0xFF, "SetByte1" );
  Test32Bit( SetByte( 0, 0xFF, 1 ), 0xFF00, "SetByte2" );
  Test32Bit( SetByte( 0, 0xFF, 2 ), 0xFF0000, "SetByte3" );
  Test32Bit( SetByte( 0, 0xFF, 3 ), 0xFF000000, "SetByte4" );
  Test32Bit( SetByte( 0x12345678, 0xAA, 0 ), 0x123456aa, "SetByte5" );
  Test32Bit( SetByte( 0x12345678, 0xAA, 1 ), 0x1234aa78, "SetByte6" );
  Test32Bit( SetByte( 0x12345678, 0xAA, 2 ), 0x12aa5678, "SetByte7" );
  Test32Bit( SetByte( 0x12345678, 0xAA, 3 ), 0xaa345678, "SetByte8" );

  Test32Bit( Negate( -1 ), 1, "Negate1" );
  Test32Bit( Negate( 1 ), -1, "Negate2" );
  Test32Bit( Negate( 2 ), -2, "Negate3" );
  Test32Bit( Negate( -2 ), 2, "Negate4" );
  Test32Bit( Negate( 0 ), 0, "Negate5" );
  Test32Bit( Negate( 0x7fffffff ), 0x80000001, "Negate6" );
  Test32Bit( Negate( 0xAAAAAAAA ), 0x55555556, "Negate7" );


  Test32Bit( Increment( 0 ), 1, "Increment1" );
  Test32Bit( Increment( -1 ), 0, "Increment2" );
  Test32Bit( Increment( 10000 ), 10001, "Increment3" );
  Test32Bit( Increment( -999 ), -998, "Increment4" );

  return 0;
}