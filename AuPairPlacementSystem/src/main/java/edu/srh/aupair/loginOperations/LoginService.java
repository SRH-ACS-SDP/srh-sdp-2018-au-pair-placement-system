package edu.srh.aupair.loginOperations;

public class LoginService implements IloginServiceInterface {

	
	public  void loginUser (String userName, String userPassword,  String personType)
	{
			
		LoginRepository obj=new LoginRepository();
		obj.loginUser(userName, userPassword, personType);
	}

}
