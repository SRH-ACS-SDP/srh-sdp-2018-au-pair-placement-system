package edu.srh.aupair.UserRegistrationOperations;

import java.sql.Date;

public interface IUserRegistrationInterface {

	int getCountryIdFromCountryName(String country, int countryCurrencyId);

	int registerNewHostUser(String personType, String firstName, String lastName, String emailid, String contactNo,
			String gender, String maritalStatus, String languages, String proficiency, int countryCurrencyId,
			String address, String city, int postCode, String title, String aboutMe, String passportNumber,
			boolean isActive, String hashedUserPassword,Date latestOnlineTime, 
			boolean isSalaryProvided,int noOfKids,int ageOfKid,boolean hasPhysicalDisability);

	int verifyUserExistenceInSystem(String personType, String passportNumber, String emailid);
	
	int registerNewAuPairUser(String personType, String firstName, String lastName, String emailid, String contactNo, 
			String gender, String maritalStatus, String languages, String proficiency, int countryCurrencyId,
			String address, String city, int postCode, String title,
			String aboutMe, String passportNumber, boolean hasValidVisa, boolean hasSalaryExpectation, 
			boolean hasDrivingLicense, String hobbies,
			String supervisesChildOfage, String educationQualification, boolean isActive, String hashedUserPassword, 
			Date latestOnlineTime, String fromTime, String toTime);
	
	int insertIntoInterviewSchedule(String fromTime, String toTime, int auPairId);

	int registerNewHostUser(HostUser hostUser);

	int registerNewAuPairUser(AuPairUser aupairUser);
}