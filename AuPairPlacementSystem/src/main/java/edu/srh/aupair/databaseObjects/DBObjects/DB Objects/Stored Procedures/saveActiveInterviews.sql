CREATE DEFINER=`root`@`localhost` PROCEDURE `saveActiveInterviews`( 
IN interviewId INT(10),
IN hostId INT(10),

OUT ai int)
BEGIN

INSERT INTO au_pair_management.active_interviews
(INTERVIEW_ID,HOST_ID,PROPOSALS_OFFERS,JOB_OFFERED_BY_HOSTS,JOB_ACCEPTED_BY_AU_PAIR)
VALUES (interviewID,hostId,'None','None','None');

SELECT ACTIVE_INTERVIEW_ID INTO ai FROM active_interviews 
WHERE INTERVIEW_ID = interviewId;

END