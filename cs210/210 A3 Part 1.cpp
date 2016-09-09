#include <iostream>
#include <cstdlib>
#include <stdlib.h>

void uintConvert(long num,long collection[]){

 int j = 0; // starting index of collection array
 unsigned long a = num; // whole number
 unsigned long p = a & 0x7F; // first 7 bits
 p = p & 0x7F; // sets 8th bit to 0 for first byte
 collection[j] = p;
 a = a >> 7; //looks at next 7

  while (a != 0 ){
    ++j; // increment index of collection array
    p = a & 0x7F; //7 bits
    p = p | 0x80; // sets 8th bit to 1;
    collection[j] = p; // stored in array
    a = a >> 7; // shift right 

} // end while

  for ( int i = 0; j >= i; --j){ // prints out uintvar for the number
	long uintvar = collection[j];
	std::cout << std::hex << uintvar << " " ;
  }

} // end function




int
main(int argc, char *argv[])
{

long uintvarArray[10];
   
   for (int i = 1; i<argc; ++i){
	long a = ::atol(argv[i]); // converts to long
	std::cout << argv[i]<<" : ";
	uintConvert(a, uintvarArray);//call method to convert each number to uintvar
	std::cout << std::endl;
	
		
}
   return 0;
}


