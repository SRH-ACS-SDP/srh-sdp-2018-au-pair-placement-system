CREATE DEFINER=`root`@`localhost` PROCEDURE `verifyExistingUser`(IN passportNumber VARCHAR(255), 
IN personType VARCHAR(255),
                      OUT the_count VARCHAR(10))
BEGIN

   select  count(*)  INTO the_count 
from au_pair_management.person where 
Person_type = personType and passport_no = passportNumber ;
       
END
