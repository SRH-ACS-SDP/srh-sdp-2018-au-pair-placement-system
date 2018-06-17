package edu.srh.aupair.bookingOperations;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IbookingServiceInterface {

	int bookingOperation(int interviewId, int hostId) throws SQLException;

	String[] getInterviewSlotForAuPair(int AU_PAIR_ID) throws SQLException;

	int getAuPairIdFromPersonId(int personId);
}
