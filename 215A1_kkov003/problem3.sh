#!/bin/bash
# upi: kkov003
# Ksenia Kovaleva
#Id: 4716583

read -p "Please enter Status ('S' for salary, 'H' for hour or 'C' for contract) : " stat
read -p "Please enter Years: " years
status=$(echo $stat | tr [:lower:] [:upper:] ) 
case $status in
		S | H) 
		if  [ $years -ge 2 ]
		then 
			echo " You are eligible to receive 50 shares of company stock."
		else
			if [ $years -lt 2 ]
			then
			    echo "You are not eligible to receive 50 shares of company stock."
			else
			    echo "Invalid input for Years."
                         fi

			
		fi
			 ;;
		
		C)
			if [ $years -gt 3 ]
			then
				echo "You are eligible to receive a \$100 bonus!"
			else
				if [ $years -le 3 ] 
				then
				   echo " You are not eligible for the \$100 bonus."
			        else
			           echo "Invalid input for Years."
			        fi
			fi
			
			;;
		*)
			echo "Invalid Status input." ;;
esac