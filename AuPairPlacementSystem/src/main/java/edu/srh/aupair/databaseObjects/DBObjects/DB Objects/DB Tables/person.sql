CREATE TABLE `person` (
  `PERSON_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `PERSON_TYPE` varchar(45) NOT NULL,
  `LAST_NAME` varchar(45) NOT NULL,
  `USER_PASSWORD` varchar(500) NOT NULL,
  `FIRST_NAME` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `CONTACT_NO` varchar(25) NOT NULL,
  `GENDER` varchar(45) NOT NULL,
  `MARITAL_STATUS` varchar(45) NOT NULL,
  `DOB` datetime NOT NULL,
  `IS_ACTIVE` bit(1) NOT NULL,
  `LAST_ONLINE` datetime NOT NULL,
  `TITLE` varchar(45) NOT NULL,
  `ABOUT_ME` varchar(45) NOT NULL,
  `passport_no` varchar(15) NOT NULL,
  PRIMARY KEY (`PERSON_ID`),
  UNIQUE KEY `person_id_UNIQUE` (`PERSON_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;