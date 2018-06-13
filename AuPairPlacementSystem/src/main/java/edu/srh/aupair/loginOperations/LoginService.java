package edu.srh.aupair.loginOperations;

public class LoginRepository {

	
	public static void loginUser (String userName, String userPassword,  String personType)
	{
			
		edu.srh.aupair.loginOperations.LoginConnectionManager.loginUser(userName, userPassword, personType);
	}

}
