-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Newsfeed_User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Newsfeed_User` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Newsfeed_User` (
  `USER_ID` INT NOT NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(45) NULL,
  `PASSWORD` VARCHAR(45) NULL,
  PRIMARY KEY (`USER_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Article`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Article` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Article` (
  `ID_ARTICLE` INT NOT NULL AUTO_INCREMENT,
  `HEADLINE` VARCHAR(45) NULL,
  `LINK` VARCHAR(45) NULL,
  `AUTHOR` VARCHAR(45) NULL,
  `VOTES` INT NULL,
  `BY_USER` INT NOT NULL,
  PRIMARY KEY (`ID_ARTICLE`),
  CONSTRAINT `fk_Article_Newsfeed_User`
    FOREIGN KEY (`BY_USER`)
    REFERENCES `mydb`.`Newsfeed_User` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Article_Newsfeed_User_idx` ON `mydb`.`Article` (`BY_USER` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`VOTING`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`VOTING` ;

CREATE TABLE IF NOT EXISTS `mydb`.`VOTING` (
  `VOTING_ID` INT NOT NULL AUTO_INCREMENT,
  `VOTE` CHAR NULL,
  `article_id` INT NOT NULL,
  `user_voted` INT NOT NULL,
  PRIMARY KEY (`VOTING_ID`),
  CONSTRAINT `fk_VOTING_Article1`
    FOREIGN KEY (`article_id`)
    REFERENCES `mydb`.`Article` (`ID_ARTICLE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VOTING_Newsfeed_User1`
    FOREIGN KEY (`user_voted`)
    REFERENCES `mydb`.`Newsfeed_User` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_VOTING_Article1_idx` ON `mydb`.`VOTING` (`article_id` ASC);

CREATE INDEX `fk_VOTING_Newsfeed_User1_idx` ON `mydb`.`VOTING` (`user_voted` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
