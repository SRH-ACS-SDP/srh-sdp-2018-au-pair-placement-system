package edu.srh.aupair.bookingOperations;

import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;

import edu.srh.aupair.proposalOperations.IProposalOperations;
import edu.srh.aupair.proposalOperations.ProposalOperationsGUI;
import edu.srh.aupair.proposalOperations.ProposalOperationsService;

public class BookingGUI {

	static IbookingServiceInterface bookingServiceObject;

	public BookingGUI() throws SQLException {
		bookingServiceObject = new BookingService();
	}

	public void bookingOperation(int auPairpersonId, int hostId) throws SQLException {		

		int auPairId = bookingServiceObject.getAuPairIdFromPersonId(auPairpersonId);
		
		System.out.println("**ENTER THE AU PAIR YOU WANT TO SCHEDULE AN INTERVIEW WITH**"); 
		
		String[] activeInterviewIds = bookingServiceObject.getInterviewSlotForAuPair(auPairId);
			
		
		int interviewId = -1;String empty = new String();
		
		if (!activeInterviewIds[0].equals(empty) ) 
		{
			String availableInterviewSlot = activeInterviewIds[0] + " TO " + activeInterviewIds[1];
			
			interviewId = Integer.parseInt(activeInterviewIds[2]);
			
			System.out.println("The available interview slot are " + availableInterviewSlot);			
			System.out.println("Do you want to book this slot? Press 1 for yes \n");

			Scanner input = new Scanner(System.in);
			int userWantsToBookInterviewSlot = input.nextInt();

			if (userWantsToBookInterviewSlot == 1) {
				
				System.out.println(  hostId);
				int activeInterviewId = bookingServiceObject.bookingOperation(interviewId, hostId);
				
				System.out.println("aaa" + activeInterviewId);
				
				System.out.println(activeInterviewId + "Congratulations your interview has been scheduled");

				ProposalOperationsGUI proposalOperationsGUI = new ProposalOperationsGUI();
				proposalOperationsGUI.saveProposalDetailsByHost(activeInterviewId);

				
			} else {
				System.out.println("No slots booked yet");
			}

		} else {
			System.out.println("No interview slots available for this Au pair");
		}

	}
}
