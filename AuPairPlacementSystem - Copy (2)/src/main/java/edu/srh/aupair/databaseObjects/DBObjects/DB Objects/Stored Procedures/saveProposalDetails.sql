CREATE DEFINER=`root`@`localhost` PROCEDURE `saveProposalDetails`(
IN activeInterviewId INT(10),
IN tasksForAuPair VARCHAR(45),
IN workingHoursProposed VARCHAR (45),
IN remunerationProposed VARCHAR(45),
IN holidaysProposed VARCHAR(45),
IN travelCosts bit(1),
OUT proposal_id int
)
BEGIN
INSERT INTO au_pair_management.proposals(
ACTIVE_INTERVIEW_ID,TASKS_FOR_AU_PAIR,WORKING_HOURS_PROPOSED,
REMUNERATIONS_PROPOSED,HOLIDAYS_PROPOSED,TRAVEL_COSTS)
VALUES
(activeInterviewId,tasksForAuPair,workingHoursProposed,
remunerationProposed,holidaysProposed,travelCosts);

SELECT PROPOSAL_ID INTO proposal_id FROM proposals
WHERE ACTIVE_INTERVIEW_ID = activeInterviewId;

UPDATE active_interviews SET JOB_OFFERED_BY_HOSTS = "true"
WHERE ACTIVE_INTERVIEW_ID = proposal_id;

END
