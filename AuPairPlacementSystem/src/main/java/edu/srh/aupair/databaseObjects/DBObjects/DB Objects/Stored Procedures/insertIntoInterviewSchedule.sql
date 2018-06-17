CREATE DEFINER=`root`@`localhost` PROCEDURE `insertIntoInterviewSchedule`(
IN auPairID INT(10), 
IN fromTime VARCHAR(45),
IN toTime VARCHAR(45),
OUT interviewId int)
BEGIN

   Insert into interview_availability(AU_PAIR_ID,FROM_TIME ,TO_TIME)
   values (auPairID , fromTime ,toTime);   
       
   SELECT INTERVIEW_ID  into interviewId
from interview_availability where  AU_PAIR_ID = auPairID and FROM_TIME=fromTime 
   and TO_TIME  = toTime;
   
       
END
