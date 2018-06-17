package edu.srh.aupair.UserRegistrationOperations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UserRegistrationRepository {

	Connection connection = null;

	public UserRegistrationRepository() throws SQLException {
		connection = edu.srh.aupair.utilities.utilities.getConnectionString();
	}

	public int verifyUserExistenceInSystem(String personType, String passportNumber, String emailid)
			throws SQLException {

		String query = "{CALL verifyExistingUser(?, ? ,?, ?)}";
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

	public int registerNewHostUser(HostUser hostUser) throws SQLException {
		
		Date db = new Date(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dob = sdf.format(db);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date(0);
		String y = dateFormat.format(date);

		java.sql.Date latestOnlineTime = new java.sql.Date(db.getTime());
		
		String registerHostUserQuery = "{CALL registerAHostUser(?,?,? ,?,?,?,?,? ,? ,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement myStmt = connection.prepareCall(registerHostUserQuery);
		myStmt.setString(1, "HOST");
		myStmt.setString(2, hostUser.getLastName());
		myStmt.setString(3, hostUser.getHashedUserPassword());
		myStmt.setString(4, hostUser.getFirstName());
		myStmt.setString(5, hostUser.getEmailid());
		myStmt.setString(6, hostUser.getContactNo());
		myStmt.setString(7, hostUser.getGender());
		myStmt.setString(8, hostUser.getMaritalStatus());
		myStmt.setString(9, hostUser.getDOB()); // to do DOB
		myStmt.setString(10,hostUser.getLanguages());
		myStmt.setString(11,hostUser.getProficiency());
		myStmt.setString(12,hostUser.getAddress());
		myStmt.setString(13, hostUser.getCity());
		myStmt.setInt(14, hostUser.getPostCode());
		myStmt.setInt(15, hostUser.getCountryCurrencyId());
		myStmt.setBoolean(16, true);
		myStmt.setDate(17, latestOnlineTime);
		myStmt.setString(18, hostUser.getTitle());
		myStmt.setString(19, hostUser.getAboutMe());
		myStmt.setString(20, hostUser.getPassportNumber());
		myStmt.setBoolean(21, hostUser.isIS_SALARY_PROVIDED());
		myStmt.setInt(22, hostUser.getNUMBER_OF_KIDS());
		myStmt.setInt(23, hostUser.getAGE_OF_KIDS());
		myStmt.setBoolean(24, hostUser.isHAS_PHYSICAL_DISABILITY());
		myStmt.registerOutParameter(25, Types.INTEGER);
		myStmt.execute();
		int personId = myStmt.getInt(25);

		// System.out.println("host id testing " + hostId);
		return personId;

		
	}

	public int registerNewAuPairUser(AuPairUser aupairUser) throws SQLException {
		
		Date db = new Date(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dob = sdf.format(db);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date(0);
		String y = dateFormat.format(date);

		java.sql.Date latestOnlineTime = new java.sql.Date(db.getTime());
		
		String registerHostUserQuery = "{CALL `registerAuPairUser`(?,?,? ,?,?,?,?,? ,? ,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement myStmt = connection.prepareCall(registerHostUserQuery);
		myStmt.setString(1, "AUPAIR");
		myStmt.setString(2, aupairUser.getLastName());
		myStmt.setString(3, aupairUser.getHashedUserPassword());
		myStmt.setString(4, aupairUser.getFirstName());
		myStmt.setString(5, aupairUser.getEmailid());
		myStmt.setString(6, aupairUser.getContactNo());
		myStmt.setString(7, aupairUser.getGender());
		myStmt.setString(8, aupairUser.getMaritalStatus());
		myStmt.setDate(9, latestOnlineTime);
		myStmt.setString(10, aupairUser.getLanguages());
		myStmt.setString(11, aupairUser.getProficiency());
		myStmt.setString(12, aupairUser.getAddress());
		myStmt.setString(13, aupairUser.getCity());
		myStmt.setInt(14, aupairUser.getPostCode());
		myStmt.setInt(15, aupairUser.getCountryCurrencyId());
		myStmt.setBoolean(16, true);
		myStmt.setDate(17, latestOnlineTime);
		myStmt.setString(18, aupairUser.getTitle());
		myStmt.setString(19, aupairUser.getAboutMe());
		myStmt.setString(20, aupairUser.getPassportNumber());
		myStmt.setBoolean(21, aupairUser.isHasValidVisa());
		myStmt.setBoolean(22, aupairUser.isHasSalaryExpectation());
		myStmt.setBoolean(23, aupairUser.isHasDrivingLicense());
		myStmt.setString(24, aupairUser.getHobbies());
		myStmt.setString(25, aupairUser.getSupervisesChildOfage());
		myStmt.setString(26, aupairUser.getEducationQualification());
		myStmt.setString(27, aupairUser.getInterviewAvailabilityFromTime());
		myStmt.setString(28, aupairUser.getInterviewAvailabilityToTime());
		myStmt.registerOutParameter(29, Types.INTEGER);
		myStmt.execute();
		int personId = myStmt.getInt(29);
		return personId;

		
	}

}
