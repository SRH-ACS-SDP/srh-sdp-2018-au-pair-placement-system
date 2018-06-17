CREATE DEFINER=`root`@`localhost` PROCEDURE `getInterviewSchedule`(
IN AuPairId int(10), 
OUT fromTime VARCHAR(45),
OUT inTime VARCHAR (45),
OUT interviewId int(10))
BEGIN

SELECT @iId := Interview_id  FROM interview_availability 
WHERE AU_PAIR_ID = AuPairId;

select count(*) INTO  @the_count FROM  active_interviews WHERE INTERVIEW_ID = @iId;

IF @the_count = 0 THEN
SELECT FROM_TIME INTO fromTime FROM interview_availability WHERE AU_PAIR_ID = AuPairId;

SELECT TO_TIME INTO inTime FROM interview_availability WHERE AU_PAIR_ID = AuPairId;

SELECT INTERVIEW_ID INTO interviewId FROM interview_availability WHERE AU_PAIR_ID = AuPairId;


END IF;
      
END