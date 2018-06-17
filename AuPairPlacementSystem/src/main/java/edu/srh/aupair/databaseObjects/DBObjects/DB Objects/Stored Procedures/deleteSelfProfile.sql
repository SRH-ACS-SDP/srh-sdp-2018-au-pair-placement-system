CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteSelfProfile`(IN PERSONID int)
BEGIN
	update PERSON set IS_ACTIVE=0 where PERSON_ID=PERSONID;
END