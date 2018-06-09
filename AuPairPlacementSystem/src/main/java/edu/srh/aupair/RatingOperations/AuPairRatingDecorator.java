package edu.srh.aupair.RatingOperations;

abstract class AuPairRatingDecorator implements AuPairRating {

	protected AuPairRating auPairRating;
	
	public AuPairRatingDecorator(AuPairRating newAuPairRating)
	{
		auPairRating =  newAuPairRating;
	}
	public double getAupairRating()
	{
		return auPairRating.getAupairRating();
		
	}

}
