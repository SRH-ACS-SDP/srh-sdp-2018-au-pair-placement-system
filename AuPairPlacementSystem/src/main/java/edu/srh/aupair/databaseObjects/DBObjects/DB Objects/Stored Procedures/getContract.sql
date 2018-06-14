CREATE DEFINER=`root`@`localhost` PROCEDURE `getContract`(IN HOSTID INT,IN AUPAIRID INT)
BEGIN
SELECT * FROM proposals INNER JOIN active_interviews ON proposals.ACTIVE_INTERVIEW_ID =active_interviews.ACTIVE_INTERVIEW_ID INNER JOIN hostuser 
ON active_interviews.HOST_ID=hostuser.HOST_ID INNER JOIN interview_availability ON interview_availability.INTERVIEW_ID=active_interviews.INTERVIEW_ID INNER JOIN au_pair ON
	AU_PAIR.AU_PAIR_ID=interview_availability.AU_PAIR_ID inner join person AP ON AP.PERSON_ID=AU_PAIR.PERSON_ID INNER JOIN PERSON HP ON HP.PERSON_ID=HOSTUSER.PERSON_ID
WHERE AU_PAIR.AU_PAIR_ID=AUPAIRID AND hostuser.HOST_ID=HOSTID;
END
