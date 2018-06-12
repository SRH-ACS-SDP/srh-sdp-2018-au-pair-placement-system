package edu.srh.aupair.proposalOperations;

<<<<<<< Updated upstream
import java.sql.*;
=======
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
>>>>>>> Stashed changes
import java.util.Scanner;

public class ProposalOperations {

<<<<<<< Updated upstream
	public static void main(String[] args) {
=======
public static void main(String[] args) {
>>>>>>> Stashed changes
		
		String tasksForAuPair="";
		String workingHrsProposed="";
		String remunerationsProposed="";
		String holidaysProposed ="";
<<<<<<< Updated upstream
		String travelCosts="";
		int activeInterviewId = 0;
		try{
=======
		boolean travelCosts;
		int activeInterviewId = 2; //this val will come from booking operations
		try{
			
>>>>>>> Stashed changes
			Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();
			
			Scanner input = new Scanner(System.in);
			System.out.println("Do you want to create Proposal for Au Pair?");
<<<<<<< Updated upstream
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
=======
			System.out.println("If yes press 1 or else press 0");//ask 
			int value = input.nextInt();

			if (value == 1) // for host
			{	
			
				
					System.out.println("What tasks areassigned to Au Pair? ");
					tasksForAuPair = input.next();
					System.out.println("Working hours proposed: ");
					workingHrsProposed = input.next();
					System.out.println("Remunerations proposed: ");
					remunerationsProposed = input.next();
					System.out.println("Holidays Proposed");
					holidaysProposed = input.next();
					System.out.println("Travel cost (true/false)");
					travelCosts = input.nextBoolean();
					
					String saveProposalDetailsQuery= "{Call saveProposalDetails(?,?,?,?,?,?,?)}";
					CallableStatement myStmt = conn.prepareCall(saveProposalDetailsQuery);
					myStmt.setInt(1, activeInterviewId);
					myStmt.setString(2, tasksForAuPair);
					myStmt.setString(3, workingHrsProposed);
					myStmt.setString(4, remunerationsProposed);
					myStmt.setString(5, holidaysProposed);
					myStmt.setBoolean(6, travelCosts);
					myStmt.registerOutParameter(7, Types.INTEGER);					
					myStmt.execute();			
					int proposaId = myStmt.getInt(7);
					System.out.println(proposaId + "Congratulations your proposal has been created");
				}
				else
				{
					System.out.println("Exit");
				}
			
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
>>>>>>> Stashed changes

		catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
}

