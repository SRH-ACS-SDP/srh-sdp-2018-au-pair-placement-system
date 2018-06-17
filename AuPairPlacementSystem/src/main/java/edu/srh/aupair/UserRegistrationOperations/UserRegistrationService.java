package edu.srh.aupair.UserRegistrationOperations;

import java.sql.SQLException;

public class UserRegistrationService implements IUserRegistrationInterface {

	public int verifyUserExistenceInSystem(String personType, String passportNumber, String emailid) {
		UserRegistrationRepository userRegistrationRepository = null;
		int userExists = 0;
		try {
			userRegistrationRepository = new UserRegistrationRepository();
			userExists = userRegistrationRepository.verifyUserExistenceInSystem(personType, passportNumber, emailid);
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

	public int insertIntoInterviewSchedule(String fromTime, String toTime, int auPairId) {

		UserRegistrationRepository userRegistrationRepository = null;
		int interviewId = -1;
		try {
			userRegistrationRepository = new UserRegistrationRepository();
			interviewId = userRegistrationRepository.insertIntoInterviewSchedule(fromTime, toTime, auPairId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return interviewId;
	}

	public int registerNewHostUser(HostUser hostUser) {

		UserRegistrationRepository userRegistrationRepository = null;
		int personIdForCreatedHost = 0;
		try {
			userRegistrationRepository = new UserRegistrationRepository();
			personIdForCreatedHost = userRegistrationRepository.registerNewHostUser(hostUser);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return personIdForCreatedHost;
	}

	public int registerNewAuPairUser(AuPairUser aupairUser) {

		UserRegistrationRepository userRegistrationRepository = null;
		int personIdForCreatedHost = 0;
		try {
			userRegistrationRepository = new UserRegistrationRepository();
			personIdForCreatedHost = userRegistrationRepository.registerNewAuPairUser(aupairUser);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return personIdForCreatedHost;
	}
}
