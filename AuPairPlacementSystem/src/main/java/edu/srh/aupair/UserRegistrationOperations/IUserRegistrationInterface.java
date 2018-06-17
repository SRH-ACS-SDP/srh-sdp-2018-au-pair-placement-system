package edu.srh.aupair.UserRegistrationOperations;

import java.sql.Date;

public interface IUserRegistrationInterface {

	int getCountryIdFromCountryName(String country, int countryCurrencyId);

	int verifyUserExistenceInSystem(String personType, String passportNumber, String emailid);

	int insertIntoInterviewSchedule(String fromTime, String toTime, int auPairId);

	int registerNewHostUser(HostUser hostUser);

	int registerNewAuPairUser(AuPairUser aupairUser);
}