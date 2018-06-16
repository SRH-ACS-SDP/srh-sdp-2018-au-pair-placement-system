package edu.srh.aupair.UserRegistrationOperations;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

import edu.srh.aupair.bookingOperations.BookingService;
import edu.srh.aupair.bookingOperations.IbookingServiceInterface;
import edu.srh.aupair.loginOperations.LoginGUI;
import edu.srh.aupair.userProfileOperations.UserProfileOperationsGUI;

public class UserRegistrationGUI {

	public static String personType = "";
	
	public static IUserRegistrationInterface userRegistrationServiceObject;

	public 	UserRegistrationGUI() throws SQLException {
		userRegistrationServiceObject = new  UserRegistrationService();
	}
	
	
	public static void main(String[] args) throws SQLException {

		System.out
				.println("                    <=== WELCOME TO AU PAIR PLACEMENT SYSTEM ===>                        \n");
		
		System.out.println("* ENTER 1 TO LOGIN \n");
		System.out.println("* ENTER 2 TO REGISTER \n");
		Scanner input = new Scanner(System.in);
		int userChoice = input.nextInt();

		if (userChoice == 1) 
		{
			LoginGUI loginGUI = new LoginGUI();
			int personId = loginGUI.loginUser();
				
			if(personId != 0)
			{
				UserProfileOperationsGUI userProfileOperationsGUI = new UserProfileOperationsGUI();
				userProfileOperationsGUI.getProfile(LoginGUI.personType, personId);
			}		
			
		} 
		else if (userChoice == 2)
		{
			UserRegistrationGUI userRegistrationGUI = new UserRegistrationGUI();
			userRegistrationGUI.registerUser();

		} else {
			System.out.println("<=== SORRY, YOU ENTERED A WRONG CHOICE ===>");

		}
	}

