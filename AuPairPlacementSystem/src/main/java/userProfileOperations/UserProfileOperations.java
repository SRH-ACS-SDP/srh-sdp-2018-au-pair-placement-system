package userProfileOperations;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
public class UserProfileOperations {
//private static final int CONTACT_NO = 0;

//	Connection conn = null;
//	static Statement mystat;

//	public UserProfileOperations() {
//		try {
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AU_PAIR_MANAGEMENT", "root", "password123");
//			mystat = conn.createStatement();
//		} catch (Exception e) {
//		}
//	}

	public static void main(String[] args) throws SQLException {
//		
//		updateProfile();
//		searchProfile();
//		viewProposals();
		Connection conn = null;
		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AU_PAIR_MANAGEMENT", "root",
					"password123");

			CallableStatement statement = conn.prepareCall("{call getProfileDetails(?,?)}");

			
			String PERSON_TYPE = "HOST";
			
			
			statement.setInt("PERSON_ID", 1);
			statement.setString("PERSON_TYPE", "HOST");
			
			ResultSet rs = statement.executeQuery();
			
			String firstName = rs.getString("FIRSTNAME");
			String lastName = rs.getString("LASTNAME");
			int contactNo = rs.getInt("CONTACT_NO");
			String gender = rs.getString("GENDER");
			String maritalStatus = rs.getString("MARITAL_STATUS");
			Date dob = rs.getDate("DOB"); /// check DOB Date time variable
			Boolean isActive = rs.getBoolean("IS_ACTIVE"); // check boolean type
			String addressLine1 = rs.getString("ADDRESS_LINE1");
			String city = rs.getString("CITY");
			int postcode = rs.getInt("POSTCODE");
			String lastOnline = rs.getString("LAST_ONLINE");
			String title = rs.getString("TITLE");
			String passportNo = rs.getString("passport_no");
			Boolean hasValidVisa = rs.getBoolean("HAS_VALID_VISA");
			Boolean hasSalaryExpectation = rs.getBoolean("HAS_SALARY_EXPECTATION");
			Boolean hasDrivingLicense = rs.getBoolean("HAS_DRIVING_LICENSE");
			String hobbies = rs.getString("HOBBIES");
			String supervisesChildOfAge = rs.getString("SUPERVISES_CHILD_OF_AGE");
			String eduQualification = rs.getString("EDU_QUALIFICATION");
			Boolean isSalaryProvided = rs.getBoolean("IS_SALARY_PROVIDED");
			int numberOfKids = rs.getInt("NUMBER_OF_KIDS");
			int ageOfKids = rs.getInt("AGE_OF_KIDS");
			Boolean hasPyhsicalDisability = rs.getBoolean("HAS_PHYSICAL_DISABILITY"); // check boolean type
			String aboutMe = rs.getString("ABOUT_ME");
			
			
				
