CREATE DEFINER=`root`@`localhost` PROCEDURE `verifyExistingUser`(IN passportNumber VARCHAR(45), 
IN personType VARCHAR(45),
IN emailId VARCHAR (45),
OUT the_count VARCHAR(10))
BEGIN

select  count(*)  INTO the_count 
from au_pair_management.person where 
Person_type = personType and passport_no = passportNumber and EMAIL =emailId ;
      
END