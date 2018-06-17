CREATE PROCEDURE `getAuPairIdFromPersonId` (IN personId int, OUT auPairId int)
BEGIN

SELECT AU_PAIR_ID INTO auPairId FROM au_pair 
WHERE PERSON_ID = personId ;

END