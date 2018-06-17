CREATE DEFINER=`root`@`localhost` PROCEDURE `saveRatingsAndFeedback`(
IN hostID INT(10),
IN AuPairID INT(10),
IN ratings INT(11),
IN comments VARCHAR(45),
IN ratedByHost bit(1),
OUT rating_feedback_id int)
BEGIN

INSERT INTO au_pair_management.rating_feedback 
(HOST_ID,AU_PAIR_ID,RATINGS,COMMENTS,RATEDBYHOST) 
VALUES 
(hostID,AuPairID,ratings,comments,ratedByHost);
-- SELECT LAST_INSERT_ID() INTO rating_feedback_id ;

END
