package edu.srh.aupair.bookingOperations;

import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;

import edu.srh.aupair.proposalOperations.IProposalOperations;
import edu.srh.aupair.proposalOperations.ProposalOperationsGUI;
import edu.srh.aupair.proposalOperations.ProposalOperationsService;

public class BookingGUI {

		
	static IbookingServiceInterface bookingServiceObject;

	public 	BookingGUI() throws SQLException {
		bookingServiceObject = new BookingService();
	}
	

	public void bookingOperation(int auPairpersonId,int hostId ) throws SQLException {

		//IbookingServiceInterface serviceObj = new BookingService();

		int auPairId = bookingServiceObject.getAuPairIdFromPersonId(auPairpersonId);
		// int AU_PAIR_ID = 23; // to do get this from search grid

		System.out.println("**ENTER THE AU PAIR YOU WANT TO SCHEDULE AN INTERVIEW WITH**"); // to do : uncomment later
		// int interviewId = 3;

		ResultSet activeInterviewIds = bookingServiceObject.getInterviewSlotForAuPair(auPairId);
		int interviewId = -1;
		// ResultSet activeInterviewIds =
		// serviceObj.getInterviewSlotForAuPair(interviewId, AU_PAIR_ID);

		// to do Assume that user has clicked on an AU pair, and pass that AU pair id to
		// db and fetch his/her interview availability schedule

		if (activeInterviewIds.next()) 
		{
			String availableInterviewSlot = activeInterviewIds.getString(3) + " TO " + activeInterviewIds.getString(4);
			interviewId = activeInterviewIds.getInt(1);
			System.out.println("Available interview slot" + availableInterviewSlot);
			System.out.println(interviewId);
			
			//int hostId = 1; // to be changed later, this value should come from previous search method
			System.out.println("Do you want to book this slot? \n");

			System.out.println("Press 1 for yes \n");
			Scanner input = new Scanner(System.in);
			int userWantsToBookInterviewSlot = input.nextInt();
			System.out.println("Please enter your  \n");

			if (userWantsToBookInterviewSlot == 1) 
			{
				//IbookingServiceInterface ibookingServiceInterface = new BookingService();
				int activeInterviewId = bookingServiceObject.bookingOperation(interviewId, hostId);
				System.out.println(activeInterviewId + "Congratulations your interview has been scheduled");

				//Assuming that interview has happened
				//send email to do
				
				ProposalOperationsGUI proposalOperationsGUI = new ProposalOperationsGUI();
				proposalOperationsGUI.saveProposalDetailsByHost(activeInterviewId);
				
				proposalOperationsGUI.saveProposalResponseByAuPair();				
				
				
			} 
			else 
			{
				System.out.println("No slots booked yet");
			}
			
		} 
		else 
		{
			System.out.println("No interview slots available for this Au pair");
		}

		

	}
}
