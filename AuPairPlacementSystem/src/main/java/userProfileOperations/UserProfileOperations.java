package userProfileOperations;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserProfileOperations {
//	Connection conn = null;
//	static Statement mystat;

//	public UserProfileOperations() {
//		try {
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AU_PAIR_MANAGEMENT", "root", "password123");
//			mystat = conn.createStatement();
//		} catch (Exception e) {
//		}
//	}

	public static void main(String[] args) {

		try {

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AU_PAIR_MANAGEMENT", "root", "password123");
			Statement mystat = conn.createStatement();
			Date db = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dob = sdf.format(db);

			String userPassportNo = "L12345RT";
			String personTypeId = "AUPAIR";
			String passportNo = "123456";

			String query= viewSelfProfileQuery(passportNo);
			ResultSet myRs = mystat.executeQuery(query);
			if (myRs.next()) {
				int key = mystat.executeUpdate(
						"INSERT INTO PERSON (PERSON_TYPE,LAST_NAME,PASSWORD,FIRST_NAME,EMAIL,CONTACT_NO,GENDER,\r\n"
								+ "MARITAL_STATUS,DOB,IS_ACTIVE,LAST_ONLINE,TITLE,ABOUT_ME,passport_no) " + " VALUES ('"
								+ personTypeId
								+ "','Parineeti','password', 'TANDON' , 'a@b.com' , '12345' ,'female' , 'yes' ,'" + dob
								+ "' ,1,'2018-05-30','title','aboutme', 'L12345RT')",
						1);

				System.out.println("Key gernertared" + key);

				ResultSet myRs2 = mystat.getGeneratedKeys();

				if (myRs2.next()) {
					System.out.println("Auto Generated Primary Key " + myRs2.findColumn("LAST_NAME"));

				}
				int personId = myRs2.getInt(1);

				if (personTypeId.toUpperCase().equals("HOST")) {
					mystat.executeUpdate(
							"INSERT INTO HOST (PERSON_ID,IS_SALARY_PROVIDED,NUMBER_OF_KIDS,AGE_OF_KIDS,HAS_PHYSICAL_DISABILITY) VALUES ( '"
									+ personId + "',1,'3','5', 0)");

				}
				if (personTypeId.toUpperCase().equals("AUPAIR")) {
					mystat.executeUpdate(
							"INSERT INTO au_pair (PERSON_ID,HAS_VALID_VISA,HAS_SALARY_EXPECTATION,HAS_DRIVING_LICENSE,HOBBIES,SUPERVISES_CHILD_OF_AGE,EDU_QUALIFICATION) VALUES "
									+ "( '" + personId + "', 1 ,1,1,'soccer','10','BTech')");

				}

			} else
				System.out.println("User Does not exist!");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static String viewSelfProfileQuery(String passportNo) {
		return "select * from Person where passportNo = '" + passportNo + "'";
	}

	public static void updateSelfProfile() {
	}

	public static void deleteSelfProfile() {
	}

}
