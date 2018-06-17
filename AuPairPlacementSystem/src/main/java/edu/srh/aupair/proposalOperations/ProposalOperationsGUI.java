package edu.srh.aupair.proposalOperations;

import java.sql.SQLException;
import java.util.Scanner;

import edu.srh.aupair.contractGenerationOperations.ContractGenerationService;
import edu.srh.aupair.contractGenerationOperations.IContractGenerationInterface;
import edu.srh.aupair.utilities.SendEmail;

public class ProposalOperationsGUI {

	static IProposalOperations serviceObject;
	static IContractGenerationInterface contractObject;

	public ProposalOperationsGUI() throws SQLException {
		serviceObject = new ProposalOperationsService();
		contractObject = new ContractGenerationService();
	}

	public void saveProposalResponseByAuPair() throws SQLException {
		Scanner input = new Scanner(System.in);
		int activeInterviewId, hostID, auPairId;
		
		System.out.println("Do you want to accept / reject any proposals?");
		System.out.println("----SELECT YOUR CHOICE----" + "\n" + "ENTER 1 for accepting proposals" + "\n"
				+ "ENTER 2 for rejecting proposals");
		int userInput = input.nextInt();
		if (userInput == 1) {
			// accept proposal code
			System.out.println("Enter the proposal id that you want to accept");
			int proposalIdForAcceptance = input.nextInt();
			int[] arrayofIds = serviceObject.acceptProposalByAuPair(proposalIdForAcceptance);

			activeInterviewId = arrayofIds[0];
			hostID = arrayofIds[1];
			auPairId = arrayofIds[2];
			System.out.println("You have accepted the proposal id " + proposalIdForAcceptance
					+ ". Your contract will now be created.");

			contractObject.DynamicJasperReport();

			SendEmail.sendEmail();
			
			System.out.println("DO you rate this host : enter 1 ?");
			int uInput = input.nextInt();
			System.out.println("Enter the rating you want to give this host?");
			int ratings = input.nextInt();
			System.out.println("Enter the feedback you want to give this host?");
			String comments = input.next();

			serviceObject.saveRatingAndFeedback(hostID, auPairId, ratings, comments);

		}

		else if (userInput == 2) {
			
			System.out.println("Enter the proposal id that you want to reject");
			int proposalIdForRejection = input.nextInt();
			int aId = serviceObject.rejectProposalByAupair(proposalIdForRejection);
			System.out.println("Proposal rejected . We hope you find a better suited proposal soon." + aId); 
		}
	}

	public void saveProposalDetailsByHost(int activeInterviewId) throws SQLException {

		String tasksForAuPair = "";
		String workingHrsProposed = "";
		String remunerationsProposed = "";
		String holidaysProposed = "";
		boolean travelCosts;
		// int activeInterviewId = 26;
		String proposedStartDate, proposedEndDate;

		Scanner input = new Scanner(System.in);
		System.out.println("Do you want to create Proposal for Au Pair?");
		System.out.println("If yes press 1 or else press 0");
		int value = input.nextInt();

		if (value == 1) // for host
		{

			System.out.println("What tasks are assigned to Au Pair? ");
			tasksForAuPair = input.next();
			System.out.println("Working hours proposed per day: ");
			workingHrsProposed = input.next();
			System.out.println("Remunerations proposed per hour(In Euros): ");
			remunerationsProposed = input.next();
			System.out.println("Holidays Proposed per week");
			holidaysProposed = input.next();
			System.out.println("Travel cost (true/false)");
			travelCosts = input.nextBoolean();
			System.out.println("Proposed start date of contract (DD/MM/YYYY)");
			proposedStartDate = input.next();
			System.out.println("Proposed end date of contract (DD/MM/YYYY)");
			proposedEndDate = input.next();

			int proposaId = serviceObject.saveProposalDetailsByHost(tasksForAuPair, workingHrsProposed,
					remunerationsProposed, holidaysProposed, travelCosts, activeInterviewId, proposedStartDate,
					proposedEndDate);

			System.out.println( "Congratulations your proposal has been created\n");
			System.out.println("Plesae wait for the au-pair's response");
			
			//contractObject.DynamicJasperReport();
						
			//SendEmail.sendEmail();
			
		} else {
			System.out.println("Exit");
		}

	}

}
