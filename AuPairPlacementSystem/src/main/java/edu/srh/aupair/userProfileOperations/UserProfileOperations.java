package edu.srh.aupair.userProfileOperations;

//testing commit
import java.sql.*;
import java.util.Date;
import java.util.*;

public class UserProfileOperations {

	public static void main(String[] args) throws SQLException {

		try {

			String PERSON_TYPE = "HOST";
			// Connection conn = null;

			Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();
			// conn =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/AU_PAIR_MANAGEMENT?useSSL=false",
			// "root",
			// "password123");

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
			if (PERSON_TYPE == "AUPAIR") {
				hasValidVisa = rs.getBoolean("HAS_VALID_VISA");
				hasSalaryExpectation = rs.getBoolean("HAS_SALARY_EXPECTATION");
				hasDrivingLicense = rs.getBoolean("HAS_DRIVING_LICENSE");
				hobbies = rs.getString("HOBBIES");
				supervisesChildOfAge = rs.getString("SUPERVISES_CHILD_OF_AGE");
				eduQualification = rs.getString("EDU_QUALIFICATION");
			} else {
				isSalaryProvided = rs.getBoolean("IS_SALARY_PROVIDED");
				numberOfKids = rs.getInt("NUMBER_OF_KIDS");
				ageOfKids = rs.getInt("AGE_OF_KIDS");
				hasPyhsicalDisability = rs.getBoolean("HAS_PHYSICAL_DISABILITY");
			} // check boolean type
			aboutMe = rs.getString("ABOUT_ME");

			if (PERSON_TYPE == "AUPAIR") {
				System.out.println("Profile Details are : \n\n1) First Name: " + firstName + "\n2) Last Name: "
						+ lastName + "\n3) Contact No: " + contactNo + "\n4) Gender: " + gender
						+ "\n5) Marital Status: " + maritalStatus + "\n6) Date Of Birth: " + dob
						+ "\n7) Is Active User: " + isActive + "\n8) Address: " + addressLine1 + "\n9) City: " + city
						+ "\n10) Postcode: " + postcode + "\n11) Country: " + countryName + "\n12)Last Online: "
						+ lastOnline + "\n13)Title: " + title + "\n14) Passport Number: " + passportNo
						+ "\n15) Has a Valid Visa: " + hasValidVisa + "\n16) Has Salary Expectations: "
						+ hasSalaryExpectation + "\n17)Has Driving License: " + hasDrivingLicense + "\n18) Hobbies: "
						+ hobbies + "\n19) Supervises Age of Chidren: " + supervisesChildOfAge
						+ "\n20) Educational Qualification: " + eduQualification + "\n21) About me: " + aboutMe);
			} else if (PERSON_TYPE == "HOST") {
				System.out.println("Profile Details are : \n1) First Name: " + firstName + "\t\n2) Last Name: "
						+ lastName + "\n3) Contact No: " + contactNo + "\n4) Gender: " + gender
						+ "\n5) Marital Status: " + maritalStatus + "\n6) Date Of Birth: " + dob
						+ "\n7) Is Active User: " + isActive + "\n8) Address: " + addressLine1 + "\n9) City: " + city
						+ "\n10) Postcode: " + postcode + "\n11) Country: " + countryName + "\n12) Last Online: "
						+ lastOnline + "\n13) Title: " + title + "\n14) Passport Number: " + passportNo
						+ "\n15) Is Salary Provided: " + isSalaryProvided + "\n16) Number Of Kids: " + numberOfKids
						+ "\n17) Age of Kids: " + ageOfKids + "\n18) Does any of your kid have Physical Disability: "
						+ hasPyhsicalDisability + "\n19) About me: " + aboutMe);
			}
			mainMenu();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void mainMenu() throws SQLException {

		System.out.println("\n =============== MAIN MENU =============== \n"
				+ "\nPlease enter the appropriate actions to be performed : \n\n 1) Update your profile \n 2) Perform Search "
				+ " \n 3) Delete your profile \n 4) View Proposals \n 5) Logout  ");

		Scanner sc = new Scanner(System.in);
		int userInput = sc.nextInt();
		if (userInput == 1) {
			updateProfile(sc);
		} else if (userInput == 2) {
			searchByPreference(sc);
		} else if (userInput == 3) {
			deleteSelfProfile(sc);
		} else if (userInput == 4) {
			viewProposals();
		} else if (userInput == 5) {
			logout(sc);
		}
		sc.close();
	}

	public static void logout(Scanner sc) throws SQLException {
		
		char logout = '\0';
		System.out.println("\n Are you sure you want to logout of the system? Y/N");
		logout = sc.next().charAt(0);
		if(logout =='Y'  || logout =='y' )
		{
			System.out.println("\nSuccessfully Logged out!\n");
		}	
		else
		{
			mainMenu();
		}
	}

	public static void viewProposals() throws SQLException {

		int proposalId = 0;

		String auPairName = "";
		String hostName = "";
		String tasksForAuPair = "";
		String workingHoursProposed = "";
		String RemunerationsProposed = "";
		String holidaysProposed = "";
		boolean travelCosts = false;
		String travelCostsStr = String.valueOf(travelCosts);

		String PERSON_TYPE = "HOST";
		Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();

		String query = "{CALL viewProposal(?,?)}";
		CallableStatement statementview = conn.prepareCall(query);

		statementview.setInt("PERSONID", 1);
		statementview.setString("PERSONTYPE", "HOST");

		ResultSet rs = statementview.executeQuery();

		BuildTable table = new BuildTable();

		System.out.println("\n =============== PROPOSALS =============== \n");

		table.addRow("PROPOSAL ID   ||  ", "      NAME         ||  ", "Tasks for Au Pair   ||  ",
				"Working Hours Proposed  ||  ", "Remuneration Proposed  ||  ", "Holidays Proposed   ||  ",
				"Travel Costs");

		int count = 0;
		while (rs.next()) {

			proposalId = rs.getInt("PROPOSAL_ID");

			tasksForAuPair = rs.getString("TASKS_FOR_AU_PAIR");
			workingHoursProposed = rs.getString("WORKING_HOURS_PROPOSED");
			RemunerationsProposed = rs.getString("REMUNERATIONS_PROPOSED");
			holidaysProposed = rs.getString("HOLIDAYS_PROPOSED");
			travelCosts = rs.getBoolean("TRAVEL_COSTS");

			String proposalIdStr = String.valueOf(proposalId);

			hostName = rs.getString("HOSTNAME");
			auPairName = rs.getString("AUPAIRNAME");

			table.addRow("----------", "----------", "----------", "----------", "----------", "----------",
					"----------");

			table.addRow(proposalIdStr, PERSON_TYPE == "HOST" ? auPairName : hostName, tasksForAuPair,
					workingHoursProposed, RemunerationsProposed, holidaysProposed, travelCostsStr);
			count++;
		}
		System.out.println(table.toString());

		if (count == 0) {
			System.out.println("Sorry no Proposals found !!! \n");
			mainMenu();
		}

		// TO DO call the Proposal Operations.java where you have the option to accept
		// or reject the proposals
	}

	public static void searchByPreference(Scanner sc) throws SQLException {

		Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();
		// Connection conn =
		// DriverManager.getConnection("jdbc:mysql://localhost:3306/AU_PAIR_MANAGEMENT?useSSL=false",
		// "root", "password123");
//		Scanner sc = new Scanner(System.in);
		String PERSON_TYPE = "HOST"; ///// REMEMEBER TO REMOVE THIS AND UPAR WALE update MEIN AFTER U GET INPUTS FROM
										///// db diRECTLY ***********TO DO
		int searchOptions = '0';
		Character searchMore = 'y';
		String gender = "";
		String qualification = "";
		String country = "";
		String city = "";
		String randomSearch = "";
		String searchedParameter = "";
		Boolean salaryProvided = false;
		Boolean salaryExpectation = false;
		String preferredLanguage = "";

		Boolean validVisa = false;
		Boolean drivingLicense = false;
		int personId = 0;

		String firstName = "";
		String lastName = "";
		int contactNo = 0;

		String maritalStatus = "";
		Date dob = new Date();
		Boolean isActive = false;
		String addressLine1 = "";

		int postcode = 0;

		String lastOnline = "";
		String title = "";
		String passportNo = "";

		String hobbies = "";
		String supervisesChildOfAge = "";

		int numberOfKids = 0;
		int ageOfKids = 0;
		Boolean hasPyhsicalDisability = false;
		String aboutMe = "";
		int ratings = 0;

		while (searchMore == 'y' || searchMore == 'Y') {

			System.out.println(
					"\nWhat parameters would you like to choose for searching? \n\nChoose the following options: ");
			if (PERSON_TYPE == "AUPAIR") {
				System.out.println(
						" \n1) Gender \n2) Qualification \n3) Country \n4) City \n5) Random Search \n6) Preferred Language \n7) Ratings");
			} else if (PERSON_TYPE == "HOST")
				System.out.println(
						"\n1) Gender \n2) Qualification \n3) Country \n4) City \n5) Random Search \n6) Preferred Language \n7) Ratings");
			/// see if you want to put random search in the end.

			searchOptions = sc.nextInt();

			if (searchOptions == 1) {
				System.out.println("Enter the Gender you want to search: ");
				gender = sc.next();
				searchedParameter += "\nGender: " + gender;
				System.out.println("Do you want to add more parameters to your search criteria Y/N ?"); // Try to
																										// eliminate
																										// this
																										// afterwards.
																										// // afterwards
				searchMore = sc.next().charAt(0);
			} else if (searchOptions == 2) {
				System.out.println("Enter the Qualification you want to search: ");
				qualification = sc.next();
				searchedParameter += "\nQualification: " + qualification;
				System.out.println("Do you want to add more parameters to your search criteria Y/N ?");
				searchMore = sc.next().charAt(0);
			} else if (searchOptions == 3) {
				System.out.println("Enter the name of the Country you want to search: ");
				country = sc.next();
				searchedParameter += "\nCountry: " + country;
				System.out.println("Do you want to add more parameters to your search criteria Y/N ?");
				searchMore = sc.next().charAt(0);
			} else if (searchOptions == 4) {
				System.out.println("Enter the name of the City you want to search: ");
				city = sc.next();
				searchedParameter += "\nCity: " + city;
				System.out.println("Do you want to add more parameters to your search criteria Y/N ?");
				searchMore = sc.next().charAt(0);
			} else if (searchOptions == 5) {
				System.out.println("Enter any Random search on the basis of which you want to search: ");
				randomSearch = sc.next();
				searchedParameter += "\nRandom Search: " + randomSearch;
				System.out.println("Do you want to add more parameters to your search criteria Y/N ?");
				searchMore = sc.next().charAt(0);
			} else if (searchOptions == 6) {
				System.out.println("Enter the preferred language you want to search: ");
				preferredLanguage = sc.next();
				searchedParameter += "\nPreferred Language " + preferredLanguage;
				System.out.println("Do you want to add more parameters to your search criteria Y/N ?");
				searchMore = sc.next().charAt(0);
			} else if (searchOptions == 7) {
				System.out.println("Enter the rating between 1 to 5: ");
				ratings = sc.nextInt();
				searchedParameter += "\nRatings" + ratings;
				System.out.println("Do you want to add more parameters to your search criteria Y/N ?");
				searchMore = sc.next().charAt(0);
			}
		}

		String query = "{CALL searchByPreference(?,?,?,?,?,?,?,?)}";
		CallableStatement statementsearch = conn.prepareCall(query);

		statementsearch.setInt("PERSONID", 1);
		statementsearch.setString("PERSONTYPE", "HOST");
		statementsearch.setString("GENDER", gender);
		statementsearch.setString("QUALIFICATION", qualification);
		statementsearch.setString("COUNTRY", country);
		statementsearch.setString("CITY", city);
		// statementsearch.setBoolean("HAS_VALID_VISA", validVisa);
		// statementsearch.setBoolean("HAS_DRIVING_LICENSE", drivingLicense);
		// statementsearch.setBoolean("IS_SALARY_PROVIDED", salaryProvided);
		statementsearch.setString("RANDOMSEARCH", randomSearch);
		statementsearch.setString("PREFERREDLANGUAGE", preferredLanguage);

		BuildTable table = new BuildTable();

		table.addRow(PERSON_TYPE == "HOST" ? "AU-PAIR ID   ||  " : "HOST ID   ||" + "  ", "First Name   ||  ",
				"Last Name   ||  ", "Contact No   ||  ", "Gender   ||  ", "Marital Status   ||  ",
				"Date of Birth  ||  ", "Is Active User  ||  ", "Address   ||  ", "City   ||  ", "Postcode   ||  ",
				"Country   ||  ", "Last Online   ||  ", "Title   ||  ", "Passport Number  ||  ", "About me   ||  ",
				PERSON_TYPE == "HOST" ? "Has Salary Expectations   ||  " : "Is Salary Provided   ||  ",
				PERSON_TYPE == "HOST" ? "Has a Valid Visa   ||  " : "Number of Kids   ||  ",
				PERSON_TYPE == "HOST" ? "Has Driving License   ||  " : "Age of Kids",
				PERSON_TYPE == "HOST" ? "Hobbies   ||  " : "Kids with Disability   ||  ",
				PERSON_TYPE == "HOST" ? "Supervises Age of Children   ||  " : " ",
				PERSON_TYPE == "HOST" ? "Educational Qualification   ||  " : " ", "Preferred Language");

		table.addRow("----------", "----------", "----------", "----------", "----------", "----------", "----------",
				"----------", "----------", "----------", "----------", "----------", "----------", "----------",
				"----------", "----------", "----------", "----------", "----------", "----------", "----------",
				"----------", "----------");

		System.out.println("Displaying the results based on following parameters :\n" + searchedParameter + "\n");

		ResultSet rs = statementsearch.executeQuery();

		int count = 0;
		while (rs.next()) {

			personId = rs.getInt("PERSON_ID");
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
			country = rs.getString("COUNTRY_NAME");
			lastOnline = rs.getString("LAST_ONLINE");
			title = rs.getString("TITLE");
			passportNo = rs.getString("passport_no");
			if (PERSON_TYPE == "AUPAIR") {
				validVisa = rs.getBoolean("HAS_VALID_VISA");
				salaryExpectation = rs.getBoolean("HAS_SALARY_EXPECTATION");
				drivingLicense = rs.getBoolean("HAS_DRIVING_LICENSE");
				hobbies = rs.getString("HOBBIES");
				supervisesChildOfAge = rs.getString("SUPERVISES_CHILD_OF_AGE");
				qualification = rs.getString("EDU_QUALIFICATION");
			} else {
				// salaryProvided = rs.getBoolean("IS_SALARY_PROVIDED");
				// numberOfKids = rs.getInt("NUMBER_OF_KIDS");
				// ageOfKids = rs.getInt("AGE_OF_KIDS");
				// hasPyhsicalDisability = rs.getBoolean("HAS_PHYSICAL_DISABILITY");
			} // check boolean type
			aboutMe = rs.getString("ABOUT_ME");
			preferredLanguage = rs.getString("LANGUAGES");
			ratings = rs.getInt("RATINGS");

			String personIdStr = String.valueOf(personId);
			String validVisaStr = String.valueOf(validVisa);
			String drivingLicenseStr = String.valueOf(drivingLicense);
			String contactNoStr = String.valueOf(contactNo);
			String dobStr = String.valueOf(dob);
			String postCodeStr = String.valueOf(postcode);
			String numberOfKidsStr = String.valueOf(numberOfKids);
			String ageOfKidsStr = String.valueOf(ageOfKids);
			String hasPyhsicalDisabilityStr = String.valueOf(hasPyhsicalDisability);
			String supervisesChildOfAgeStr = String.valueOf(supervisesChildOfAge);
			String salaryExpectationStr = String.valueOf(salaryExpectation);
			String salaryProvidedStr = String.valueOf(salaryProvided);
			String isActiveStr = String.valueOf(isActive);
			String ratingsStr = String.valueOf(ratings);

			table.addRow(personIdStr, firstName, lastName, contactNoStr, gender, maritalStatus, dobStr, isActiveStr,
					addressLine1, city, postCodeStr, country, lastOnline, title, passportNo, aboutMe,
					PERSON_TYPE == "HOST" ? salaryExpectationStr : salaryProvidedStr,
					PERSON_TYPE == "HOST" ? validVisaStr : numberOfKidsStr,
					PERSON_TYPE == "HOST" ? drivingLicenseStr : ageOfKidsStr,
					PERSON_TYPE == "HOST" ? hobbies : hasPyhsicalDisabilityStr,
					PERSON_TYPE == "HOST" ? supervisesChildOfAgeStr : "", PERSON_TYPE == "HOST" ? qualification : "",
					preferredLanguage, ratingsStr);

			table.addRow("----------", "----------", "----------", "----------", "----------", "----------",
					"----------", "----------", "----------", "----------", "----------", "----------", "----------",
					"----------", "----------", "----------", "----------", "----------", "----------", "----------",
					"----------", "----------", "----------", "----------");
			count++;
		}

		System.out.println(table.toString());

		if (count == 0) {
			System.out.println("Sorry no searches found ! \n");
			searchByPreference(sc);
		}

		int personIdChoosed = 0;

		if (PERSON_TYPE == "HOST") { /////// to do yAHA PE SAMPLE DIYA HAI HOST CHECK KARO BAAD MEIN
			System.out.println(
					"Do you want to book an appointment with any of the searches above? Please enter the respective AU-PAIR ID"
							+ "\n\nOR \n\nEnter 0 to go back to the Main Menu");
			personIdChoosed = sc.nextInt();
			if (personIdChoosed != 0) {
				//// TO DO call the BOOK APPOINTMENT METHOD
			} else if (personIdChoosed == 0) {
				mainMenu();
			}
		}

		else {
			System.out
					.println("Do you want to contact to any of the searches above? Please enter the respective HOST ID"
							+ "\n\nOR \n\nEnter 0 to go back to the Main Menu");
			personIdChoosed = sc.nextInt();
			if (personIdChoosed != 0) {
				System.out.println("You have successfully contacted the HOST");
				searchByPreference(sc);
			} else if (personIdChoosed == 0) {
				mainMenu();
			}
		}

		// TO DO Merge the book appointment here

//		//// TO DO Display available slots of that person ID
//		sc.close();

	}

	public static void deleteSelfProfile(Scanner sc) throws SQLException {

//		Scanner sc = new Scanner(System.in);
		Character deleteYesOrNo = '\0';

		System.out.println("Are you sure you want to delete your Profile Y/N ?");
		deleteYesOrNo = sc.next().charAt(0);
		if (deleteYesOrNo == 'y' || deleteYesOrNo == 'Y')

		{
			System.out.println("Thank you for being a part of Au-Pair Placement System! :)");
			// to do Throw him to APPlication home page

			Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();
			// Connection conn =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/AU_PAIR_MANAGEMENT?useSSL=false",
			// "root", "password123");
			String query = "{CALL deleteSelfProfile(?)}";
			CallableStatement statementdelete = conn.prepareCall(query);
			statementdelete.setInt("PERSONID", 1);
			statementdelete.executeUpdate();
		} else {
			mainMenu();
		}
	}

	public static void updateProfile(Scanner sc) throws SQLException {
		//

		Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();
		// Connection conn =
		// DriverManager.getConnection("jdbc:mysql://localhost:3306/AU_PAIR_MANAGEMENT?useSSL=false",
		// "root", "password123");

		String firstname = "";
		String lastname = "";
		String email = "";
		String contact = "";
		String aboutme = "";
		String title = "";
		String maritalstatus = "";
		String interviewtimeslot = "";
		String passportnumber = "";
//		Scanner sc = new Scanner(System.in);
//			
		String query = "{CALL updateSelfProfile(?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement statementup = conn.prepareCall(query);

		char wish = 'y';
		int count = 0;
		String updatedchanges = "";
		while (wish == 'y' || wish == 'Y') {
			System.out.println("Which fields do you wish to Update? \n \nPress enter appropriate numbers: "
					+ "\n1) First name \n2) Last name \n3) Email \n4) Contact \n5) About me \n6) Title \n7) Marital Status"
					+ "\n8) Interview Time Slot \n9) Passport No.");

			int updateSelf = sc.nextInt();
			if (updateSelf == 1) {
				System.out.println("Please enter your first name: ");
				firstname = sc.next();
				count++;
				updatedchanges += "First Name: " + firstname;

				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 2) {
				System.out.println("Please enter your last name: ");
				lastname = sc.next();
				count++;
				updatedchanges += "\nLast Name: " + lastname;

				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 3) {
				System.out.println("Please enter your email: ");
				email = sc.next();
				count++;
				updatedchanges += "\nEmail: " + email;

				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 4) {
				System.out.println("Please enter your Contact: ");
				contact = sc.next();
				count++;
				updatedchanges += "\nContact: " + contact;

				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 5) {
				System.out.println("Please enter your About me section: ");
				aboutme = sc.next();
				count++;
				updatedchanges += "\nAbout me:" + aboutme;

				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 6) {
				System.out.println("Please enter your Title: ");
				title = sc.next();
				count++;
				updatedchanges += "\nTitle: " + title;

				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 7) {
				System.out.println("Please enter your Marital Status: ");
				maritalstatus = sc.next();
				count++;
				updatedchanges += "\nMarital Status: " + maritalstatus;

				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 8) {
				System.out.println("Please enter your Interview Time Slots: "); //// doubtsssss
				interviewtimeslot = sc.next();
				count++;
				updatedchanges += "\nInterview Time Slot: " + interviewtimeslot;
				//////
				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			} else if (updateSelf == 9) {
				System.out.println("Please enter your Passport Number: ");
				passportnumber = sc.next();
				count++;
				updatedchanges += "\nPassport number: " + passportnumber;

				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
			}

		}
		//
		statementup.setInt("PERSONID", 1);
		statementup.setString("FIRSTNAME", firstname);
		statementup.setString("LASTNAME", lastname);
		statementup.setString("EMAIL", email);
		statementup.setString("CONTACT", contact);
		statementup.setString("ABOUTME", aboutme);
		statementup.setString("TITLE", title);
		statementup.setString("MARITALSTATUS", maritalstatus);
		statementup.setString("INTERVIEW_TIME_SLOT", interviewtimeslot);
		statementup.setString("PASSPORT", passportnumber);
		statementup.execute();

		System.out.println(+count + " changes updated successfully");
		System.out.println("\n" + updatedchanges);

		mainMenu();
		sc.close();
	}
}
