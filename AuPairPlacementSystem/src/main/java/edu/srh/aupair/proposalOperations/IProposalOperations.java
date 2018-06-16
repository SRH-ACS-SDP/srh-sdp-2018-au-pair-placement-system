package edu.srh.aupair.proposalOperations;

import java.sql.SQLException;

public interface IProposalOperations {

	public int saveProposalDetailsByHost(String tasksForAuPair, String workingHrsProposed,
			String remunerationsProposed, String holidaysProposed, boolean travelCosts, int activeInterviewId,
			String proposedStartDate, String proposedEndDate);

	public int[] acceptProposalByAuPair(int proposalIdForAcceptance);

	public void saveRatingAndFeedback(int hostID, int auPairId, int ratings, String comments) throws SQLException;
	
	public int rejectProposalByAupair(int proposalIdForRejection);
}
