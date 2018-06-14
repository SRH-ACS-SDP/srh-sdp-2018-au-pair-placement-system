CREATE DEFINER=`root`@`localhost` PROCEDURE `registerAHostUser`( 
in personType VARCHAR(45),
in lastName VARCHAR(45), 
in userPassword VARCHAR(500),
in firstName VARCHAR(45), 
in emailid VARCHAR(45), 
in contactNo VARCHAR(45), 
in gender varchar(45), 
in maritalStatus varchar(45), 
in Dob datetime, 
in languages varchar(45),
in proficiency varchar(45),
in address varchar(45),
in city varchar(45),
in postCode int(11),
in countryCurrencyId int(10),
in isActive bit, 
in lastOnline datetime, 
in title varchar(45), 
in aboutMe varchar(45),  
in passportNumber varchar(45),
in IS_SALARY_PROVIDED bit,
in NUMBER_OF_KIDS int(11),
in AGE_OF_KIDS int(11),
in HAS_PHYSICAL_DISABILITY bit, OUT the_host_id int)
BEGIN
Insert INTO au_pair_management.person(person_type,last_name,
user_password,
first_name,email,contact_no,gender,
marital_status,DOB,is_active,last_online,
title,about_me,passport_no)
values 
(personType,lastName,userPassword,firstName,
emailid,contactNo,gender,maritalStatus,Dob,isActive,
lastOnline,title,aboutMe,passportNumber);

SELECT @pid := PERSON_ID FROM au_pair_management.person 
WHERE person.passport_no = passportNumber;


if @pid IS NOT NULL THEN
Insert INTO au_pair_management.HostUser
(PERSON_ID,IS_SALARY_PROVIDED, 
NUMBER_OF_KIDS,AGE_OF_KIDS,HAS_PHYSICAL_DISABILITY)
values 
(@pid,IS_SALARY_PROVIDED,NUMBER_OF_KIDS,AGE_OF_KIDS,HAS_PHYSICAL_DISABILITY);

INSERT INTO au_pair_management.languages(PERSON_ID,LANGUAGES,PROFICIENCY) 
VALUES (@pid,languages,proficiency);

INSERT INTO au_pair_management.address(PERSON_ID,COUNTRY_CURRENCY_ID,ADDRESS_LINE1,CITY,POSTCODE)
VALUES (@pid,countryCurrencyId,address,city,postCode);

select  HOST_ID  INTO the_host_id From au_pair_management.hostuser
 where hostuser.PERSON_ID = @pid;

END IF;
END
