package edu.srh.aupair.UserRegistrationOperations;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

import java.text.DateFormat;


public class UserRegistration {

	private static final java.sql.Date Dob = null;
	private static final boolean IS_SALARY_PROVIDED = false;
	private static final int NUMBER_OF_KIDS = 0;
	private static final int AGE_OF_KIDS = 0;
	private static final boolean HAS_PHYSICAL_DISABILITY = false;
	public enum Gender 
	{
		  ONE("Female"), 
		  TWO("Male");
		}

	public static void main(String[] args) {
		String personType = "";
		String firstName = "";
		String lastName = "";
		String userPassword = "";
		String emailid = "";
		String contactNo = "";
		String gender = "";
		String maritalStatus = "";
		String dateOfBirth = "";
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
		

		try {

			Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();
			
			String x = "1990-03-30";
			
			Date db = new Date();
			

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String dob = sdf.format(db);
			
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String y =  dateFormat.format(date);			

			java.sql.Date latestOnlineTime = new java.sql.Date(db.getTime());

			
			// to take input from user
			Scanner input = new Scanner(System.in);
			System.out.println("Do you want to register yourself as Host user or Au Pair user \n");
			System.out.println("Enter 1 to register as Host user or enter 2 to register as Au Pair user: \n");
			int value = input.nextInt();

			if (value == 1) // for host
			{
				// System.out.println("Enter person type");
				personType = "HOST";
				System.out.println("Enter passport number: ");
				passportNumber = input.next();

				String query = "{CALL registerUser(?, ? ,?)}";
				CallableStatement stmt = conn.prepareCall(query);

				stmt.setString(1, passportNumber);
				stmt.setString(2, personType);
				stmt.registerOutParameter(3, Types.INTEGER);
				stmt.execute();
				int the_count = stmt.getInt(3);
				//System.out.println("thecount" + the_count); // to remove later

				if (the_count == 0) {
					System.out.println("Enter first name: ");
					firstName = input.next();
					System.out.println("Enter last name: ");
					lastName = input.next();
					System.out.println("Enter password: ");
					userPassword = input.next();
					hashedUserPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());
					
					System.out.println(hashedUserPassword);
					System.out.println("Enter email address: ");
					emailid = input.next();
					System.out.println("Contact number: ");
					contactNo = input.next();
					System.out.println("Gender ");
					gender = input.next();
					System.out.println("Marital Status : ");
					maritalStatus = input.next();
					System.out.println("Date of birth: ");
					dateOfBirth = input.next();
					
//					//Scanner sc = new Scanner(System.in); 
//					System.out.println("Enter bday: "); 
//					String ind = input.nextLine();
//					DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
//					java.sql.Date d = null; 
//					
//					try {
//						d=df.parse(ind);			
//						System.out.println(d);}
//					catch(ParseException e) 
//					{
//					System.out.println("Unable to parse " + ind);
//					}
						
					

					// Check that an unencrypted password matches or not
//					if (BCrypt.checkpw(candidate, hashed))
//					    System.out.println("It matches");
//					else
//					    System.out.println("It does not match");
					
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
					

					String registerHostUserQuery = "{CALL registerAHostUser(?,?,? ,?,?,?,?,? ,? ,?,?,?,?,?, ?,?,?,?,?)}";
					CallableStatement myStmt = conn.prepareCall(registerHostUserQuery);
					myStmt.setString(1, personType);
					myStmt.setString(2, lastName);
					myStmt.setString(3, hashedUserPassword);
					myStmt.setString(4, firstName);
					myStmt.setString(5, emailid);
					myStmt.setString(6, contactNo);
					myStmt.setString(7, gender);
					myStmt.setString(8, maritalStatus);
					myStmt.setDate(9, latestOnlineTime);
					myStmt.setBoolean(10, isActive);
					myStmt.setDate(11, latestOnlineTime);
					myStmt.setString(12, title);
					myStmt.setString(13, aboutMe);
					myStmt.setString(14, passportNumber);
					myStmt.setBoolean(15, IS_SALARY_PROVIDED);
					myStmt.setInt(16, NUMBER_OF_KIDS);
					myStmt.setInt(17, AGE_OF_KIDS);
					myStmt.setBoolean(18, HAS_PHYSICAL_DISABILITY);
					myStmt.registerOutParameter(19, Types.INTEGER);

					myStmt.execute();

					int hostId = myStmt.getInt(19);
					
					System.out.println("***REGISTERATION SUCCESSFUL AS HOST USER***");
					
					callingLoginSteps(input);

				}
				else {
					System.out.println("***YOU ARE AN EXISTING HOST USER. PLEASE LOGIN***");
				}
			} 

			else if (value == 2) 
			{
				personType = "AU-PAIR";
				System.out.println("Enter passport number: ");
				passportNumber = input.next();

				String query1 = "{CALL registerUser(?, ? ,?)}";
				CallableStatement stmt1 = conn.prepareCall(query1);

				stmt1.setString(1, passportNumber);
				stmt1.setString(2, personType);
				stmt1.registerOutParameter(3, Types.INTEGER);
				stmt1.execute();
				int count = stmt1.getInt(3);
				//System.out.println("thecount" + count); 

				if (count == 0) {

					System.out.println("Enter first name: ");
					firstName = input.next();
					System.out.println("Enter last name: ");
					lastName = input.next();
					System.out.println("Enter password: ");
					userPassword = input.next();
					hashedUserPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());
					System.out.println("Enter email address: ");
					emailid = input.next();
					System.out.println("Please provide your contact number: ");
					contactNo = input.next();
					System.out.println("Gender ");
					gender = input.next();
					System.out.println("Marital Status : ");
					maritalStatus = input.next();
					System.out.println("Date of birth: ");
					dateOfBirth = input.next();
					System.out.println("Active on " + db);
					System.out.println("Last online " + db);
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
					
//					System.out.println("Enter your interview availability /n" );
//					System.out.println("Enter Date (format: DD/MM/YYYY");
//					String interviewDate = input.next();
//					System.out.println("Please select the suitable slot : 1. 10am to 12 pm , 2. 12pm to 2pm , 3. 2pm to 4pm , 4. 4pm to 6pm");
//					int interviewSlot = input.nextInt();
//					
//					if(interviewSlot == 1)
//					{
//						String fromTime = "10am";
//						String toTime =  "12pm";
//						String slotOne = interviewDate.concat( "," + fromTime + "," + toTime);
//					}
					
					
					String registerHostUserQuery = "{CALL `registerAuPairUser`(?,?,? ,?,?,?,?,? ,? ,?,?,?,?,?, ?,?,?,?,?,?,?)}";
					CallableStatement myStmt = conn.prepareCall(registerHostUserQuery);
					myStmt.setString(1, personType);
					myStmt.setString(2, lastName);
					myStmt.setString(3, hashedUserPassword);
					myStmt.setString(4, firstName);
					myStmt.setString(5, emailid);
					myStmt.setString(6, contactNo);
					myStmt.setString(7, gender);
					myStmt.setString(8, maritalStatus);
					myStmt.setDate(9, latestOnlineTime);
					myStmt.setBoolean(10, isActive);
					myStmt.setDate(11, latestOnlineTime);
					myStmt.setString(12, title);
					myStmt.setString(13, aboutMe);
					myStmt.setString(14, passportNumber);
					myStmt.setBoolean(15, hasValidVisa);
					myStmt.setBoolean(16, hasSalaryExpectation);
					myStmt.setBoolean(17, hasDrivingLicense);
					myStmt.setString(18, hobbies);
					myStmt.setString(19, supervisesChildOfage);
					myStmt.setString(20, educationQualification);
					myStmt.registerOutParameter(21, Types.INTEGER);

					myStmt.execute();

					int auPairId = myStmt.getInt(21);

					//System.out.println("Au Pair id entered " + auPairId);
					System.out.println("***REGISTERATION SUCCESSFUL AS AU-PAIR USER***");
					
					callingLoginSteps(input);
				} 
				else {
					System.out.println("***YOU ARE AN EXISTING AU PAIR USER. PLEASE LOGIN***");
				}
			} 
			else {
				System.out.println("***SOMETHING WENT WRONG***");
			} 

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void callingLoginSteps(Scanner input) {
		System.out.println("***ENTER 1 TO LOGIN NOW?***");
		int login = input.nextInt();					
		if(login == 1)
		{
			edu.srh.aupair.loginOperations.Login.loginWithUserCredentials();
		}
		else
		{
			System.out.println("***EXIT***");
		}
	}	
}
