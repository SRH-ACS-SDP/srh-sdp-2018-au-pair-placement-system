package edu.srh.aupair.userProfileOperations;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfileOperationsService implements IUserProfileOperationsInterface{
	UserProfileOperationsRepository repository;
	public UserProfileOperationsService() throws SQLException {
		repository=new UserProfileOperationsRepository();
	}
	
	public ResultSet getProfileDetails(String persontype,int personId) throws SQLException{
		ResultSet result=repository.getProfileDetails(persontype,personId);
		return result;
	}

	public void updateProfile(int personId,String firstname,String lastname,String email,String contact,String aboutme,String title,String maritalstatus,String interviewtimeslot,String passportnumber) throws SQLException{
		repository.updateProfile(personId,firstname,lastname,email,contact,aboutme,title,maritalstatus,interviewtimeslot,passportnumber);
	}

	public ResultSet searchByPreference(int personId,String persontype,String gender,String qualification,String country,String city,String randomSearch,String preferredLanguage,int rating) throws SQLException{
		ResultSet result=repository.searchByPreference(personId,persontype,gender,qualification,country,city,randomSearch,preferredLanguage,rating);
		return result;
	}

	public void deleteSelfProfile(int personId) throws SQLException{
		repository.deleteSelfProfile(personId);
	}

	public ResultSet viewProposals(int personId,String persontype) throws SQLException{
		ResultSet result=repository.viewProposals(personId,persontype);
		return result;
	}
}