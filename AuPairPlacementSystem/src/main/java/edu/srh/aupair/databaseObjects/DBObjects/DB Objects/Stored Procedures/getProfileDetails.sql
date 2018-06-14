CREATE DEFINER=`root`@`localhost` PROCEDURE `getProfileDetails`(IN PERSONID int,IN PERSONTYPE varchar(45))
BEGIN
if(PERSONTYPE='AUPAIR') then
select a.*,p.*,ad.*,c.* from PERSON p inner join AU_PAIR a on p.PERSON_ID=a.PERSON_ID inner join address ad on p.PERSON_ID=ad.PERSON_ID
 inner join country_currency c on c.COUNTRY_CURRENCY_ID=ad.COUNTRY_CURRENCY_ID where 
p.PERSON_ID=PERSONID and 
p.PERSON_TYPE=PERSONTYPE;
elseif(PERSONTYPE='HOST') then
select h.*,p.*,ad.*,c.* from PERSON p inner join HOSTUSER h on p.PERSON_ID=h.PERSON_ID inner join address ad on p.PERSON_ID=ad.PERSON_ID
 inner join country_currency c on c.COUNTRY_CURRENCY_ID=ad.COUNTRY_CURRENCY_ID where 
p.PERSON_id=PERSONID and
p.PERSON_TYPE=PERSONTYPE;
END IF;
END