			if(PERSON_TYPE =="AUPAIR") {
			System.out.println("Profile Details are : \n1)First Name: " +firstName + "\n2)Last Name: " +lastName + "\n3)Contact No: " +contactNo +
					"\n4)Gender: " +gender + "\n5)Marital Status: " +maritalStatus + "\n6)Date Of Birth: " +dob + "\n7)Is Active User: " +isActive + 
					"\n8)Address: " +addressLine1 +"\n9)City: " +city + "\n10)Postcode: " +postcode + "\n11)Last Online: " +lastOnline + 
					"\n12)Title: " +title + "\n13)Passport Number: " +passportNo + "\n14)Has a Valid Visa: " +hasValidVisa +
					"\n15)Has Salary Expectations: " +hasSalaryExpectation + "\n16)Has Driving License: " +hasDrivingLicense + "\n17)Hobbies: " +hobbies +
					"\n18)Supervises Age of Chidren: " +supervisesChildOfAge + "\n19)Educational Qualification: " +eduQualification + "\n20) About me: " +aboutMe );
			}
			else if(PERSON_TYPE =="HOST") {
			System.out.println("Profile Details are : \n1)First Name: " +firstName + "\n2)Last Name: " +lastName + "\n3)Contact No: " +contactNo +
						"\n4)Gender: " +gender + "\n5)Marital Status: " +maritalStatus + "\n6)Date Of Birth: " +dob + "\n7)Is Active User: " +isActive + 
						"\n8)Address: " +addressLine1 +"\n9)City: " +city + "\n10)Postcode: " +postcode + "\n11)Last Online: " +lastOnline + 
						"\n12)Title: " +title + "\n13)Passport Number: " +passportNo + 	"\n14)Is Salary Provided: " +isSalaryProvided +
						"\n15)Number Of Kids: " +numberOfKids + "\n16)Age of Kids: " +ageOfKids + "\n17)Does any of your kid have Physical Disability: " + hasPyhsicalDisability + 
						"\n18) About me: " +aboutMe);
						}
					
					
					
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Please enter the appropriate actions to be performed : \n 1) Update your profile \n 2) Perform Search "
								+ " \n 3) Delete your profile \n 4) View Proposals \n 5) Logout  ");
			int userInput = sc.nextInt();
			if(userInput == 1)
			{
			 updateProfile(); 
			}
			else if(userInput==2)
			{
				searchProfile();
			}
			else if(userInput==3)
			{
				System.out.println("Are you sure you want to delete your profile ? Y/N");
				Character deleteYN = sc.next().charAt(0);
				if (deleteYN == 'y' || deleteYN =='Y')
				{
				String sql = "DELETE FROM PERSON WHERE PERSON_ID = PERSON_ID"; /// check ek baar

			      statement.executeUpdate(sql);															/////check check
			      System.out.println("Thank you for being a part of Au-Pair Placement System");
			      
//				OR
				
				//		CallableStatement DELETE WALA FROM NIVEDITHA //)
				}
				else {
				System.out.println("Go"); // Ask niveditha about not deleting where to point it
				}
			}
			else if (userInput==4) {
					// Ask niveditha for Proposals Do we need to call the Proposals Operations???
			}
			else if (userInput==5) {
				//////LOGOUT FUNCTIONALITY ASK NIVEDITHA
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
			private static void viewProposals() {
			
}

			private static void searchProfile() {
//				SELECT * from PERSON where LOCATION="", RATING ='' ,AGE = '', TITLE = "";
				Scanner sc = new Scanner(System.in);
				System.out.println("Which Search result do you want to choose? \n 1) or 2) or 3)");
				int useroption = sc.nextInt();
				if (useroption == 1 || useroption ==2 || useroption ==3);
				{
				
				CallableStatement statement = conn.prepareCall("{call getProfileDetails(?,?)}");
				
				
				}
				
				System.out.println("Do you want to book an appointment? ");
}

			private static void updateProfile() throws SQLException { 
				
				Connection conn = null;
				String firstname = null;
				String lastname = null;
				String email = null;
				String contact = null;
				String aboutme = null;
				String title = null;
				String maitalstatus = null;
				String interviewTimeSlot = null;
				String 	passportNo = null;
				Scanner sc = new Scanner(System.in);
				
				
				System.out.println("Which fields do you wish to Update? \n \nPress appropriate numbers: "
						+ "\n1) First name \n2)Last name \n3) Email \n4) Contact \n5) About me \n6) Title \n7) Marital Status"
						+ "\n8) Interview Time Slot \n9 Passport No.");
				
				CallableStatement statementup = conn.prepareCall("{call updateSelfProfile(?,?,?,?,?,?,?,?,?,?)}");
				
				char wish='y';
				while (wish=='y')
				{
				int updateSelf = sc.nextInt();
				if (updateSelf ==1)
				{
				System.out.println("Please enter your first name: ");
				String firstnamenew = sc.next();
				statementup.setString("FIRSTNAME", firstnamenew);
				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
				}
				else if (updateSelf ==2)
				{
				System.out.println("Please enter your last name: ");
				String lastnamenew = sc.next();
				statementup.setString("LASTNAME", lastnamenew);
				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
				}
				else if (updateSelf ==3)
				{
				System.out.println("Please enter your email: ");
				String emailnew = sc.next();
				statementup.setString("EMAIL", emailnew);
				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
				}
				else if (updateSelf ==4)
				{
				System.out.println("Please enter your Contact: ");
				String contactnew = sc.next();
				statementup.setString("CONTACT_NO", contactnew);
				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
				}
				else if (updateSelf ==5)
				{
				System.out.println("Please enter your About me section: ");
				String aboutmenew = sc.next();
				statementup.setString("ABOUT_ME", aboutmenew);
				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
				}
				else if (updateSelf ==6)
				{
				System.out.println("Please enter your Title: ");
				String titlenew = sc.next();
				statementup.setString("TITLE", titlenew);
				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
				}
				else if (updateSelf ==7)
				{
				System.out.println("Please enter your Marital Status: ");
				String maritalstatusnew = sc.next();
				statementup.setString("MARITAL_STATUS", maritalstatusnew);
				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
				}
				else if (updateSelf ==8)
				{
				System.out.println("Please enter your Interview Time Slots: "); ////doubtsssss
				String interviewtimeslotnew = sc.next();
				statementup.setString("inteview_Time_Slot", interviewtimeslotnew);
				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
				}
				else if (updateSelf ==9)
				{
				System.out.println("Please enter your Passport Number: ");
				String passportnumbernew = sc.next();
				statementup.setString("PASSPORT_NO", passportnumbernew);
				System.out.println("Do you wish to update more fields? Y/N ");
				wish = sc.next().charAt(0);
				}
				
				}				
				
			}
}				
				

				
				//ask to add set properties from registration part
