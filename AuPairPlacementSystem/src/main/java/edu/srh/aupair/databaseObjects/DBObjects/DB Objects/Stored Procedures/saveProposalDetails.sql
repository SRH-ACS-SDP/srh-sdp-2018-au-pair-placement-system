CREATE DEFINER=`root`@`localhost` PROCEDURE `saveProposalDetails`(
IN activeInterviewId INT(10),
IN tasksForAuPair VARCHAR(45),
IN workingHoursProposed VARCHAR (45),
IN remunerationProposed VARCHAR(45),
IN holidaysProposed VARCHAR(45),
IN travelCosts bit(1),
IN proposedStartDate Varchar(45),
IN proposedEndDate varchar(45),
OUT pid int(10))
BEGIN

INSERT INTO au_pair_management.proposals(
ACTIVE_INTERVIEW_ID,TASKS_FOR_AU_PAIR,WORKING_HOURS_PROPOSED,
REMUNERATIONS_PROPOSED,HOLIDAYS_PROPOSED,TRAVEL_COSTS,PROPOSED_START_DATE_OF_CONTRACT, 
PROPOSED_END_DATE_OF_CONTRCAT)
VALUES
(activeInterviewId,tasksForAuPair,workingHoursProposed,
remunerationProposed,holidaysProposed,travelCosts,proposedStartDate,proposedEndDate);

SELECT PROPOSAL_ID INTO pid FROM proposals
WHERE ACTIVE_INTERVIEW_ID = activeInterviewId;

UPDATE active_interviews SET JOB_OFFERED_BY_HOSTS = 'TRUE'
WHERE ACTIVE_INTERVIEW_ID = activeInterviewId;

END