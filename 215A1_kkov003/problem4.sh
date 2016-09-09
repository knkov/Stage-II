#!/bin/bash
#upi:kkov003
# Ksenia Kovaleva
#Id: 4716583
# script for guessing random number between 1-10

((num = $RANDOM % 10 + 1))

read -p "Please enter a number between 1 and 10 inclusive " userNum

until [ $userNum -eq $num ]
do
    read -p "Please enter a number between 1 and 10 inclusive: " userNum
done
echo "Congratulations you guessed the number!"