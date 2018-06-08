package edu.srh.aupair.RatingOperations;

public class SuperAdvancedAuPair extends AuPairRatingDecorator{

	public SuperAdvancedAuPair(AuPairRating newAuPairRating) {
		super(newAuPairRating);
		
		System.out.println("Adding rating from basic aupair class");		
		System.out.println("Adding more rating because of a super advanced au pair");
	}

	public double getAupairRating()
	{
		return auPairRating.getAupairRating() + 5;
		
	}
	
	public String getAupairFeedback() {
		
		return auPairRating.getAupairFeedback() + " super advanced ";
	}

}

