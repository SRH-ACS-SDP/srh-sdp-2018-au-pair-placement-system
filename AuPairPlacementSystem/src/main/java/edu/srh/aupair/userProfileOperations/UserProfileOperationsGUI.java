package edu.srh.aupair.userProfileOperations;

import java.sql.*;
import java.util.Date;

import javax.naming.StringRefAddr;

import edu.srh.aupair.bookingOperations.BookingGUI;
import edu.srh.aupair.loginOperations.LoginGUI;
import edu.srh.aupair.proposalOperations.ProposalOperationsGUI;

import java.util.*;

public class UserProfileOperationsGUI {
	int person_Id = -1;
	public String loggedInPersonType;
	static IUserProfileOperationsInterface serviceObject;

	public ProposalOperationsGUI proposalGUI;

	public UserProfileOperationsGUI() throws SQLException {
		serviceObject = new UserProfileOperationsService();
		proposalGUI = new ProposalOperationsGUI();
	}

	public static void main(String[] args) throws SQLException {

		try {
			String PERSON_TYPE = "HOST";
			int personId = 1;
			UserProfileOperationsGUI userProfileOperationsObject = new UserProfileOperationsGUI();
			userProfileOperationsObject.getProfile(PERSON_TYPE, personId);
			// userProfileOperationsObject.printSearch();

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void getProfile(String PERSON_TYPE, int personId) throws SQLException {
		person_Id = personId;
		loggedInPersonType = PERSON_TYPE;
		ResultSet result = serviceObject.getProfileDetails(PERSON_TYPE, personId);
		result.next();

		String firstName = "";
		String lastName = "";
		String contactNo = "";
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

		firstName = result.getString("FIRST_NAME");
		lastName = result.getString("LAST_NAME");
		contactNo = result.getString("CONTACT_NO");
		gender = result.getString("GENDER");
		maritalStatus = result.getString("MARITAL_STATUS");
		dob = result.getDate("DOB"); /// check DOB Date time variable
		isActive = result.getBoolean("IS_ACTIVE"); // check boolean type
		addressLine1 = result.getString("ADDRESS_LINE1");
		city = result.getString("CITY");
		postcode = result.getInt("POSTCODE");
		countryName = result.getString("COUNTRY_NAME");
		lastOnline = result.getString("LAST_ONLINE");
		title = result.getString("TITLE");
		passportNo = result.getString("passport_no");
		if (PERSON_TYPE == "AUPAIR") {
			hasValidVisa = result.getBoolean("HAS_VALID_VISA");
			hasSalaryExpectation = result.getBoolean("HAS_SALARY_EXPECTATION");
			hasDrivingLicense = result.getBoolean("HAS_DRIVING_LICENSE");
			hobbies = result.getString("HOBBIES");
			supervisesChildOfAge = result.getString("SUPERVISES_CHILD_OF_AGE");
			eduQualification = result.getString("EDU_QUALIFICATION");
		} else {
			isSalaryProvided = result.getBoolean("IS_SALARY_PROVIDED");
			numberOfKids = result.getInt("NUMBER_OF_KIDS");
			ageOfKids = result.getInt("AGE_OF_KIDS");
			hasPyhsicalDisability = result.getBoolean("HAS_PHYSICAL_DISABILITY");
		} // check boolean type
		aboutMe = result.getString("ABOUT_ME");

		if (PERSON_TYPE == "AUPAIR") {
			System.out.println("Profile Details are : \n\n1) First Name: " + firstName + "\n2) Last Name: " + lastName
					+ "\n3) Contact No: " + contactNo + "\n4) Gender: " + gender + "\n5) Marital Status: "
					+ maritalStatus + "\n6) Date Of Birth: " + dob + "\n7) Is Active User: " + isActive
					+ "\n8) Address: " + addressLine1 + "\n9) City: " + city + "\n10) Postcode: " + postcode
					+ "\n11) Country: " + countryName + "\n12)Last Online: " + lastOnline + "\n13)Title: " + title
					+ "\n14) Passport Number: " + passportNo + "\n15) Has a Valid Visa: " + hasValidVisa
					+ "\n16) Has Salary Expectations: " + hasSalaryExpectation + "\n17)Has Driving License: "
					+ hasDrivingLicense + "\n18) Hobbies: " + hobbies + "\n19) Supervises Age of Chidren: "
					+ supervisesChildOfAge + "\n20) Educational Qualification: " + eduQualification + "\n21) About me: "
					+ aboutMe);
		} else if (PERSON_TYPE == "HOST") {
			System.out.println("Profile Details are : \n1) First Name: " + firstName + "\t\n2) Last Name: " + lastName
					+ "\n3) Contact No: " + contactNo + "\n4) Gender: " + gender + "\n5) Marital Status: "
					+ maritalStatus + "\n6) Date Of Birth: " + dob + "\n7) Is Active User: " + isActive
					+ "\n8) Address: " + addressLine1 + "\n9) City: " + city + "\n10) Postcode: " + postcode
					+ "\n11) Country: " + countryName + "\n12) Last Online: " + lastOnline + "\n13) Title: " + title
					+ "\n14) Passport Number: " + passportNo + "\n15) Is Salary Provided: " + isSalaryProvided
					+ "\n16) Number Of Kids: " + numberOfKids + "\n17) Age of Kids: " + ageOfKids
					+ "\n18) Does any of your kid have Physical Disability: " + hasPyhsicalDisability
					+ "\n19) About me: " + aboutMe);
		}
		mainMenu(person_Id);
	}

	public void mainMenu(int person_Id) throws SQLException {

		Scanner sc = new Scanner(System.in);

		System.out.println("\n =============== MAIN MENU =============== \n"
				+ "\nPlease enter the appropriate actions to be performed : \n\n 1) Update your profile \n 2) Perform Search "
				+ " \n 3) Delete your profile \n 4) View Proposals \n 5) Logout  ");

		int userInput = sc.nextInt();
		if (userInput == 1) {
			updateProfile(sc);
		} else if (userInput == 2) {
			searchByPreference(sc);
		} else if (userInput == 3) {
			deleteSelfProfile(sc);
		} else if (userInput == 4) {
			viewProposals(person_Id);
		} else if (userInput == 5) {
			logout(sc);
		}
	}

	public void logout(Scanner sc) throws SQLException {

		char logout = '\0';
		System.out.println("\n Are you sure you want to logout of the system? Y/N");
		logout = sc.next().charAt(0);
		if (logout == 'Y' || logout == 'y') {
			System.out.println("\nSuccessfully Logged out!\n");
			LoginGUI login = new LoginGUI();
			person_Id = 0;
			login.loginUser();
		} else {
			mainMenu(person_Id);
		}
	}

	public void viewProposals(int personId) throws SQLException {

		int proposalId = 0;
		String auPairName = "";
		String hostName = "";
		String tasksForAuPair = "";
		String workingHoursProposed = "";
		String RemunerationsProposed = "";
		String holidaysProposed = "";
		boolean travelCosts = false;
		String travelCostsStr = String.valueOf(travelCosts);
		String jobOfferedByHost = "";
		String jobAcceptedByAuPair = "";
		
		String PERSON_TYPE = loggedInPersonType;
		ResultSet result = serviceObject.viewProposals(personId, PERSON_TYPE);
		result.next();

		BuildTable table = new BuildTable();

		System.out.println("\n =============== PROPOSALS =============== \n");

		table.addRow("PROPOSAL ID   ||  ", "      NAME         ||  ", "Tasks for Au Pair   ||  ",
				"Working Hours Proposed  ||  ", "Remuneration Proposed  ||  ", "Holidays Proposed   ||  ",
				"Travel Costs  ||  ","Job accepted by Au Pair  ||  " , "Job proposed by Host  ||  " );

		int count = 0;
		while (result.next()) {
			proposalId = result.getInt("PROPOSAL_ID");
			tasksForAuPair = result.getString("TASKS_FOR_AU_PAIR");
			workingHoursProposed = result.getString("WORKING_HOURS_PROPOSED");
			RemunerationsProposed = result.getString("REMUNERATIONS_PROPOSED");
			holidaysProposed = result.getString("HOLIDAYS_PROPOSED");
			travelCosts = result.getBoolean("TRAVEL_COSTS");
			String proposalIdStr = String.valueOf(proposalId);
			hostName = result.getString("HOSTNAME");
			auPairName = result.getString("AUPAIRNAME");
			jobAcceptedByAuPair = result.getString("JOB_ACCEPTED_BY_AU-PAIR");
			jobOfferedByHost = result.getString("JOB_OFFERED	_BY_HOSTS");
			
			table.addRow("----------", "----------", "----------", "----------", "----------", "----------",
					"----------", "----------", "----------");
			table.addRow(proposalIdStr, PERSON_TYPE == "HOST" ? auPairName : hostName, tasksForAuPair,
					workingHoursProposed, RemunerationsProposed, holidaysProposed, travelCostsStr, jobAcceptedByAuPair,
					jobOfferedByHost);
			count++;
		}
		System.out.println(table.toString());
		if (count == 0) {
			System.out.println("Sorry no Proposals found !!! \n");
			mainMenu(person_Id);
		} else {
			if (loggedInPersonType == "AUPAIR") {
				proposalGUI.saveProposalResponseByAuPair();
			}
		}

		// TO DO call the Proposal Operations.java where you have the option to accept
		// or reject the proposals
	}

	public void searchByPreference(Scanner sc) throws SQLException {
		String PERSON_TYPE = loggedInPersonType; ///// REMEMEBER TO REMOVE THIS AND UPAR WALE update MEIN AFTER U GET
													///// INPUTS FROM
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
		String contactNo = "";

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
		int hostId = 0;
		
		int count =0;
		
		while (searchMore == 'y' || searchMore == 'Y') {

			System.out.println(
					"\nWhat parameters would you like to choose for searching? \n\nChoose the following options: ");
			if (PERSON_TYPE == "AUPAIR") {
				System.out.println(
						" \n1) Gender \n2) Country \n3) City \n4) Random Search \n5) Preferred Language \n6) Ratings");
			} else if (PERSON_TYPE == "HOST")
				System.out.println(
						"\n1) Gender  \n2) Country \n3) City \n4) Random Search \n5) Preferred Language \n6) Ratings \n7) Qualification");
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
			} else if (searchOptions == 7 && PERSON_TYPE =="HOST") {
				System.out.println("Enter the Qualification you want to search: ");
				qualification = sc.next();
				searchedParameter += "\nQualification: " + qualification;
				System.out.println("Do you want to add more parameters to your search criteria Y/N ?");
				searchMore = sc.next().charAt(0);
			} else if (searchOptions == 2) {
				System.out.println("Enter the name of the Country you want to search: ");
				country = sc.next();
				searchedParameter += "\nCountry: " + country;
				System.out.println("Do you want to add more parameters to your search criteria Y/N ?");
				searchMore = sc.next().charAt(0);
			} else if (searchOptions == 3) {
				System.out.println("Enter the name of the City you want to search: ");
				city = sc.next();
				searchedParameter += "\nCity: " + city;
				System.out.println("Do you want to add more parameters to your search criteria Y/N ?");
				searchMore = sc.next().charAt(0);
			} else if (searchOptions == 4) {
				System.out.println("Enter any Random search on the basis of which you want to search: ");
				randomSearch = sc.next();
				searchedParameter += "\nRandom Search: " + randomSearch;
				System.out.println("Do you want to add more parameters to your search criteria Y/N ?");
				searchMore = sc.next().charAt(0);
			} else if (searchOptions == 5) {
				System.out.println("Enter the preferred language you want to search: ");
				preferredLanguage = sc.next();
				searchedParameter += "\nPreferred Language " + preferredLanguage;
				System.out.println("Do you want to add more parameters to your search criteria Y/N ?");
				searchMore = sc.next().charAt(0);
			} else if (searchOptions == 6) {
				System.out.println("Enter the rating between 1 to 5: ");
				ratings = sc.nextInt();
				searchedParameter += "\nRatings" + ratings;
				System.out.println("Do you want to add more parameters to your search criteria Y/N ?");
				searchMore = sc.next().charAt(0);
			}
		}
		
		System.out.println("Displaying the results based on following parameters :\n" + searchedParameter + "\n");
		
		
		printSearch(personId, firstName, lastName, contactNo, gender, maritalStatus, dob, isActive, addressLine1, city, postcode, 
				country, lastOnline, title, passportNo, validVisa, salaryExpectation, drivingLicense, hobbies, supervisesChildOfAge, 
				qualification, hostId, aboutMe, preferredLanguage, ratings, randomSearch, PERSON_TYPE, numberOfKids, ageOfKids, 
				hasPyhsicalDisability, salaryProvided, count);
		
		int personIdContacted =0;
		contactHost(personIdContacted , PERSON_TYPE, gender, qualification, country, city, randomSearch, preferredLanguage, 
				ratings, personId, firstName, lastName, contactNo, maritalStatus, dob, isActive, addressLine1, postcode, lastOnline,
				title, passportNo, validVisa, salaryExpectation, drivingLicense, hobbies, supervisesChildOfAge, hostId, aboutMe, numberOfKids,
				ageOfKids, hasPyhsicalDisability, salaryProvided, count);
	
		

		int personIdChoosen = 0;

		if (PERSON_TYPE == "HOST") { /////// to do yAHA PE SAMPLE DIYA HAI HOST CHECK KARO BAAD MEIN
			System.out.println(
					"Do you want to book an appointment with any of the searches above? Please enter the respective AU-PAIR ID"
							+ "\n\nOR \n\nEnter 0 to go back to the Main Menu");
			personIdChoosen = sc.nextInt();
			if (personIdChoosen != 0) {
				BookingGUI bookingGUI = new BookingGUI();
				bookingGUI.bookingOperation(personIdChoosen, hostId);

			} else if (personIdChoosen == 0) {
				mainMenu(person_Id);
			}
		}
	}
	
	public void contactHost (int personIdContacted, String PERSON_TYPE, String gender,String qualification, String country, String city,
			String randomSearch, String preferredLanguage, int ratings, int personId, String firstName, String lastName, String contactNo,  
			String maritalStatus, Date dob, Boolean isActive, 
			String addressLine1, int postcode, String lastOnline, String title, String passportNo, boolean validVisa,
			boolean salaryExpectation,boolean drivingLicense, String hobbies, String supervisesChildOfAge, int hostId, String aboutMe, 
			int numberOfKids, int ageOfKids, boolean hasPyhsicalDisability,
			boolean salaryProvided, int count ) throws SQLException
	{	
		Scanner sc=new Scanner(System.in);
		char contactYesOrNo = '\0';
		personIdContacted = 0;
		String email ="";

		if (loggedInPersonType=="AUPAIR")
		{
			System.out.println("\nDo you want to contact any of the HOST's from above? Insert Y for Yes/N to go back to the main menu\n");
			contactYesOrNo =sc.next().charAt(0);
			if(contactYesOrNo=='y' || contactYesOrNo=='Y')
			{
				System.out.println("\nPlease enter the respective HOST ID from the above searches to contact");
				personIdContacted=sc.nextInt();
				
				ResultSet result = serviceObject.searchByPreference(personIdContacted, PERSON_TYPE, gender, qualification, country, city,
						randomSearch, preferredLanguage, ratings);
				
				result.next();
				email = result.getString("EMAIL");
				 firstName = result.getString("FIRST_NAME");
				 lastName = result.getString("LAST_NAME");
				// To do Can add Send email method here if required!!!
				
				System.out.println("\nCongratulations you have successfully contacted " + firstName + " " + lastName + " , HOST ID = " +personIdContacted +
						"\n\nEmail Sent to :" +email);
				
				System.out.println("\n");
				
				printSearch(personId, firstName, lastName, contactNo, gender, maritalStatus, dob, isActive, addressLine1, city, postcode, 
						country, lastOnline, title, passportNo, validVisa, salaryExpectation, drivingLicense, hobbies, supervisesChildOfAge, 
						qualification, hostId, aboutMe, preferredLanguage, ratings, randomSearch, PERSON_TYPE, numberOfKids, ageOfKids, 
						hasPyhsicalDisability, salaryProvided, count);

				contactHost(personIdContacted , PERSON_TYPE, gender, qualification, country, city, randomSearch, preferredLanguage, 
						ratings, personId, firstName, lastName, contactNo, maritalStatus, dob, isActive, addressLine1, postcode, lastOnline,
						title, passportNo, validVisa, salaryExpectation, drivingLicense, hobbies, supervisesChildOfAge, hostId, aboutMe, numberOfKids,
						ageOfKids, hasPyhsicalDisability, salaryProvided, count);
				
			}
			else {
				mainMenu(person_Id);
			}
			
		}
	}
	
	public void printSearch(int personId, String firstName, String lastName, String contactNo, String gender, String maritalStatus, Date dob, Boolean isActive, 
							String addressLine1, String city, int postcode, String country, String lastOnline, String title, String passportNo, boolean validVisa,
							boolean salaryExpectation,boolean drivingLicense, String hobbies, String supervisesChildOfAge, String qualification,int hostId, String aboutMe, 
							String preferredLanguage, int ratings, String randomSearch, String PERSON_TYPE, int numberOfKids, int ageOfKids, boolean hasPyhsicalDisability,
							boolean salaryProvided, int count ) throws SQLException	 {
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

		ResultSet result = serviceObject.searchByPreference(personId, PERSON_TYPE, gender, qualification, country, city,
				randomSearch, preferredLanguage, ratings);
		
		count =0;

		while (result.next()) {
			
			personId = result.getInt("PERSON_ID");
			firstName = result.getString("FIRST_NAME");
			lastName = result.getString("LAST_NAME");
			contactNo = result.getString("CONTACT_NO");
			gender = result.getString("GENDER");
			maritalStatus = result.getString("MARITAL_STATUS");
			dob = result.getDate("DOB"); /// check DOB Date time variable
			isActive = result.getBoolean("IS_ACTIVE"); // check boolean type
			addressLine1 = result.getString("ADDRESS_LINE1");
			city = result.getString("CITY");
			postcode = result.getInt("POSTCODE");
			country = result.getString("COUNTRY_NAME");
			lastOnline = result.getString("LAST_ONLINE");
			title = result.getString("TITLE");
			passportNo = result.getString("passport_no");
			if (PERSON_TYPE == "HOST") {
				validVisa = result.getBoolean("HAS_VALID_VISA");
				salaryExpectation = result.getBoolean("HAS_SALARY_EXPECTATION");
				drivingLicense = result.getBoolean("HAS_DRIVING_LICENSE");
				hobbies = result.getString("HOBBIES");
				supervisesChildOfAge = result.getString("SUPERVISES_CHILD_OF_AGE");
				qualification = result.getString("EDU_QUALIFICATION");
				hostId = serviceObject.getHostId(person_Id);
			} else {
			} // check boolean type
			aboutMe = result.getString("ABOUT_ME");
			preferredLanguage = result.getString("LANGUAGES");
			if(ratings!=0){
				ratings = result.getInt("RATINGS");
			}

			String personIdStr = String.valueOf(personId);
			String validVisaStr = String.valueOf(validVisa);
			String drivingLicenseStr = String.valueOf(drivingLicense);
//			String contactNoStr = String.valueOf(contactNo);
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
			


			table.addRow(personIdStr, firstName, lastName, contactNo, gender, maritalStatus, dobStr, isActiveStr,

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
			Scanner sc =new Scanner(System.in);
			System.out.println("Sorry no searches found ! \n");
			searchByPreference(sc);
		}
	}

	public void deleteSelfProfile(Scanner sc) throws SQLException {
		Character deleteYesOrNo = '\0';
		int personId = person_Id;
		System.out.println("Are you sure you want to delete your Profile Y/N ?");
		deleteYesOrNo = sc.next().charAt(0);
		if (deleteYesOrNo == 'y' || deleteYesOrNo == 'Y') {
			System.out.println("Thank you for being a part of Au-Pair Placement System! :)");
			// to do Throw him to APPlication home page
			serviceObject.deleteSelfProfile(personId);
		} else {
			mainMenu(person_Id);
		}
	}

	public void updateProfile(Scanner sc) throws SQLException {
		String firstname = "";
		String lastname = "";
		String contact = "";
		String aboutme = "";
		String title = "";
		String maritalstatus = "";
		String passportnumber = "";
		char wish = 'y';
		int count = 0;
		String updatedchanges = "";
		while (wish == 'y' || wish == 'Y') {
			System.out.println("Which fields do you wish to Update? \n \nPress enter appropriate numbers: "
					+ "\n1) First name \n2) Last name \n3) Passport No. \n4) Contact \n5) About me \n6) Title \n7) Marital Status");

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
				System.out.println("Please enter your passport No.: ");
				passportnumber = sc.next();
				count++;
				updatedchanges += "\nPassport no.: " + passportnumber;

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
			}
		}

		serviceObject.updateProfile(person_Id, firstname, lastname, contact, aboutme, title, maritalstatus,
				passportnumber);
		System.out.println(+count + " changes updated successfully");
		System.out.println("\n" + updatedchanges);
		mainMenu(person_Id);
	}
}
