package edu.srh.aupair.UserRegistrationOperations;

import java.sql.Date;
import java.sql.SQLException;

public class UserRegistrationService implements IUserRegistrationInterface {

	public int verifyUserExistenceInSystem(String personType, String passportNumber) {
		UserRegistrationRepository userRegistrationRepository = null;
		int userExists = 0;
		try {
			userRegistrationRepository = new UserRegistrationRepository();
			userExists = userRegistrationRepository.verifyUserExistenceInSystem(personType, passportNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userExists;

	}

	public int getCountryIdFromCountryName(String country, int countryCurrencyId) {
		int ccId = 0;
		try {
			UserRegistrationRepository userRegistrationRepository = new UserRegistrationRepository();
			ccId = userRegistrationRepository.getCountryIdFromCountryName(country, countryCurrencyId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ccId;

	}

	public int registerNewHostUser(String personType, String firstName, String lastName, String emailid,
			String contactNo, String gender, String maritalStatus, String languages, String proficiency,
			int countryCurrencyId, String address, String city, int postCode, String title, String aboutMe,
			String passportNumber, boolean isActive, String hashedUserPassword, Date latestOnlineTime,
			boolean isSalaryProvided, int noOfKids, int ageOfKid, boolean hasPhysicalDisability) {
		{

			UserRegistrationRepository userRegistrationRepository = null;int personId = -1;
			try {
				userRegistrationRepository = new UserRegistrationRepository();
				personId = userRegistrationRepository.registerNewHostUser(personType, firstName, lastName, emailid, contactNo,
						gender, maritalStatus, languages, proficiency, countryCurrencyId, address, city, postCode,
						title, aboutMe, passportNumber, isActive, hashedUserPassword, latestOnlineTime,
						isSalaryProvided, ageOfKid, ageOfKid, hasPhysicalDisability);
			} catch (SQLException e) {
				e.printStackTrace();

			}
			return personId;
		}
	}

	
	
//	public int registerNewAuPairUser(String personType, String firstName, String lastName, String emailid,
//			String contactNo, String gender, String maritalStatus, String languages, String proficiency,
//			int countryCurrencyId, String address, String city, int postCode, String title, String aboutMe,
//			String passportNumber, boolean hasValidVisa, boolean hasSalaryExpectation, boolean hasDrivingLicense,
//			String hobbies, String supervisesChildOfage, String educationQualification, boolean isActive,
//			String hashedUserPassword, Date latestOnlineTime) 
//	{
//		UserRegistrationRepository userRegistrationRepository = null;
//		int auPairId = -1; 
//		try {
//			userRegistrationRepository = new UserRegistrationRepository();
//			auPairId = userRegistrationRepository.registerNewAuPairUser(personType, firstName, lastName, emailid, contactNo, gender,
//					maritalStatus, languages, proficiency, countryCurrencyId, address, city, postCode, title,
//					aboutMe, passportNumber, hasValidVisa, hasSalaryExpectation, hasDrivingLicense, hobbies,
//					supervisesChildOfage, educationQualification, isActive, hashedUserPassword, 
//					latestOnlineTime);
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//		}
//		return auPairId;	
//		
//	}

	public int insertIntoInterviewSchedule(String fromTime, String toTime, int auPairId) {

		UserRegistrationRepository userRegistrationRepository = null;int interviewId = -1;
		try {
			userRegistrationRepository = new UserRegistrationRepository();
			interviewId = userRegistrationRepository.insertIntoInterviewSchedule( fromTime, toTime, auPairId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return interviewId;
	}

	public int registerNewAuPairUser(String personType, String firstName, String lastName, String emailid,
			String contactNo, String gender, String maritalStatus, String languages, String proficiency,
			int countryCurrencyId, String address, String city, int postCode, String title, String aboutMe,
			String passportNumber, boolean hasValidVisa, boolean hasSalaryExpectation, boolean hasDrivingLicense,
			String hobbies, String supervisesChildOfage, String educationQualification, boolean isActive,
			String hashedUserPassword, Date latestOnlineTime, String fromTime, String toTime) {

		UserRegistrationRepository userRegistrationRepository = null;
		int auPairId = -1; 
		try {
			userRegistrationRepository = new UserRegistrationRepository();
			auPairId = userRegistrationRepository.registerNewAuPairUser(personType, firstName, lastName, emailid, contactNo, gender,
					maritalStatus, languages, proficiency, countryCurrencyId, address, city, postCode, title,
					aboutMe, passportNumber, hasValidVisa, hasSalaryExpectation, hasDrivingLicense, hobbies,
					supervisesChildOfage, educationQualification, isActive, hashedUserPassword, 
					latestOnlineTime,fromTime,toTime);
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return auPairId;	
	}
}
