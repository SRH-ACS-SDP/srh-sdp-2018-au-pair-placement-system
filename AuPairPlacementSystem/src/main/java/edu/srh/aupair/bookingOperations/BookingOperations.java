<<<<<<< Updated upstream
package edu.srh.aupair.bookingOperations;

public class BookingOperations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			System.out.println("Jenkins build operation succeed");
	}

}
=======
package edu.srh.aupair.bookingOperations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class BookingOperations {

	public static void main(String[] args) throws SQLException {
		
		ResultSet rs = null;
		
		Connection conn = edu.srh.aupair.utilities.utilities.getConnectionString();
		
		String AU_PAIR_ID = "19"; //get this from search grid
		
		//Assume that user has clicked on an AU pair, and pass that AU pair id to db and fetch his/her interview availability schedule		
		String query  = "SELECT * FROM interview_availability  where AU_PAIR_ID = " + AU_PAIR_ID ;
		
		CallableStatement stmt = conn.prepareCall(query);
		
		rs = stmt.executeQuery(query);	int interviewId = 0; 
		
		if(rs.next())
		{
			String availableInterviewSlot = rs.getString(3) + " TO " + rs.getString(4) ;
			interviewId = rs.getInt(1);
			System.out.println(availableInterviewSlot);
			System.out.println(interviewId);
		}
		else
		{
			System.out.println("No interview slots available for this Au pair");
		}
		
		
		int hostId = 1;	 //to be changed later, this value should come from previous search method	
		System.out.println("Do you want to book this slot? \n");
		
		System.out.println("Press 1 for yes \n");
		Scanner input = new Scanner(System.in);
		int userWantsToBookInterviewSlot = input.nextInt();
		
		if(userWantsToBookInterviewSlot == 1)
		{
			query = "{CALL `saveActiveInterviews`(?,?,?,?,?,?)}";
			CallableStatement cs = conn.prepareCall(query);			
			cs.setInt(1, interviewId);		
			cs.setInt(2, hostId);
			cs.setBoolean(3, true);
			cs.setBoolean(4, false);
			cs.setBoolean(5, false);
			cs.registerOutParameter(6, Types.INTEGER);							
			cs.execute();			
			int activeInterviewId = cs.getInt(6);
			System.out.println(activeInterviewId + "Congratulations your interview has been scheduled");
			
				
			
		}
		else
		{
			
		}
		
		
		
		
	}

}
>>>>>>> Stashed changes
