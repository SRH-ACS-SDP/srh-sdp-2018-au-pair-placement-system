package edu.srh.aupair.UserRegistrationOperations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UserRegistrationRepository {

	Connection connection = null;

	public UserRegistrationRepository() throws SQLException {
		connection = edu.srh.aupair.utilities.utilities.getConnectionString();
	}

	public int verifyUserExistenceInSystem(String personType, String passportNumber, String emailid)
			throws SQLException {

		String query = "{CALL registerUser(?, ? ,?, ?)}";
		CallableStatement stmt = connection.prepareCall(query);

		stmt.setString(1, passportNumber);
		stmt.setString(2, personType);
		stmt.setString(3, emailid);
		stmt.registerOutParameter(4, Types.INTEGER);
		stmt.execute();
		int the_count = stmt.getInt(4);
		return the_count;
	}

	public int getCountryIdFromCountryName(String country, int countryCurrencyId) throws SQLException {
		String query1 = "select country_currency_id from COUNTRY_CURRENCY where COUNTRY_NAME = '" + country + "'";
		CallableStatement myStmt1 = connection.prepareCall(query1);
		ResultSet rs = myStmt1.executeQuery(query1);
		if (rs.next()) {
			countryCurrencyId = rs.getInt(1);
		}
		return countryCurrencyId;
	}

	public int registerNewHostUser(String personType, String firstName, String lastName, String emailid,
			String contactNo, String gender, String maritalStatus, String languages, String proficiency,
			int countryCurrencyId, String address, String city, int postCode, String title, String aboutMe,
			String passportNumber, boolean isActive, String hashedUserPassword, Date latestOnlineTime,
			boolean IS_SALARY_PROVIDED, int NUMBER_OF_KIDS, int AGE_OF_KIDS, boolean HAS_PHYSICAL_DISABILITY)
			throws SQLException {

		String registerHostUserQuery = "{CALL registerAHostUser(?,?,? ,?,?,?,?,? ,? ,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement myStmt = connection.prepareCall(registerHostUserQuery);
		myStmt.setString(1, personType);
		myStmt.setString(2, lastName);
		myStmt.setString(3, hashedUserPassword);
		myStmt.setString(4, firstName);
		myStmt.setString(5, emailid);
		myStmt.setString(6, contactNo);
		myStmt.setString(7, gender);
		myStmt.setString(8, maritalStatus);
		myStmt.setDate(9, latestOnlineTime); // to do DOB
		myStmt.setString(10, languages);
		myStmt.setString(11, proficiency);
		myStmt.setString(12, address);
		myStmt.setString(13, city);
		myStmt.setInt(14, postCode);
		myStmt.setInt(15, countryCurrencyId);
		myStmt.setBoolean(16, isActive);
		myStmt.setDate(17, latestOnlineTime);
		myStmt.setString(18, title);
		myStmt.setString(19, aboutMe);
		myStmt.setString(20, passportNumber);
		myStmt.setBoolean(21, IS_SALARY_PROVIDED);
		myStmt.setInt(22, NUMBER_OF_KIDS);
		myStmt.setInt(23, AGE_OF_KIDS);
		myStmt.setBoolean(24, HAS_PHYSICAL_DISABILITY);
		myStmt.registerOutParameter(25, Types.INTEGER);
		myStmt.execute();
		int personId = myStmt.getInt(25);

		// System.out.println("host id testing " + hostId);
		return personId;

	}

	public int registerNewAuPairUser(String personType, String firstName, String lastName, String emailid,
			String contactNo, String gender, String maritalStatus, String languages, String proficiency,
			int countryCurrencyId, String address, String city, int postCode, String title, String aboutMe,
			String passportNumber, boolean hasValidVisa, boolean hasSalaryExpectation, boolean hasDrivingLicense,
			String hobbies, String supervisesChildOfage, String educationQualification, boolean isActive,
			String hashedUserPassword, Date latestOnlineTime, String fromTime, String toTime) throws SQLException {
		String registerHostUserQuery = "{CALL `registerAuPairUser`(?,?,? ,?,?,?,?,? ,? ,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement myStmt = connection.prepareCall(registerHostUserQuery);
		myStmt.setString(1, personType);
		myStmt.setString(2, lastName);
		myStmt.setString(3, hashedUserPassword);
		myStmt.setString(4, firstName);
		myStmt.setString(5, emailid);
		myStmt.setString(6, contactNo);
		myStmt.setString(7, gender);
		myStmt.setString(8, maritalStatus);
		myStmt.setDate(9, latestOnlineTime);
		myStmt.setString(10, languages);
		myStmt.setString(11, proficiency);
		myStmt.setString(12, address);
		myStmt.setString(13, city);
		myStmt.setInt(14, postCode);
		myStmt.setInt(15, countryCurrencyId);
		myStmt.setBoolean(16, isActive);
		myStmt.setDate(17, latestOnlineTime);
		myStmt.setString(18, title);
		myStmt.setString(19, aboutMe);
		myStmt.setString(20, passportNumber);
		myStmt.setBoolean(21, hasValidVisa);
		myStmt.setBoolean(22, hasSalaryExpectation);
		myStmt.setBoolean(23, hasDrivingLicense);
		myStmt.setString(24, hobbies);
		myStmt.setString(25, supervisesChildOfage);
		myStmt.setString(26, educationQualification);
		myStmt.setString(27, fromTime);
		myStmt.setString(28, toTime);
		myStmt.registerOutParameter(29, Types.INTEGER);

		myStmt.execute();
		int personId = myStmt.getInt(29);
		return personId;

	}

	public int insertIntoInterviewSchedule(String fromTime, String toTime, int auPairId) throws SQLException {
		String insertIntoInterviewScheduleQuery = "{CALL `insertIntoInterviewSchedule`(?,?,?,?)}";
		CallableStatement cs = connection.prepareCall(insertIntoInterviewScheduleQuery);
		cs.setInt(1, auPairId);
		cs.setString(2, fromTime);
		cs.setString(3, toTime);
		cs.registerOutParameter(4, Types.INTEGER);
		cs.execute();
		int interviewId = cs.getInt(4);
		return interviewId;
	}

}
