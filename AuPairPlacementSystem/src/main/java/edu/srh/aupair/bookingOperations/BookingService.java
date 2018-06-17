package edu.srh.aupair.bookingOperations;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingService implements IbookingServiceInterface {

	public int bookingOperation(int interviewId, int hostId) throws SQLException {
		BookingRepository obj = new BookingRepository();
		return obj.bookingRepository(interviewId, hostId);
	}

	public String[] getInterviewSlotForAuPair(int AU_PAIR_ID) throws SQLException {
		BookingRepository obj = new BookingRepository();
		return obj.getInterviewSlotForAuPair(AU_PAIR_ID);
	}

	public int getAuPairIdFromPersonId(int personId) {

		BookingRepository obj = new BookingRepository();
		try {
			return obj.getAuPairIdFromPersonId(personId);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return personId;
	}

}
