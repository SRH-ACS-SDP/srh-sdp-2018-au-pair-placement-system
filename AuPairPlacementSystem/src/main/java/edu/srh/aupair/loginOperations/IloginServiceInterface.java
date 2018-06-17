package edu.srh.aupair.loginOperations;

import java.sql.SQLException;

public interface IloginServiceInterface {

	public String[] loginUser(String userName, String personType, String userPassword) throws SQLException;
}
