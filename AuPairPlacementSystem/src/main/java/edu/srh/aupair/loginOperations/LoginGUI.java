package edu.srh.aupair.loginOperations;

import java.sql.SQLException;
import java.util.Scanner;
import edu.srh.aupair.userProfileOperations.UserProfileOperationsGUI;
import org.mindrot.jbcrypt.BCrypt;

public class LoginGUI {

	public static String loggedInPersonType;

	public static void main(String[] args) throws SQLException {
		LoginGUI loginGUI = new LoginGUI();
		int personId = loginGUI.loginUser();

		if (personId != 0) {
			UserProfileOperationsGUI userProfileOperationsGUI = new UserProfileOperationsGUI();
			userProfileOperationsGUI.getProfile(LoginGUI.loggedInPersonType, personId);
		}
	}

	public int loginUser() throws SQLException {

		int person_id = 0;
		String userName = "";
		String userPassword = "";
		int the_count = 0;
		Scanner input = new Scanner(System.in);

		System.out.println("***PRESS 1 TO LOGIN AS HOST && PRESS 2 TO LOGIN AS AUPAIR*** \n");

		int loginAs = input.nextInt();

		System.out.println("Enter Username: ");
		userName = input.next();
		System.out.println("Enter Password: ");
		userPassword = input.next();
		// String personType;

		if (loginAs == 1) {
			loggedInPersonType = "HOST";
			IloginServiceInterface obj = new LoginService();

			String ids[] = obj.loginUser(userName, loggedInPersonType, userPassword);

			person_id = Integer.parseInt(ids[1]);

			int noOfAttempts = 0;

			if (ids[0] == null) {
				System.out.println("No such entry in database");
			}
			if (ids[0] != null) 
			{
				while (!(BCrypt.checkpw(userPassword, ids[0])) && (noOfAttempts < 2)) {
					System.out.println("Login failed...Please enter your password again");
					userPassword = input.next();
					noOfAttempts++;
				}
				if (BCrypt.checkpw(userPassword, ids[0])) {
					System.out.println("Login successful");
					System.out.println("---------------------------");

				} else {
					System.out.println("Failed to login...Try again with correct password");
					loginUser();
				}
			}

		} else if (loginAs == 2) {
			loggedInPersonType = "AUPAIR";
			IloginServiceInterface obj = new LoginService();

			String ids[] = obj.loginUser(userName, loggedInPersonType, userPassword);
			person_id = Integer.parseInt(ids[1]);

			int noOfAttempts = 0;

			if (ids[0] == null) {
				System.out.println("No such entry in database");
			}

			if (ids[0] != null) {
				while (!(BCrypt.checkpw(userPassword, ids[0])) && (noOfAttempts < 2)) {
					System.out.println("Login failed...Please enter your password again");
					userPassword = input.next();
					noOfAttempts++;
				}
				if (BCrypt.checkpw(userPassword, ids[0])) {
					System.out.println("Login successful");
					System.out.println("---------------------------");

				} else {
					System.out.println("Failed to login...Try again with correct password");
					loginUser();
				}
			}

		}
		return person_id;

	}

}
