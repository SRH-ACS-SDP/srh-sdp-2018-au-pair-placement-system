package edu.srh.aupair.proposalOperations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class ProposalOperationsRepository {

	Connection conn;

	ProposalOperationsRepository() {
		try {
			conn = edu.srh.aupair.utilities.utilities.getConnectionString();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int saveProposalDetailsByHost(String tasksForAuPair, String workingHrsProposed, String remunerationsProposed,
			String holidaysProposed, boolean travelCosts, int activeInterviewId, String proposedStartDate,
			String proposedEndDate) throws SQLException {
		String saveProposalDetailsQuery = "{Call saveProposalDetails(?,?,?,?,?,?,?,?,?)}";
		CallableStatement myStmt = conn.prepareCall(saveProposalDetailsQuery);
		myStmt.setInt(1, activeInterviewId);
		myStmt.setString(2, tasksForAuPair);
		myStmt.setString(3, workingHrsProposed);
		myStmt.setString(4, remunerationsProposed);
		myStmt.setString(5, holidaysProposed);
		myStmt.setBoolean(6, travelCosts);
		myStmt.setString(7, proposedStartDate);
		myStmt.setString(8, proposedEndDate);
		myStmt.registerOutParameter(9, Types.INTEGER);
		myStmt.execute();
		int proposaId = myStmt.getInt(9);
		return proposaId;
	}

	public int[] acceptProposalByAuPair(int proposalIdForAcceptance) throws SQLException {
		String query = "{CALL `acceptProposalByAuPair`(?,?,?,?)}";
		CallableStatement cs = conn.prepareCall(query);
		cs.setInt(1, proposalIdForAcceptance);
		cs.registerOutParameter(2, Types.INTEGER);
		cs.registerOutParameter(3, Types.INTEGER);
		cs.registerOutParameter(4, Types.INTEGER);
		cs.execute();

		int[] returnIds = new int[10];
		returnIds[0] = cs.getInt(2); // activeinterviewid
		returnIds[1] = cs.getInt(3); // host id
		returnIds[2] = cs.getInt(4); // au pair id

		return returnIds;

	}

	public int rejectProposalByAupair(int proposalIdForRejection) throws SQLException {
		String query = "{CALL `rejectProposalByAuPair`(?,?)}";
		CallableStatement cs = conn.prepareCall(query);
		cs.setInt(1, proposalIdForRejection);
		// cs.setInt(2, activeIntId);
		cs.registerOutParameter(2, Types.INTEGER);
		cs.execute();
		int activeInterviewId = cs.getInt(2);
		return activeInterviewId;
	}

	public void saveRatingAndFeedback(int hostID, int auPairId, int ratings, String comments) throws SQLException {
		String query;
		query = "{CALL `saveRatingsAndFeedback`(?,?,?,?,?,?)}";
		CallableStatement stat = conn.prepareCall(query);
		stat.setInt(1, hostID);
		stat.setInt(2, auPairId);
		stat.setInt(3, ratings);
		stat.setString(4, comments);
		stat.setBoolean(5, false);
		stat.registerOutParameter(6, Types.INTEGER);
		stat.execute();

	}
}
