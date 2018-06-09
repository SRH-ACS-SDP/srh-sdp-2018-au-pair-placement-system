package edu.srh.aupair.RatingOperations;

public class AdvancedAuPair extends AuPairRatingDecorator{

	public AdvancedAuPair(AuPairRating newAuPairRating) {
		super(newAuPairRating);
		
		System.out.println("Adding rating from basic aupair class");
		
		System.out.println("Adding more rating because of an advanced au pair");
	}

	public double getAupairRating()
	{
		return auPairRating.getAupairRating() + 3;
		
	}

	public String getAupairFeedback() {
		
		return auPairRating.getAupairFeedback() + " advanced ";
	}

}
