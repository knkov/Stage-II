#!bin/bash
#Ksenia Kovaleva
#Id: 4716385
#upi: kkov003

cd ~
mkdir myclasses 
cd myclasses
mkdir CPSC203 CPSC331
cd CPSC203
echo "Answer to homework1" > text1.txt
echo "Answer to homework2" > text2.txt
cd ../CPSC331
mkdir notes programs old_stuff
cd notes
echo "COMPSCI.215" >> text3.txt
echo "Semester I" >> text3.txt
echo "City Campus" >> text3.txt
echo "   2013   " >> text3.txt
cd ../old_stuff
mkdir results misc
cd ../programs
echo 'public class helloworld{
public static void main (String args []){
System.out.println("hello world");
}}' > helloworld.java
javac helloworld.java 
java helloworld > output.txt
mv output.txt ../old_stuff/results
mv helloworld.class ../old_stuff/misc
