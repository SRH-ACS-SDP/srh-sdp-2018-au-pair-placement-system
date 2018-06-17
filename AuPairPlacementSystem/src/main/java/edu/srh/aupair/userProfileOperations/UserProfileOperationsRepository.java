package edu.srh.aupair.userProfileOperations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UserProfileOperationsRepository {
	Connection connection;
	public UserProfileOperationsRepository() throws SQLException {
		connection=edu.srh.aupair.utilities.utilities.getConnectionString();
	}
	public ResultSet getProfileDetails(String persontype,int personId) throws SQLException {
		String query = "{CALL getProfileDetails(?, ?)}";
		CallableStatement statement = connection.prepareCall(query);
		statement.setInt("PERSONID",personId);
		statement.setString("PERSONTYPE",persontype);
		ResultSet result = statement.executeQuery();
		return result;
	}
	
	public void updateProfile(int personId,String firstname,String lastname,String contact,String aboutme,String title,String maritalstatus,String passportnumber) throws SQLException {
		String query = "{CALL updateSelfProfile(?,?,?,?,?,?,?,?)}";
		CallableStatement statement = connection.prepareCall(query);
		statement.setInt("PERSONID", personId);
		statement.setString("FIRSTNAME", firstname);
		statement.setString("LASTNAME", lastname);
		statement.setString("CONTACT", contact);
		statement.setString("ABOUTME", aboutme);
		statement.setString("TITLE", title);
		statement.setString("MARITALSTATUS", maritalstatus);
		statement.setString("PASSPORT", passportnumber);
		statement.execute();
	}
	
	public ResultSet searchByPreference
	(int personId,String persontype,String gender,String qualification,String country,String city,
	String randomSearch,String preferredLanguage,int rating) throws SQLException {
		
		String query = "{CALL searchByPreference(?,?,?,?,?,?,?,?,?)}";
		CallableStatement statement = connection.prepareCall(query);
		statement.setInt("PERSONID",personId);
		statement.setString("PERSONTYPE",persontype);
		statement.setString("GENDER", gender);
		statement.setString("QUALIFICATION", qualification);
		statement.setString("COUNTRY", country);
		statement.setString("CITY", city);
		statement.setString("RANDOMSEARCH", randomSearch);
		statement.setString("PREFERREDLANGUAGE", preferredLanguage);
		statement.setInt("RATINGS",rating);
		ResultSet result = statement.executeQuery();
		return result;
	}
	
	public void deleteSelfProfile(int personId) throws SQLException {
		String query = "{CALL deleteSelfProfile(?)}";
		CallableStatement statementdelete = connection.prepareCall(query);
		statementdelete.setInt("PERSONID", 1);
		statementdelete.executeUpdate();
	}
	
	public ResultSet viewProposals(int personId,String persontype) throws SQLException {
		String query = "{CALL viewProposal(?,?)}";
		CallableStatement statementview = connection.prepareCall(query);
		statementview.setInt("PERSONID",personId);
		statementview.setString("PERSONTYPE",persontype);
		ResultSet result = statementview.executeQuery();
		
		System.out.println(personId+persontype);
		//System.out.println();
		return result;
	}
	
	public int getHostId(int personId) throws SQLException {
		String query = "{CALL `getHostIdFromPersonId`(?,?)}";
		CallableStatement cs = connection.prepareCall(query);
		cs.setInt(1, personId);
		cs.registerOutParameter(2, Types.INTEGER);
		cs.execute();
		int hostId = cs.getInt(2);
		return hostId;
	}
	}