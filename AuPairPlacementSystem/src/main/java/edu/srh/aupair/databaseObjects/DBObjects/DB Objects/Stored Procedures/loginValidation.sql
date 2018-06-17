CREATE DEFINER=`root`@`localhost` PROCEDURE `loginValidation`( in userName varchar(45), 
in userPassword VARCHAR(45),   
OUT the_count VARCHAR(10))
BEGIN

   select  count(*)  INTO the_count 
from au_pair_management.person where 
EMAIL= userName and USER_PASSWORD = userPassword ;

END
