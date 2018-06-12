package edu.srh.aupair.proposalOperations;

import java.sql.*;
import java.util.Scanner;

public class ProposalOperations {

	public static void main(String[] args) {
		
		String tasksForAuPair="";
		String workingHrsProposed="";
		String remunerationsProposed="";
		String holidaysProposed ="";
		String travelCosts="";
		int activeInterviewId = 0;
		try{
			Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();
			
			Scanner input = new Scanner(System.in);
			System.out.println("Do you want to create Proposal for Au Pair?");
			System.out.println("If yes press 1 or else press 0");
			int value = input.nextInt();

			if (value == 1) // for host
			{	
			
				int the_count = 0;
				if (the_count == 0) {
					System.out.println("What tasks areassigned to Au Pair? ");
					tasksForAuPair = input.nextLine();
					System.out.println("Working hours proposed: ");
					workingHrsProposed = input.nextLine();
					System.out.println("Remunerations proposed: ");
					remunerationsProposed = input.nextLine();
					System.out.println("Holidays Proposed");
					holidaysProposed = input.nextLine();
					System.out.println("Travel cost");
					travelCosts = input.nextLine();
					
					String saveProposalDetailsQuery= "{Call saveProposalDetails(?,?,?,?,?,?,?)}";
					CallableStatement myStmt = conn.prepareCall(saveProposalDetailsQuery);
					myStmt.setInt(1, activeInterviewId);
					myStmt.setString(2, tasksForAuPair);
					myStmt.setString(3, workingHrsProposed);
					myStmt.setString(4, remunerationsProposed);
					myStmt.setString(5, holidaysProposed);
					myStmt.setString(6, travelCosts);
					myStmt.registerOutParameter(7, Types.INTEGER);
					
					myStmt.execute();
					
					int proposal_id = myStmt.getInt(7);
					
					System.out.println("***PROPOSAL OFFERED BY HOST***");
				}
				else
				{
					System.out.println("Exit");
				}
			}
			input.close();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
}
