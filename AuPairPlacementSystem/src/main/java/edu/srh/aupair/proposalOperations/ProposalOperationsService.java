package edu.srh.aupair.proposalOperations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import edu.srh.aupair.bookingOperations.BookingRepository;

public class ProposalOperationsService implements IProposalOperations {

	
	public int saveProposalDetailsByHost(String tasksForAuPair, String workingHrsProposed, String remunerationsProposed,
			String holidaysProposed, boolean travelCosts, int activeInterviewId, String proposedStartDate,
			String proposedEndDate) throws SQLException {
		ProposalOperationsRepository proposalOperationsRepository = new ProposalOperationsRepository();
		int proposalId = 
				proposalOperationsRepository.saveProposalDetailsByHost(tasksForAuPair,
						workingHrsProposed, remunerationsProposed, holidaysProposed, travelCosts, 
						activeInterviewId, proposedStartDate, proposedEndDate);
		return proposalId;
	}

	public int[] acceptProposalByAuPair(int proposalIdForAcceptance) {
		ProposalOperationsRepository proposalOperationsRepository = new ProposalOperationsRepository();
		int[] ids = null;
		try {
			ids = proposalOperationsRepository.acceptProposalByAuPair(proposalIdForAcceptance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
		
	}

	public void saveRatingAndFeedback(int hostID, int auPairId, int ratings, String comments) {
		ProposalOperationsRepository proposalOperationsRepository = new ProposalOperationsRepository();
		try {
			proposalOperationsRepository.saveRatingAndFeedback(hostID, auPairId, ratings, comments);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public int rejectProposalByAupair(int proposalIdForRejection) {
		ProposalOperationsRepository proposalOperationsRepository = new ProposalOperationsRepository();
		int propId = 0;
		try {
			propId = proposalOperationsRepository.rejectProposalByAupair(proposalIdForRejection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propId;
		
	}

	

}
