package edu.srh.aupair.loginOperations;

public class LoginService implements IloginServiceInterface {

	
	public  int loginUser (String userName, String userPassword,  String personType)
	{
			
		LoginRepository obj=new LoginRepository();
		int personid = obj.loginUser(userName, userPassword, personType);
		return personid;
	}

}
