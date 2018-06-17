package edu.srh.aupair.bookingOperations;

import java.sql.CallableStatement;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class BookingRepository {
	Connection conn;

	BookingRepository() {
		try {
			conn = edu.srh.aupair.utilities.utilities.getConnectionString();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getInterviewSlotForAuPair(int AU_PAIR_ID) throws SQLException {
		String query = "SELECT * FROM interview_availability  where AU_PAIR_ID = " + AU_PAIR_ID;
		CallableStatement stmt = conn.prepareCall(query);
		return stmt.executeQuery(query);
	}

	public int bookingRepository(int interviewId, int hostId) throws SQLException {
		String query;
		query = "{CALL `saveActiveInterviews`(?,?,?)}";
		CallableStatement cs = conn.prepareCall(query);
		cs.setInt(1, interviewId);
		cs.setInt(2, hostId);
		cs.registerOutParameter(3, Types.INTEGER);
		cs.execute();
		int activeInterviewId = cs.getInt(3);
		return activeInterviewId;
	}

	public int getAuPairIdFromPersonId(int personId) throws SQLException {
		String query;
		query = "{CALL `getAuPairIdFromPersonId`(?,?)}";
		CallableStatement cs = conn.prepareCall(query);
		cs.setInt(1, personId);
		cs.registerOutParameter(2, Types.INTEGER);
		cs.execute();
		int auPairId = cs.getInt(2);
		return auPairId;
	}
}