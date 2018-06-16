package edu.srh.aupair.loginOperations;

import java.sql.SQLException;
import java.util.Scanner;
import edu.srh.aupair.userProfileOperations.UserProfileOperationsGUI;

public class LoginGUI {

	public static String loggedInPersonType ; 
	
	public static void main(String[] args) throws SQLException 
	{	
		LoginGUI loginGUI =new LoginGUI();
		int personId = loginGUI.loginUser();
		
		if(personId != 0)
		{
			UserProfileOperationsGUI userProfileOperationsGUI = new UserProfileOperationsGUI();
			userProfileOperationsGUI.getProfile(LoginGUI.loggedInPersonType, personId);
		}
		
	}

	public  int loginUser() 
	{
		int person_id = 0 ;
		String userName = "";
		String userPassword = "";
		int the_count = 0;
		Scanner input = new Scanner(System.in);
		//System.out.println("***WELCOME TO AU PAIR PLACEMENT SYSTEM***\n");
		
		System.out.println("***PRESS 1 TO LOGIN AS HOST && PRESS 2 TO LOGIN AS AUPAIR*** \n");
					
		int loginAs = input.nextInt();
		
		System.out.println("Enter Username: ");
		userName = input.next();
		System.out.println("Enter Password: ");
		userPassword = input.next();
		
					
		if(loginAs == 1)
		{
			loggedInPersonType = "HOST";
			IloginServiceInterface obj=new LoginService();
			person_id = obj.loginUser(userName, userPassword, loggedInPersonType);
			
			
		}
		else if(loginAs == 2)
		{
			loggedInPersonType = "AUPAIR";
			IloginServiceInterface obj = new LoginService();
			person_id = obj.loginUser(userName, userPassword, loggedInPersonType);
		}
		else
		{
			System.out.println("***Incorrect user input***");
			
		}
		
		
		return person_id;
	}

}
