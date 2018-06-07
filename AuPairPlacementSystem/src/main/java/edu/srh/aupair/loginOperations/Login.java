package edu.srh.aupair.loginOperations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

public class Login {

	public static void main(String[] args) {
		loginWithUserCredentials();

	}

	public static void loginWithUserCredentials() {
		String userName = "";
		String userPassword = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Au Pair Placement system");
		int the_count = 0;
		try

		{
			Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();

			System.out.println("Enter Username: ");
			userName = input.next();
			System.out.println("Enter Password: ");
			userPassword = input.next();

			try {
				String getEncryptedPassword = "{Call VerifyEncryptedPassword(?,?)}";
				CallableStatement cs = conn.prepareCall(getEncryptedPassword);
				cs.setString(1, userName);
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.execute();
				String hashedPassword = cs.getString(2);
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
			// String getEncryptedPassword = "{Call VerifyEncryptedPassword(?,?)}";
			// CallableStatement cs = conn.prepareCall(getEncryptedPassword);
			// cs.setString(1, userName);
			// cs.registerOutParameter(2, Types.VARCHAR);
			// cs.execute();
			// String hashedPassword = cs.getString(2);
			// System.out.println(hashedPassword);
			//
			// if (BCrypt.checkpw(userPassword, hashedPassword)) {
			// System.out.println("It matches");
			// String loginValidation = "{Call loginValidation(?,?,?)}";
			// CallableStatement stmt = conn.prepareCall(loginValidation);
			// System.out.println("It matches2");
			// stmt.setString(1, userName);
			// stmt.setString(2, userPassword);
			// System.out.println("It matches3" + userName + userPassword);
			// stmt.registerOutParameter(3, Types.INTEGER);
			// stmt.execute();
			// the_count = stmt.getInt(3);
			// // System.out.println("thecount" + the_count);
			//
			// if (the_count != 0) {
			// System.out.println("Login successful");

			// } else {
			// System.out.println("Failed to login...Try again with correct password");
			// }

			// }

			// else
			// System.out.println("Failed to login...Try again with correct password");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
