package edu.srh.aupair.bookingOperations;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingService implements IbookingServiceInterface {
	
	public int bookingOperation(int interviewId, int hostId ) throws SQLException
	{
			
		BookingRepository obj = new BookingRepository();
		return obj.bookingRepository(interviewId, hostId);
			
	}

	public ResultSet getInterviewSlotForAuPair(int interviewId, int AU_PAIR_ID) throws SQLException {
		BookingRepository obj = new BookingRepository();
		return obj.getInterviewSlotForAuPair(interviewId, AU_PAIR_ID);
	}
	
}
