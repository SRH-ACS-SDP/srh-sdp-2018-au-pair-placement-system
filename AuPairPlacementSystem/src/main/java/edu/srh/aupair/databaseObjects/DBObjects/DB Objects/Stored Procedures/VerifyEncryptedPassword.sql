CREATE DEFINER=`root`@`localhost` PROCEDURE `VerifyEncryptedPassword`( in userName varchar(45),
in personType varchar(45),
out userPassword VARCHAR(500))
BEGIN

SELECT @count := count(*) from au_pair_management.person 
where EMAIL = userName and PERSON_TYPE = personType;
IF @count IS NOT NULL THEN
select  USER_PASSWORD  INTO userPassword 
from au_pair_management.person where 
EMAIL= userName and  PERSON_TYPE = personType;
END IF;
END
