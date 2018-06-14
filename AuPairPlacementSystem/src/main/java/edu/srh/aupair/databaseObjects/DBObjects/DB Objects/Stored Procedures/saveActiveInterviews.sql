CREATE DEFINER=`root`@`localhost` PROCEDURE `saveActiveInterviews`( 
IN interviewId INT(10),
IN hostId INT(10),
IN proposalOffers bit,
IN jobOfferedByHosts bit,
IN jobAcceptedbyAuPair bit,OUT active_interview_id int)
BEGIN

Insert INTO au_pair_management.active_interviews
(INTERVIEW_ID,HOST_ID,
PROPOSALS_OFFERS,
JOB_OFFERED_BY_HOSTS,JOB_ACCEPTED_BY_AU_PAIR)
values 
(interviewID,hostId,proposalOffers,jobOfferedByHosts,
jobAcceptedbyAuPair);

SELECT ACTIVE_INTERVIEW_ID INTO active_interview_id FROM active_interviews 
WHERE INTERVIEW_ID= interviewId;

END
