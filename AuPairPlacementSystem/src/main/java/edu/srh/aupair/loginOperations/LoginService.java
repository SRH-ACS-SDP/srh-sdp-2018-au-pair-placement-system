package edu.srh.aupair.loginOperations;

import java.sql.SQLException;

public class LoginService implements IloginServiceInterface {

	
	public  String[] loginUser (String userName,   String personType, String userPassword) throws SQLException
	{
			
		LoginRepository obj=new LoginRepository();
		String[] ids = obj.loginUser(userName, personType ,userPassword );
		return ids;
	}

}
