package edu.srh.aupair.RatingOperations;

import java.sql.*;
import java.util.Scanner;

public class ratingFeedback {

	public static void main(String[] args) {
		
		int ratings =0;
		String comments ="";
		int hostID = 0;
		int auPairId =0;
		//boolean ratedByHost = false;
		
		try
		{	
			Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();
			Scanner input = new Scanner (System.in);
			System.out.println("To submit ratings for Au-Pair press 1 or press 2 for Host User");
			int value = input.nextInt();
			
			if(value == 1) //Au pair
			{
			String personType = "Host";
			System.out.println("Enter the host username that you want to rate: ");
			String hostUsername = input.next();
			System.out.println("Enter ratings for Host: ");
			ratings = input.nextInt();
			System.out.println(" Enter comments: ");
			comments = input.next();
			
			String saveRatingsAndFeedbackQuery = "{CALL `saveRatingsAndFeedback`(?,?,?,?,?,?)}";
			CallableStatement cs = conn.prepareCall(saveRatingsAndFeedbackQuery);
			cs.setInt(1, hostID);
			cs.setInt(2, auPairId);
			cs.setInt(3, ratings);
			cs.setString(4, comments);
			cs.setBoolean(5,false);
			cs.registerOutParameter(6, Types.INTEGER);
			cs.execute();
			
			System.out.println("****THANK YOU FOR YOUR VALUABLE FEEDBACK***");
			}
			
			else if (value == 2) //Host
			{
				String personType = "AU Pair";
				System.out.println("Enter the Au Pair username that you want to rate: ");
				String hostUsername = input.next();
				System.out.println("Enter ratings for Host: ");
				ratings = input.nextInt();
				System.out.println(" Enter comments: ");
				comments = input.next();
				
				String saveRatingsAndFeedbackQuery = "{CALL `saveRatingsAndFeedback`(?,?,?,?,?,?)}";
				CallableStatement cs = conn.prepareCall(saveRatingsAndFeedbackQuery);
				cs.setInt(1, hostID);
				cs.setInt(2, auPairId);
				cs.setInt(3, ratings);
				cs.setString(4, comments);
				cs.setBoolean(5,false);
				cs.registerOutParameter(6, Types.INTEGER);
				cs.execute();
				
				System.out.println("****THANK YOU FOR YOUR VALUABLE FEEDBACK***");
			}
			
			else
			{
				System.out.println("Exit ");
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		

	}

}
