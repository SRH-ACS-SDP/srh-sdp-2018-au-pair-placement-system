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

	public String[] getInterviewSlotForAuPair(int AU_PAIR_ID ) throws SQLException {
		
		String query;
		query = "{CALL `getInterviewSchedule`(?,?,?,?)}";
		CallableStatement cs = conn.prepareCall(query);
		cs.setInt(1, AU_PAIR_ID);
		cs.registerOutParameter(2, Types.VARCHAR); 
		cs.registerOutParameter(3, Types.VARCHAR);
		cs.registerOutParameter(4, Types.INTEGER);
		cs.execute();
		String fromTime = cs.getString(2);
		String toTime = cs.getString(3);
		int interviewId = cs.getInt(4);
		String iId = String.valueOf(interviewId);
		
		String[] interviewSchedule = new String[5];
		interviewSchedule[0] = fromTime;
		interviewSchedule[1] = toTime;
		interviewSchedule[2] = iId;
		
		
		return interviewSchedule;
			
		
		
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