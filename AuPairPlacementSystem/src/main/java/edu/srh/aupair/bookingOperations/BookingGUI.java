package edu.srh.aupair.bookingOperations;

import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;

public class BookingGUI {

	public static void main(String[] args) {

		BookingGUI bookingGUI = new BookingGUI();
		try {
			bookingGUI.bookingOperation();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void bookingOperation() throws SQLException {
		int AU_PAIR_ID = 23; // to do get this from search grid

		System.out.println("**ENTER THE AU PAIR YOU WANT TO SCHEDULE AN INTERVIEW WITH**"); // to do : uncomment later
		int interviewId = 3;

		// to do Assume that user has clicked on an AU pair, and pass that AU pair id to
		// db and fetch his/her interview availability schedule
		IbookingServiceInterface serviceObj = new BookingService();
		ResultSet activeInterviewIds = serviceObj.getInterviewSlotForAuPair(interviewId, AU_PAIR_ID);

		if (activeInterviewIds.next()) {
			String availableInterviewSlot = activeInterviewIds.getString(3) + " TO " + activeInterviewIds.getString(4);
			interviewId = activeInterviewIds.getInt(1);
			System.out.println("Available interview slot" + availableInterviewSlot);
			System.out.println(interviewId);
		} else {
			System.out.println("No interview slots available for this Au pair");
		}

		int hostId = 1; // to be changed later, this value should come from previous search method
		System.out.println("Do you want to book this slot? \n");

		System.out.println("Press 1 for yes \n");
		Scanner input = new Scanner(System.in);
		int userWantsToBookInterviewSlot = input.nextInt();

		if (userWantsToBookInterviewSlot == 1) {
			IbookingServiceInterface serviceObj1 = new BookingService();
			int activeInterviewId1 = serviceObj1.bookingOperation(interviewId, hostId);
			System.out.println(activeInterviewId1 + "Congratulations your interview has been scheduled");

		} else {
			System.out.println("No slots booked yet");
		}

		input.close();
	}
}
