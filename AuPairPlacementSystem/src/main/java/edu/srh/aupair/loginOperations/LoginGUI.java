package edu.srh.aupair.loginOperations;

import java.util.Scanner;

public class LoginGUI {

	public static void main(String[] args) {
		
		String userName = "";
		String userPassword = "";
		int the_count = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("***WELCOME TO AU PAIR PLACEMENT SYSTEM***\n");
		
		System.out.println("***PRESS 1 TO LOGIN AS HOST && PRESS 2 TO LOGIN AS AUPAIR*** \n");
					
		int loginAs = input.nextInt();
		
		System.out.println("Enter Username: ");
		userName = input.next();
		System.out.println("Enter Password: ");
		userPassword = input.next();
		String personType ;
		
		if(loginAs == 1)
		{
			personType = "HOST";
			edu.srh.aupair.loginOperations.LoginRepository.loginUser(userName, userPassword, personType);
			
		}
		else if(loginAs == 2)
		{
			personType = "AUPAIR";
			edu.srh.aupair.loginOperations.LoginRepository.loginUser(userName, userPassword, personType);
		}
		else
		{
			System.out.println("***Incorrect user input***");
			
		}
		
		input.close();

	}

}
