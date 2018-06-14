package edu.srh.aupair.loginOperations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

public class Login {

	public static void main(String[] args) throws SQLException {
		
		String userName = "";
		String userPassword = "";
		int the_count = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("***WELCOME TO AU PAIR PLACEMENT SYSTEM***\n");
		
		System.out.println("***PRESS 1 TO LOGIN AS HOST && PRESS 2 TO LOGIN AS AUPAIR*** \n");
					
		int loginAs = input.nextInt();
		
		
		if(loginAs == 1)
		{
			System.out.println("Enter Username: ");
			userName = input.next();
			System.out.println("Enter Password: ");
			userPassword = input.next();
			String personType = "HOST";

			loginUser(userName, userPassword, personType);
		}
		else if(loginAs == 2)
		{
			String personType = "AUPAIR";
			loginUser(userName, userPassword,  personType);
		}
		else
		{
			System.out.println("***Incorrect user input***");
			
		}
		
		input.close();
		
	}



		
	public static void loginWithUserCredentials() throws SQLException {
		String userName = "";
		String userPassword = "";
		int the_count = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("***WELCOME TO AU PAIR PLACEMENT SYSTEM***\n");
		
		
		System.out.println("***PRESS 1 TO LOGIN AS HOST && PRESS 2 TO LOGIN AS AUPAIR*** \n");
					
		int loginAs = input.nextInt();
		
		Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();
		
		if(loginAs == 1)
		{
			System.out.println("Enter Username: ");
			userName = input.next();
			System.out.println("Enter Password: ");
			userPassword = input.next();
			String personType = "HOST";

			try {
				String getEncryptedPassword = "{Call VerifyEncryptedPassword(?,?,?)}";
				CallableStatement cs = conn.prepareCall(getEncryptedPassword);
				cs.setString(1, userName);
				cs.setString(2, personType);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.execute();
				String hashedPassword = cs.getString(3);
				// System.out.println(hashedPassword);
				if (BCrypt.checkpw(userPassword, hashedPassword)) {
					System.out.println("Login successful");
				}

				else {
					System.out.println("Failed to login...Try again with correct password");
				}

			}

			catch (Exception ex) {
				System.out.println("Failed to login...Try again with correct password");
			}
		}
		else if(loginAs == 2)
		{	
			System.out.println("Enter Username: ");			
			userName = input.next();
			System.out.println("Enter Password: ");
			userPassword = input.next();
			String personType = "AUPAIR";
			try {
				String getEncryptedPassword = "{Call VerifyEncryptedPassword(?,?,?)}";
				CallableStatement cs = conn.prepareCall(getEncryptedPassword);
				cs.setString(1, userName);
				cs.setString(2, personType);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.execute();
				String hashedPassword = cs.getString(3);
				// System.out.println(hashedPassword);
				if (BCrypt.checkpw(userPassword, hashedPassword)) {
					System.out.println("Login successful");
				}

				else {
					System.out.println("Failed to login...Try again with correct password");
				}

			}

			catch (Exception ex) {
				System.out.println("Failed to login...Try again with correct password");
			}
		}
		else
		{
			System.out.println("***Incorrect user input***");
			
		}
		
		input.close();
		
	}

	
	public static void loginUser (String userName, String userPassword,  String personType)
	{
		verifyLoginUser(userName,userPassword,personType);
	}
	
	
	
	

	public static void verifyLoginUser(String userName, String userPassword,  String personType) 
	{
		try {
			
			Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();
			
			
			String getEncryptedPassword = "{Call VerifyEncryptedPassword(?,?,?)}";
			CallableStatement cs = conn.prepareCall(getEncryptedPassword);
			cs.setString(1, userName);
			cs.setString(2, personType);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.execute();
			String hashedPassword = cs.getString(3);
			// System.out.println(hashedPassword);
			if (BCrypt.checkpw(userPassword, hashedPassword)) {
				System.out.println("Login successful");
			}

			else {
				System.out.println("Failed to login...Try again with correct password");
			}

		}

		catch (Exception ex) {
			System.out.println("Failed to login...Try again with correct password");
		}
	}

}
