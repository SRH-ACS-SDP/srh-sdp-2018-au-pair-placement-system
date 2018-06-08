package edu.srh.aupair.RatingOperations;

public class PersonRationgOperations {

	public static void main(String[] args) {
		
		AuPairRating basicAuPair = new SuperAdvancedAuPair(new AdvancedAuPair(new BasicAuPair()));
		
		System.out.println("Rating " + basicAuPair.getAupairRating());
		
		System.out.println("Feedback " + basicAuPair.getAupairFeedback());
	}

}
