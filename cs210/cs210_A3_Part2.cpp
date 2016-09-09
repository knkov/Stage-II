#include <iostream>
#include <cstdio>
#include <math.h>
#include <string>
#include <stdio.h>


void breakUpByte(long num,long collection[]){ // start method

int j = 0; //index of collection array
unsigned long msb;
unsigned long a = num; // total uintvar
unsigned long p = a & 0xFF;// extracts first 8 bits

msb = p & 0x80; //extracts terminating bit

if(msb == 0x80){ // no terminating byte
std::cout << 0; // output 0 to mean error


}else{
collection[j] = p; // puts first byte into array

a = a >> 8; // get next 8 bits
while(a != 0){
++j; //increment pointer
p = a & 0xFF; //extract 8 bits


p = p & 0x7F; // sets 8th bit to 0
collection[j] = p; // store in array
a = a >> 8; // next 8 bits
} // end while
//go through collection array
 
int powerOf = 0; // starting power
int sum = 0;



for(int i = 0; i <= j; i++){
  long b = collection[i];
  
  long byteVal = b * (pow(128,powerOf));
 
  sum += byteVal; // adds to sum

  ++powerOf; //increments power
  

}
std::cout << sum;
}// end else

} // end breakUpByte

int
main(int argc, char* argv[])
{ // start main
   long byteArray[10];
   //std::string str;
  
   
   
   for ( int i = 1; i < argc; ++i )
   { // start for
      long a;
      std::cout << argv[i] << ": "; // prints original input
	//str = argv[i]; 
	
	

	 //if(str.length() > 4){ // length of string is greater than 4 
	// trims string to first 4 hex chars 
	 
	//argv[i] = str.substr(0,3);
	
	//} //end if
      ::sscanf(argv[i], "%lx", &a); // read hex string and form long value
	//extract first 4 bits
      breakUpByte(a,byteArray);//break it into 8 bits
      //clear MSB
      // do base 128 arithmetic
	std::cout << std::endl;
   }//end for
   

   return 0;
}//end main



