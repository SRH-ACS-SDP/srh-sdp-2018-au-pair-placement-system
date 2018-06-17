package edu.srh.aupair.loginOperations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.mindrot.jbcrypt.BCrypt;

public class LoginRepository {
	Connection conn;

	public LoginRepository() throws SQLException {
		conn = edu.srh.aupair.utilities.utilities.getConnectionString();
	}

	public String[] loginUser(String userName, String personType, String userPassword) throws SQLException {

		String getEncryptedPassword = "{Call VerifyEncryptedPassword(?,?,?,?)}";
		CallableStatement cs = conn.prepareCall(getEncryptedPassword);
		cs.setString(1, userName);
		cs.setString(2, personType);
		cs.registerOutParameter(3, Types.VARCHAR);
		cs.registerOutParameter(4, Types.INTEGER);
		cs.execute();

		String[] ids = new String[10];
		String hashedPassword = cs.getString(3);

		int person_id = cs.getInt(4);
		String pid = Integer.toString(person_id);

		ids[0] = hashedPassword;
		ids[1] = pid;
		System.out.println(cs.getString(3));
		return ids;
	}

}
