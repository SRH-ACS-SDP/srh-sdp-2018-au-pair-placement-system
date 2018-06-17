CREATE TABLE `interview_availability` (
  `INTERVIEW_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `AU_PAIR_ID` int(10) unsigned NOT NULL,
  `FROM_TIME` datetime NOT NULL,
  `TO_TIME` datetime NOT NULL,
  PRIMARY KEY (`INTERVIEW_ID`),
  KEY `AU_PAIR_ID_idx` (`AU_PAIR_ID`),
  CONSTRAINT `FK_INTERVIEW_AVAILABILITY_AU_PAIR_ID` FOREIGN KEY (`AU_PAIR_ID`) REFERENCES `au_pair` (`au_pair_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


ALTER TABLE `au_pair_management`.`interview_availability` 
modify COLUMN `FROM_TIME`  VARCHAR(45) null default null;

ALTER TABLE `au_pair_management`.`interview_availability` 
modify COLUMN `TO_TIME`  VARCHAR(45) null default null;