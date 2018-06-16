CREATE DEFINER=`root`@`localhost` PROCEDURE `registerAuPairUser`(
in personType VARCHAR(45),
in lastName VARCHAR(45), 
in userPassword VARCHAR(500),
in firstName VARCHAR(45), 
in emailid VARCHAR(45), 
in contactNo VARCHAR(25), 
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
in hasValidVisa bit, 
in hasSalaryExpectation bit,
in hasDrivingLicense bit,
in hobbies varchar(45),
in supervisesChildOfAge varchar(10),
in eduQualification varchar(45),
in fromTime VARCHAR(45),
in toTime VARCHAR(45),
out the_person_id int
)
BEGIN

Insert INTO au_pair_management.person(person_type,
last_name,
user_password,
first_name,email,contact_no,gender,
marital_status,DOB,is_active,last_online,
title,about_me,passport_no)
values 
(personType,lastName,userPassword,firstName,
emailid,contactNo,gender,maritalStatus,Dob,isActive,
lastOnline,title,aboutMe,passportNumber);

SELECT @pid := PERSON_ID FROM 
au_pair_management.person 
WHERE person.passport_no = passportNumber and person.EMAIL = emailid and person.PERSON_TYPE= 'AUPAIR';

if @pid IS NOT NULL THEN
Insert INTO au_pair_management.au_pair
(PERSON_ID,HAS_VALID_VISA, HAS_SALARY_EXPECTATION, 
HAS_DRIVING_LICENSE,HOBBIES,SUPERVISES_CHILD_OF_AGE,
EDU_QUALIFICATION)
values 
(@pid,HAS_VALID_VISA, HAS_SALARY_EXPECTATION, 
HAS_DRIVING_LICENSE,HOBBIES,SUPERVISES_CHILD_OF_AGE,
EDU_QUALIFICATION);

INSERT INTO au_pair_management.languages(PERSON_ID,LANGUAGES,PROFICIENCY) 
VALUES (@pid,languages,proficiency);

INSERT INTO au_pair_management.address(PERSON_ID,COUNTRY_CURRENCY_ID,ADDRESS_LINE1,CITY,POSTCODE)
VALUES (@pid,countryCurrencyId,address,city,postCode);

SELECT @AU_PAIR_ID :=  AU_PAIR_ID FROM au_pair_management.au_pair 
WHERE au_pair.PERSON_ID = @pid;

if @AU_PAIR_ID IS NOT NULL THEN
Insert into interview_availability(AU_PAIR_ID,FROM_TIME ,TO_TIME)
values (@AU_PAIR_ID , fromTime ,toTime); 
END IF;  
        
#SELECT INTERVIEW_ID  into interviewId
#from interview_availability where  AU_PAIR_ID = auPairID and FROM_TIME=fromTime 
#and TO_TIME  = toTime;
    
SELECT PERSON_ID INTO the_person_id FROM au_pair_management.person 
WHERE person.passport_no = passportNumber;

END IF;

END