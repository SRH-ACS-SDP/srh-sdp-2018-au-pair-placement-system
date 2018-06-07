package edu.srh.aupair.loginOperations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;

public class Login {

	public static void main(String[] args) {
		loginUser();
		
	}

	public static void loginUser() {
		String userName ="";
		String userPassword="";
		Scanner input = new Scanner (System.in);
		System.out.println( "Welcome to Au Pair Placement system" );
		int the_count = 0 ;
		try
		
		{
			Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();
			
				System.out.println("Enter Username: ");
				userName = input.next();
				System.out.println("Enter Password: ");
				userPassword = input.next();
				
				String loginValidation = "{Call loginValidation(?,?,?)}";
				CallableStatement stmt = conn.prepareCall(loginValidation);
				
				stmt.setString(1, userName);
				stmt.setString(2, userPassword);
				stmt.registerOutParameter(3, Types.INTEGER);
				stmt.execute();
				the_count = stmt.getInt(3);
				System.out.println("thecount" + the_count);
				
				if(the_count != 0)
				{
				System.out.println("Login successful");
								
			}
			else {
				System.out.println("Failed to login...");
			}
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
