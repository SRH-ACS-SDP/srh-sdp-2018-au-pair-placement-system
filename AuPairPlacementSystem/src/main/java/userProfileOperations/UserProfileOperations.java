package userProfileOperations;

import java.sql.*;
import java.util.Date;
import java.util.*;

public class UserProfileOperations {

	public static void main(String[] args) throws SQLException {

		try {

			//test
			String PERSON_TYPE = "HOST";
			Connection conn = null;
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AU_PAIR_MANAGEMENT?useSSL=false",
			"root", "password123");
			
			String query = "{CALL getProfileDetails(?, ?)}";
			CallableStatement statement = conn.prepareCall(query);

			statement.setInt("PERSONID", 1);
			statement.setString("PERSONTYPE", "HOST");

			ResultSet rs = statement.executeQuery();
			rs.next();

			String firstName = "";
			String lastName = "";
			int contactNo = 0;
			String gender = "";
			String maritalStatus = "";
			Date dob = new Date();
			Boolean isActive = false;
			String addressLine1 = "";
			String city = "";
			int postcode = 0;
			String countryName = "";
			String lastOnline = "";
			String title = "";
			String passportNo = "";
			
			Boolean hasValidVisa = false;
			Boolean hasSalaryExpectation = false;
			Boolean hasDrivingLicense = false;
			String hobbies = "";
			String supervisesChildOfAge = "";
			String eduQualification = "";
		
			Boolean isSalaryProvided = false;
			int numberOfKids = 0;
			int ageOfKids = 0;
			Boolean hasPyhsicalDisability = true; 
			String aboutMe = "";
							
			
			 firstName = rs.getString("FIRST_NAME");
			 lastName = rs.getString("LAST_NAME");
			 contactNo = rs.getInt("CONTACT_NO");
			 gender = rs.getString("GENDER");
			 maritalStatus = rs.getString("MARITAL_STATUS");
			 dob = rs.getDate("DOB"); /// check DOB Date time variable
			 isActive = rs.getBoolean("IS_ACTIVE"); // check boolean type
			 addressLine1 = rs.getString("ADDRESS_LINE1");
			 city = rs.getString("CITY");
			 postcode = rs.getInt("POSTCODE");
			 countryName = rs.getString("COUNTRY_NAME");
			 lastOnline = rs.getString("LAST_ONLINE");
			 title = rs.getString("TITLE");
			 passportNo = rs.getString("passport_no");
			if(PERSON_TYPE=="AUPAIR")
			{
			 hasValidVisa = rs.getBoolean("HAS_VALID_VISA");
			 hasSalaryExpectation = rs.getBoolean("HAS_SALARY_EXPECTATION");
			 hasDrivingLicense = rs.getBoolean("HAS_DRIVING_LICENSE");
			 hobbies = rs.getString("HOBBIES");
			 supervisesChildOfAge = rs.getString("SUPERVISES_CHILD_OF_AGE");
			 eduQualification = rs.getString("EDU_QUALIFICATION");
			}
			else{
			 isSalaryProvided = rs.getBoolean("IS_SALARY_PROVIDED");
			 numberOfKids = rs.getInt("NUMBER_OF_KIDS");
			 ageOfKids = rs.getInt("AGE_OF_KIDS");
			 hasPyhsicalDisability = rs.getBoolean("HAS_PHYSICAL_DISABILITY"); 
			}// check boolean type
			 aboutMe = rs.getString("ABOUT_ME");

			if (PERSON_TYPE == "AUPAIR") {
				System.out.println("Profile Details are : \n1)First Name: " + firstName + "\n2)Last Name: " + lastName
						+ "\n3)Contact No: " + contactNo + "\n4)Gender: " + gender + "\n5)Marital Status: "
						+ maritalStatus + "\n6)Date Of Birth: " + dob + "\n7)Is Active User: " + isActive
						+ "\n8)Address: " + addressLine1 + "\n9)City: " + city + "\n10)Postcode: " + postcode
						+ "\n11)Country: " +countryName + "\n12)Last Online: " + lastOnline + "\n13)Title: " + title + "\n14)Passport Number: "
						+ passportNo + "\n15)Has a Valid Visa: " + hasValidVisa + "\n16)Has Salary Expectations: "
						+ hasSalaryExpectation + "\n17)Has Driving License: " + hasDrivingLicense + "\n18)Hobbies: "
						+ hobbies + "\n19)Supervises Age of Chidren: " + supervisesChildOfAge
						+ "\n20)Educational Qualification: " + eduQualification + "\n21) About me: " + aboutMe);
			} else if (PERSON_TYPE == "HOST") {
				System.out.println("Profile Details are : \n1)First Name: " + firstName + "\n2)Last Name: " + lastName
						+ "\n3)Contact No: " + contactNo + "\n4)Gender: " + gender + "\n5)Marital Status: "
						+ maritalStatus + "\n6)Date Of Birth: " + dob + "\n7)Is Active User: " + isActive
						+ "\n8)Address: " + addressLine1 + "\n9)City: " + city + "\n10)Postcode: " + postcode
						+ "\n11)Country: " +countryName + "\n12)Last Online: " + lastOnline + "\n13)Title: " + title + "\n14)Passport Number: "
						+ passportNo + "\n15)Is Salary Provided: " + isSalaryProvided + "\n16)Number Of Kids: "
						+ numberOfKids + "\n17)Age of Kids: " + ageOfKids
						+ "\n18)Does any of your kid have Physical Disability: " + hasPyhsicalDisability
						+ "\n19)About me: " + aboutMe);
				
			}
			mainmenu();

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private static void mainmenu() throws SQLException {

		System.out.println(
				"\n Please enter the appropriate actions to be performed : \n 1) Update your profile \n 2) Perform Search "
						+ " \n 3) Delete your profile \n 4) View Proposals \n 5) Logout  ");

		Scanner sc = new Scanner(System.in);
		int userInput = sc.nextInt();
		if (userInput == 1) {
			updateProfile();
		} else if (userInput == 2) {
			searchProfile();
		} else if (userInput == 3) {
////			SystString sql = "DELETE FROM PERSON WHERE PERSON_ID = PERSON_ID"; /// check ek baar
//
//				// statement.executeUpdate(sql); /////check check
//				System.out.println("Thank you for being a part of Au-Pair Placement System");
//
//				// OR
//
//				// CallableStatement DELETE WALA FROM NIVEDITHA //)
//			} else {
//				System.out.println("Go"); // Ask niveditha about not deleting where to point it
//			}
		} else if (userInput == 4) {
			// Ask niveditha for Proposals Do we need to call the Proposals Operations???
		} else if (userInput == 5) {
			////// LOGOUT FUNCTIONALITY ASK NIVEDITHA
		}
		sc.close();
	}


	private static void viewProposals() {

	}

	private static void searchProfile() {
		// SELECT * from PERSON where LOCATION="", RATING ='' ,AGE = '', TITLE = "";

		// Scanner sc = new Scanner(System.in);
		// System.out.println("Which Search result do you want to choose? \n 1) or 2) or
		// 3)");
		// int useroption = sc.nextInt();
		// if (useroption == 1 || useroption ==2 || useroption ==3);
		// {
		//
		// CallableStatement statement = conn.prepareCall("{call
		// getProfileDetails(?,?)}");
		//
		//
		// }
		//
		System.out.println("Do you want to book an appointment? ");
	}

	private static void updateProfile() throws SQLException {
//
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AU_PAIR_MANAGEMENT?useSSL=false",
				"root", "password123");
				
		String firstname = "";
		String lastname = "";
		String email = "";
		String contact = "";
		String aboutme = "";
		String title = "";
		String maritalstatus = "";
		String interviewtimeslot = "";
		String passportnumber = "";
		Scanner sc = new Scanner(System.in);

		String query = "{CALL updateSelfProfile(?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement statementup = conn.prepareCall(query);
		
		
		char wish = 'y';
		int count = 0;
		String updatedchanges ="";
		while (wish == 'y' || wish == 'Y' ) {
			System.out.println("Which fields do you wish to Update? \n \nPress enter appropriate numbers: "
					+ "\n1) First name \n2) Last name \n3) Email \n4) Contact \n5) About me \n6) Title \n7) Marital Status"
					+ "\n8) Interview Time Slot \n9) Passport No.");

			int updateSelf = sc.nextInt();
			if (updateSelf == 1) {
				System.out.println("Please enter your first name: ");
				firstname = sc.next();
				count++;
				updatedchanges+= "First Name: " +firstname;
				
				
				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 2) {
				System.out.println("Please enter your last name: ");
				lastname = sc.next();
				count++;
				updatedchanges+="\nLast Name: "+lastname;  

				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 3) {
				System.out.println("Please enter your email: ");
				email = sc.next();
				count++;
				updatedchanges+="\nEmail: "+email;

				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 4) {
				System.out.println("Please enter your Contact: ");
				contact = sc.next();
				count++;
				updatedchanges+="\nContact: "+contact;
			
				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 5) {
				System.out.println("Please enter your About me section: ");
				aboutme = sc.next();
				count++;
				updatedchanges+="\nAbout me:"+aboutme;

				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 6) {
				System.out.println("Please enter your Title: ");
				title = sc.next();
				count++;
				updatedchanges+="\nTitle: "+title;
			
				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 7) {
				System.out.println("Please enter your Marital Status: ");
				maritalstatus = sc.next();
				count++;
				updatedchanges+="\nMarital Status: "+maritalstatus;

				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 8) {
				System.out.println("Please enter your Interview Time Slots: "); //// doubtsssss
				interviewtimeslot = sc.next();
				count++;
				updatedchanges+="\nInterview Time Slot: "+interviewtimeslot;

				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 9) {
				System.out.println("Please enter your Passport Number: ");
				passportnumber= sc.next();
				count++;
				updatedchanges+="\nPassport number: "+passportnumber;

				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			}

		}

		statementup.setInt("PERSONID", 1);
		statementup.setString("FIRSTNAME", firstname);
		statementup.setString("LASTNAME", lastname);
		statementup.setString("EMAIL", email);
		statementup.setString("CONTACT", contact);
		statementup.setString("ABOUTME", aboutme);
		//test github
		statementup.setString("TITLE", title);
		statementup.setString("MARITALSTATUS", maritalstatus);
		statementup.setString("INTERVIEW_TIME_SLOT", interviewtimeslot);
		statementup.setString("PASSPORT", passportnumber);
		

		statementup.execute();
		
		System.out.println(+count + " changes updated successfully");
		System.out.println("\n" +updatedchanges);
		
			mainmenu();
			sc.close();
	}

}

