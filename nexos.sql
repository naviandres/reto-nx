-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema nexos
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema nexos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nexos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `nexos` ;

-- -----------------------------------------------------
-- Table `nexos`.`departamentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nexos`.`departamentos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `departamento_codigo` VARCHAR(30) NOT NULL,
  `departamento_nombre` VARCHAR(100) NOT NULL,
  `fecha_hora_crea` DATETIME NULL DEFAULT NULL,
  `fecha_hora_modifica` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nexos`.`empleados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nexos`.`empleados` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `documento_tipo` VARCHAR(2) NOT NULL,
  `documento_numero` VARCHAR(45) NOT NULL,
  `nombres` VARCHAR(100) NOT NULL,
  `apellidos` VARCHAR(100) NOT NULL,
  `departamentos_id` INT NULL DEFAULT NULL,
  `ciudad` VARCHAR(45) NULL DEFAULT NULL,
  `direccion` VARCHAR(45) NULL DEFAULT NULL,
  `correo_electronico` VARCHAR(45) NULL DEFAULT NULL,
  `Telefono` VARCHAR(20) NULL DEFAULT NULL,
  `fecha_hora_crea` DATETIME NULL DEFAULT NULL,
  `fecha_hora_modifica` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `departamentos_id_idx` (`departamentos_id` ASC) VISIBLE,
  CONSTRAINT `departamentos_id`
    FOREIGN KEY (`departamentos_id`)
    REFERENCES `nexos`.`departamentos` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
