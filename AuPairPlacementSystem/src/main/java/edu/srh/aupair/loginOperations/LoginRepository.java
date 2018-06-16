package edu.srh.aupair.loginOperations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import org.mindrot.jbcrypt.BCrypt;

public class LoginRepository {

	
	public  int loginUser(String userName, String userPassword,  String personType) 
	{
		try {
			
			Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();			
			
			String getEncryptedPassword = "{Call VerifyEncryptedPassword(?,?,?,?)}";
			CallableStatement cs = conn.prepareCall(getEncryptedPassword);
			cs.setString(1, userName);
			cs.setString(2, personType);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.INTEGER);
			cs.execute();
			String hashedPassword = cs.getString(3);
			int person_id = cs.getInt(4);
			// System.out.println(hashedPassword);
			
			if (BCrypt.checkpw(userPassword, hashedPassword)) {
				
				System.out.println("Login successful");
				//System.out.println(person_id);
				return person_id;
			}

			else {
				System.out.println("Failed to login...Try again with correct password");
				return 0;
			}

		}

		catch (Exception ex) {
			System.out.println("Failed to login...Try again with correct password");
		}
		return 0;
	}

}