	public void registerUser() throws SQLException {

		
		String firstName = "";
		String lastName = "";
		String userPassword = "";
		String emailid = "";
		String contactNo = "";
		String gender = "";
		String maritalStatus = "";
		String dateOfBirth = "";
		String languages = "";
		String proficiency = "";
		String country = "";
		int countryCurrencyId = 0;
		String address = "";
		String city = "";
		int postCode = 0;
		String title = "";
		String aboutMe = "";
		String passportNumber = "";
		boolean isSalaryProvided;
		int noOfKids = 0;
		int ageOfKid = 0;
		boolean hasPhysicalDisability;
		boolean hasValidVisa;
		boolean hasSalaryExpectation;
		boolean hasDrivingLicense;
		String hobbies = "";
		String supervisesChildOfage = "";
		String educationQualification = "";
		boolean isActive = true;
		String hashedUserPassword = "";

		Connection conn = null;

		String x = "1990-03-30";
		Date db = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dob = sdf.format(db);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String y = dateFormat.format(date);

		java.sql.Date latestOnlineTime = new java.sql.Date(db.getTime());

		Scanner input = new Scanner(System.in);
		System.out.println("<=== Do you want to register yourself as Host user or Au Pair user ===>\n");

		System.out.println("<=== Enter 1 to register as Host user or enter 2 to register as Au Pair user: ===> \n ");
		int userChoice = input.nextInt();
		HostUser hostUser = new HostUser();

		if (userChoice == 1) // for host
		{

			personType = "HOST";
			System.out.println(">Enter passport number: ");
			passportNumber = input.next();
			
			System.out.println("Enter email address: ");
			emailid = input.next();
			
			//hostUser.setPassportNumber(input.next());			

			//int the_count = userRegistrationServiceObject.verifyUserExistenceInSystem(personType, passportNumber);

			//IUserRegistrationInterface iUserRegistrationInterface = new UserRegistrationService();
			int the_count = userRegistrationServiceObject.verifyUserExistenceInSystem(personType, passportNumber, emailid);
			
			if (the_count == 0) {
				System.out.println("Enter first name: ");
				firstName = input.next();
				 
				
				
				System.out.println("Enter last name: ");
				lastName = input.next();
				System.out.println("Enter password: ");
				userPassword = input.next();

				hashedUserPassword = encryptPassword(userPassword);

				// System.out.println(hashedUserPassword);
//				System.out.println("Enter email address: ");
//				emailid = input.next();
				System.out.println("Contact number: ");
				contactNo = input.next();
				System.out.println("Gender ");
				gender = input.next();
				System.out.println("Marital Status : ");
				maritalStatus = input.next();
				System.out.println("Date of birth: ");
				dateOfBirth = input.next();
				System.out.println("Preferred Language: ");
				languages = input.next();
				System.out.println("Proficiency (Beginner, Intermediate, Expert): ");
				proficiency = input.next();
				System.out.println("Enter Address Line 1");
				address = input.next();
				System.out.println("City: ");
				city = input.next();
				System.out.println("Postcode: ");
				postCode = input.nextInt();
				System.out.println("Country: ");
				country = input.next();

				System.out.println("Enter title: ");
				title = input.next();
				System.out.println("About me: ");
				aboutMe = input.next();
				System.out.println("Is salary Provided(Enter True or False)?");
				isSalaryProvided = input.nextBoolean();
				System.out.println("Number of kids: ");
				noOfKids = input.nextInt();
				System.out.println("Age of kid: ");
				ageOfKid = input.nextInt();
				System.out.println("Has physical disability(Enter True or False)");
				hasPhysicalDisability = input.nextBoolean();

				countryCurrencyId = userRegistrationServiceObject.getCountryIdFromCountryName(country, countryCurrencyId);

				int personId = userRegistrationServiceObject.registerNewHostUser(personType, firstName, lastName, emailid,
						contactNo, gender, maritalStatus, languages, proficiency, countryCurrencyId, address, city,
						postCode, title, aboutMe, passportNumber, isActive, hashedUserPassword, latestOnlineTime,
						isSalaryProvided, noOfKids, ageOfKid, hasPhysicalDisability);

				System.out.println("***REGISTERATION SUCCESSFUL AS HOST USER***");

				callingLoginSteps(input);
				// callingLoginSteps(input);
			} else {
				System.out.println("***YOU ARE AN EXISTING HOST USER. PLEASE LOGIN***");
				callingLoginSteps(input);
			}
		} // host code ends here

		else if (userChoice == 2) {
			personType = "AU-PAIR";
			
			System.out.println("Enter passport number: ");
			passportNumber = input.next();
			System.out.println("Enter email address: ");
			emailid = input.next();
			
			//IUserRegistrationInterface iUserRegistrationInterface = new UserRegistrationService();
			int the_count = userRegistrationServiceObject.verifyUserExistenceInSystem(personType, passportNumber, emailid);

			// System.out.println("thecount" + count);

			if (the_count == 0) {

				System.out.println("Enter first name: ");
				firstName = input.next();
				System.out.println("Enter last name: ");
				lastName = input.next();
				System.out.println("Enter password: ");
				userPassword = input.next();

				hashedUserPassword = encryptPassword(userPassword);

//				System.out.println("Enter email address: ");
//				emailid = input.next();
				System.out.println("Please provide your contact number: ");
				contactNo = input.next();
				System.out.println("Gender ");
				gender = input.next();
				System.out.println("Marital Status : ");
				maritalStatus = input.next();
				System.out.println("Date of birth: ");
				dateOfBirth = input.next();
				System.out.println("Preferred Language: ");
				languages = input.next();
				System.out.println("Proficiency (Beginner, Intermediate, Expert): ");
				proficiency = input.next();
				System.out.println("Enter Address Line 1");
				address = input.next();
				System.out.println("City: ");
				city = input.next();
				System.out.println("Postcode: ");
				postCode = input.nextInt();
				System.out.println("Country: ");
				country = input.next();

				System.out.println("Enter title: ");
				title = input.next();
				System.out.println("About me: ");
				aboutMe = input.next();
				System.out.println("Do you have a valid visa(Enter True or False)?");
				hasValidVisa = input.nextBoolean();
				System.out.println("Do you have a salary expectation(Enter True or False)?");
				hasSalaryExpectation = input.nextBoolean();
				System.out.println("Do you have a driving license(Enter True or False)");
				hasDrivingLicense = input.nextBoolean();
				System.out.println("What are your hobbies?");
				hobbies = input.next();
				System.out.println("Supervises child of age?");
				supervisesChildOfage = input.next();
				System.out.println("What is your education qualifiaction");
				educationQualification = input.next();

				System.out.println("Enter your interview availability /n");
				System.out.println("Enter Date (format: DD/MM/YYYY");
				String interviewDate = input.next();
				System.out.println(
						"Please select the suitable slot : 1. 10am to 12 pm , 2. 12pm to 2pm , 3. 2pm to 4pm , 4. 4pm to 6pm");
				int interviewSlot = input.nextInt();
				String slotOne = "";
				String fromTime = "";
				String toTime = "";

				if (interviewSlot == 1) {
					fromTime = interviewDate.concat("  " + "10 am");
					toTime = interviewDate.concat("  " + "12 am");
					// System.out.println(slotOne);
				} else if (interviewSlot == 2) {
					fromTime = interviewDate.concat("  " + "12 am");
					toTime = interviewDate.concat("  " + "2 pm");
				} else if (interviewSlot == 3) {
					fromTime = interviewDate.concat("  " + "2 pm");
					toTime = interviewDate.concat("  " + "4 pm");
				} else if (interviewSlot == 4) {
					fromTime = interviewDate.concat("  " + "4 pm");
					toTime = interviewDate.concat("  " + "6 pm");
				}

				countryCurrencyId = userRegistrationServiceObject.getCountryIdFromCountryName(country, countryCurrencyId);

				int personId = userRegistrationServiceObject.registerNewAuPairUser(personType, firstName, lastName,
						emailid, contactNo, gender, maritalStatus, languages, proficiency, countryCurrencyId, address,
						city, postCode, title, aboutMe, passportNumber, hasValidVisa, hasSalaryExpectation,
						hasDrivingLicense, hobbies, supervisesChildOfage, educationQualification, isActive,
						hashedUserPassword, latestOnlineTime, fromTime, toTime);

				// if (auPairId != 0) {
				// // saveInterviewSchedulePreference(conn, fromTime, toTime, auPairId);
				// int interviewId =
				// iUserRegistrationInterface.insertIntoInterviewSchedule(fromTime, toTime,
				// auPairId);
				// System.out.println(auPairId + " " + fromTime + " " + toTime + " " +
				// interviewId);// to do
				//
				// }

				// System.out.println("Au Pair id entered " + auPairId);
				System.out.println("***REGISTERATION SUCCESSFUL AS AU-PAIR USER***");

				callingLoginSteps(input);

			} else {
				System.out.println("***YOU ARE AN EXISTING AU PAIR USER. PLEASE LOGIN***");
				callingLoginSteps(input);
			}
		}

	}

	public static void callingLoginSteps(Scanner input) throws SQLException {

		System.out.println("***ENTER 1 TO LOGIN NOW?***");
		int login = input.nextInt();
		if (login == 1) 
		{
			LoginGUI loginGUI = new LoginGUI();
			int personId = loginGUI.loginUser();
			
			if(personId != 0)
			{
				UserProfileOperationsGUI userProfileOperationsGUI = new UserProfileOperationsGUI();
				userProfileOperationsGUI.getProfile(LoginGUI.personType, personId);
			}
			
			
		} else {
			System.out.println("***EXIT***");
		}
	}
	private static String encryptPassword(String userPassword) {
		String hashedUserPassword;
		hashedUserPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());
		return hashedUserPassword;
	}

}
