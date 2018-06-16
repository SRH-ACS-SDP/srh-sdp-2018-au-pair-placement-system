CREATE PROCEDURE `getHostIdFromPersonId` (IN personId int, OUT hostId int)
BEGIN

SELECT HOST_ID INTO hostId FROM hostuser 
WHERE PERSON_ID = personId ;

END