//				statement.setString("FIRSTNAME");
//				statement.getInt("CONTACT_NO");
//				statement.getString("GENDER");
//				statement.getString("MARITAL_STATUS");
//				statement.getString("DOB"); /// check DOB Date time variable
//				statement.getBoolean("IS_ACTIVE"); // check boolean type
//				statement.getString("LAST_ONLINE");
//				statement.getString("TITLE");
//				statement.getString("passport_no");

//
//		try {
//
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AU_PAIR_MANAGEMENT", "root", "password123");
//			Statement mystat = conn.createStatement();
//			Date db = new Date();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String dob = sdf.format(db);
//
//			
//			
//			String userPassportNo = "L12345RT";
//			String personTypeId = "AUPAIR";
//			String passportNo = "123456";
//
//			String query= viewSelfProfileQuery(passportNo);
//			ResultSet myRs = mystat.executeQuery(query);
//			
//			if (myRs.next()) {
//				int key = mystat.executeUpdate(
//						"INSERT INTO PERSON (PERSON_TYPE,LAST_NAME,PASSWORD,FIRST_NAME,EMAIL,CONTACT_NO,GENDER,\r\n"
//								+ "MARITAL_STATUS,DOB,IS_ACTIVE,LAST_ONLINE,TITLE,ABOUT_ME,passport_no) " + " VALUES ('"
//								+ personTypeId
//								+ "','Parineeti','password', 'TANDON' , 'a@b.com' , '12345' ,'female' , 'yes' ,'" + dob
//								+ "' ,1,'2018-05-30','title','aboutme', 'L12345RT')",
//						1);
//
//				System.out.println("Key gernertared" + key);
//
//				ResultSet myRs2 = mystat.getGeneratedKeys();
//
//				if (myRs2.next()) {
//					System.out.println("Auto Generated Primary Key " + myRs2.findColumn("LAST_NAME"));
//
//				}
//				int personId = myRs2.getInt(1);
//
//				if (personTypeId.toUpperCase().equals("HOST")) {
//					mystat.executeUpdate(
//							"INSERT INTO HOST (PERSON_ID,IS_SALARY_PROVIDED,NUMBER_OF_KIDS,AGE_OF_KIDS,HAS_PHYSICAL_DISABILITY) VALUES ( '"
//									+ personId + "',1,'3','5', 0)");
//
//				}
//				if (personTypeId.toUpperCase().equals("AUPAIR")) {
//					mystat.executeUpdate(
//							"INSERT INTO au_pair (PERSON_ID,HAS_VALID_VISA,HAS_SALARY_EXPECTATION,HAS_DRIVING_LICENSE,HOBBIES,SUPERVISES_CHILD_OF_AGE,EDU_QUALIFICATION) VALUES "
//									+ "( '" + personId + "', 1 ,1,1,'soccer','10','BTech')");
//
//				}
//
//			} else
//				System.out.println("User Does not exist!");
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//	}
//
//	public static String viewSelfProfileQuery(String passportNo) {
//		return "select * from Person where passportNo = '" + passportNo + "'";
//	}
//
//	public static void updateSelfProfile() {
//	}
//
//	public static void deleteSelfProfile() {
//	}


