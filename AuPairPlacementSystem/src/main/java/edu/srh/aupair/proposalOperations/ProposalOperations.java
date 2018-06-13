package edu.srh.aupair.proposalOperations;

import java.sql.*;
import java.util.Scanner;

public class ProposalOperations {

	public static void main(String[] args) throws SQLException 
	{
		
		Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();
		
	    saveProposalDetailsByHost(conn);
		
		saveProposalResponseByAuPair(conn);
				
	}

	private static void saveProposalResponseByAuPair(Connection conn) throws SQLException {
		
		Scanner input = new Scanner(System.in);		
		System.out.println("Hey ! you have a pending proposal. Do you want to view it?");
		System.out.println("If yes press 1 or else press 0");
		int value = input.nextInt();

		if (value == 1) // for AU pair
		{	
			//TO DO 
			// 1. Show the proposal that is created for this au pair 
			// 2. Provide the option to accept / reject
			System.out.println("Do you want to accept or reject a proposal?");
			System.out.println("----SELECT YOUR CHOICE----" + "\n" +  "ENTER 1 for accepting a proposal" +"\n"+  "ENTER 2 for rejecting a proposal");
			int userInput = input.nextInt(); 
			//int proposalIdForAcceptance = input.nextInt();  //this id will be visible on the screen and the user will enter it
			
			if(userInput == 1)
			{
				//accept proposal code	
				System.out.println("Enter the proposal id that you want to accept");
				int proposalIdForAcceptance = input.nextInt(); 
				String query = "{CALL `acceptProposalByAuPair`(?,?)}";
	 			CallableStatement cs = conn.prepareCall(query);		 				
				cs.setInt(1, proposalIdForAcceptance);		 		
				//cs.setInt(2, activeIntId);	 			
	 			cs.registerOutParameter(2, Types.INTEGER);							
	 			cs.execute();			
	 			int activeInterviewId = cs.getInt(2);
	 			System.out.println("Congratulations you have accepted the proposal. Your contract will now be created." + activeInterviewId); 	//TO do remove the id later 			
	 			
	 			//Call the contract creation code TO DO 
			}
			
			else if (userInput == 2)
			{
				//reject proposal code
				System.out.println("Enter the proposal id that you want to reject");
				int proposalIdForRejection = input.nextInt(); 
				String query = "{CALL `rejectProposalByAuPair`(?,?)}";
	 			CallableStatement cs = conn.prepareCall(query);		 				
				cs.setInt(1, proposalIdForRejection);		 		
				//cs.setInt(2, activeIntId);	 			
	 			cs.registerOutParameter(2, Types.INTEGER);							
	 			cs.execute();			
	 			int activeInterviewId = cs.getInt(2);
	 			System.out.println("Proposal rejected . We hope you find a better suited proposal soon." + activeInterviewId); //TO do remove the id later
			}
			
			
		}
		else
		{
			System.out.println("DO YOU WANT TO LOGOUT?");
			// to do : write logout code
		}
		input.close();
	}

	private static void saveProposalDetailsByHost(Connection conn) throws SQLException {
		
		String tasksForAuPair="";
		String workingHrsProposed="";
		String remunerationsProposed="";
		String holidaysProposed ="";
		boolean travelCosts;
		int activeInterviewId = 26;
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
				System.out.println("Proposed start date of contract (DD/MM-YYYY)");
				proposedStartDate = input.next();
				System.out.println("Proposed end date of contract (DD/MM-YYYY)");
				proposedEndDate = input.next();
				
				String saveProposalDetailsQuery= "{Call saveProposalDetails(?,?,?,?,?,?,?,?,?)}";
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
				System.out.println(proposaId + "Congratulations your proposal has been created");
			}
			else
			{
				System.out.println("Exit");
			}
		input.close();
	}
}
