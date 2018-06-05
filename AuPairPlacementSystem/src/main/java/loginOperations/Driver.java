package org.sonatype.examples.JpaAuPairDemo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;;

public class Driver {

	private static final java.sql.Date Dob = null;
	private static final boolean IS_SALARY_PROVIDED = false;
	private static final int NUMBER_OF_KIDS = 0;
	private static final int AGE_OF_KIDS = 0;
	private static final boolean HAS_PHYSICAL_DISABILITY = false;

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
		boolean isSlaaryProvided;
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

		try {

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AU_PAIR_MANAGEMENT?useSSL=false",
					"root", "qwerty@12345");

			Date db = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String dob = sdf.format(db);

			java.sql.Date latestOnlineTime = new java.sql.Date(db.getTime());

			// to take input from user
			Scanner input = new Scanner(System.in);
			System.out.println(" Do you want to register yourself as Host user or Au Pair user ");
			System.out.println("Enter 1 to register as Host user or enter 2 to register as Au Pair user: ");
			// String user_input = input.nextLine();
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
				System.out.println("thecount" + the_count); // to remove later
				

				if (the_count == 0) {
					System.out.println("Enter first name: ");
					firstName = input.next();
					System.out.println("Enter last name: ");
					lastName = input.next();
					System.out.println("Enter password: ");
					userPassword = input.next();
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
					System.out.println("Enter title: ");
					title = input.next();
					;
					System.out.println(" About me: ");
					aboutMe = input.next();
					// host table starts
					// search for time stamp and bit
					System.out.println("Is salaray Provided?");
					isSlaaryProvided = input.nextBoolean();
					System.out.println("Number of kids: ");
					noOfKids = input.nextInt();
					System.out.println("Age of kid: ");
					ageOfKid = input.nextInt();
					System.out.println("Has physical disability");
					hasPhysicalDisability = input.nextBoolean();

					String registerHostUserQuery = "{CALL registerAHostUser(?,?,? ,?,?,?,?,? ,? ,?,?,?,?,?, ?,?,?,?,?)}";
					CallableStatement myStmt = conn.prepareCall(registerHostUserQuery);
					myStmt.setString(1, personType);
					myStmt.setString(2, lastName);
					myStmt.setString(3, userPassword);
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

					System.out.println("host id entered " + hostId);

				} else {
					System.out.println("you alreday exist as a host. try again"); // messge change
				}

				else if (value == 2) // for Au pair
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
					System.out.println("thecount" + count); // to remove later
					
					// System.out.println("Enter person type");	
					

					// ResultSet myRs = mystat.executeQuery(search);

					if (count == 0) {
						
						System.out.println("Enter first name: ");
						firstName = input.next();
						System.out.println("Enter last name: ");
						lastName = input.next();
						System.out.println("Enter password: ");
						userPassword = input.next();
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
						System.out.println("Enter title: ");
						title = input.next();
						
						System.out.println(" About me: ");
						aboutMe = input.next();
						System.out.println("Enter passport number: ");
						passportNumber = input.next();
						// au pair table starts
						System.out.println("Do you have a valid visa?");
						hasValidVisa = input.nextBoolean();
						System.out.println("What is your salary expectation?");
						hasSalaryExpectation = input.nextBoolean();
						System.out.println("Do you have a driving license");
						hasDrivingLicense = input.nextBoolean();
						System.out.println("What are your hobbies?");
						hobbies = input.next();
						System.out.println("Supervises child of age?");
						supervisesChildOfage = input.next();
						System.out.println("What is your education qualifiaction");
						educationQualification = input.next();
						// TODO
						String registerHostUserQuery = "{CALL `registerAuPairUser`(?,?,? ,?,?,?,?,? ,? ,?,?,?,?,?, ?,?,?,?,?)}";
						CallableStatement myStmt = conn.prepareCall(registerHostUserQuery);
						myStmt.setString(1, personType);
						myStmt.setString(2, lastName);
						myStmt.setString(3, userPassword);
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

						System.out.println("host id entered " + hostId);
					} else {
						System.out.println(" No input given");
					}

					/*
					 * String passportNumber = "north12"; String personType = "HOST"; String
					 * lastName = "west"; String userPassword = "1234"; String firstName ="north12";
					 * String emailid ="a@b.com"; int contactNo = 12345; String gender="female";
					 * String maritalStatus="single"; boolean isActive= true; String
					 * title="bhavana title"; String aboutMe = "rtrt";
					 */

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
