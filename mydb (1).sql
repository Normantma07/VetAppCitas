-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 14-05-2021 a las 08:30:30
-- Versión del servidor: 8.0.21
-- Versión de PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mydb`
--

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `BorrarCita`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `BorrarCita` (IN `idCitasB` INT)  READS SQL DATA
BEGIN 
    DECLARE error INT ;
    START TRANSACTION;
    DELETE FROM citas WHERE idCitas  = idCitasB;
    SET error = (SELECT @error);
    IF(error=0) THEN 
    ROLLBACK; 
    ELSE COMMIT;
    END IF;
    END$$

DROP PROCEDURE IF EXISTS `BorrarMascotas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `BorrarMascotas` (IN `idMascotasB` INT)  READS SQL DATA
BEGIN 
    DECLARE error INT ;
    START TRANSACTION;
    DELETE FROM mascotas WHERE idMascotas  = idMascotasB;
    SET error = (SELECT @error);
    IF(error=0) THEN 
    ROLLBACK; 
    ELSE COMMIT;
    END IF;
    END$$

DROP PROCEDURE IF EXISTS `BorrarPersonas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `BorrarPersonas` (IN `idPersonaB` INT)  READS SQL DATA
BEGIN 
    DECLARE error INT ;
    START TRANSACTION;
    DELETE FROM personas WHERE idPersona  = idPersonaB;
    SET error = (SELECT @error);
    IF(error=0) THEN 
    ROLLBACK; 
    ELSE COMMIT;
    END IF;
    END$$

DROP PROCEDURE IF EXISTS `InsertarCita`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertarCita` (IN `idCitas` INT, `idPersona` VARCHAR(50), `prioridad` VARCHAR(50), `motivo_cita` VARCHAR(50))  READS SQL DATA
BEGIN DECLARE error INT; 
START TRANSACTION; 
INSERT INTO citas VALUES(idCitas,idPersona,idMascotas,prioridad,motivo_cita); 
SET error=(SELECT @error); 
IF(error=0)THEN ROLLBACK; 
ELSE COMMIT; 
END IF; 
END$$

DROP PROCEDURE IF EXISTS `InsertarMascota`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertarMascota` (IN `idMascotas` INT, `nombre_masc` VARCHAR(50), `especie` VARCHAR(50), `raza` VARCHAR(50), `edad` VARCHAR(50))  READS SQL DATA
BEGIN DECLARE error INT; 
START TRANSACTION; 
INSERT INTO mascotas VALUES(idMascotas,nombre_masc,especie,raza,edad,idPersona); 
SET error=(SELECT @error); 
IF(error=0)THEN ROLLBACK; 
ELSE COMMIT; 
END IF; 
END$$

DROP PROCEDURE IF EXISTS `InsertarPersona`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertarPersona` (IN `idPersona` INT, `nombre_per` VARCHAR(50), `apellido_per` VARCHAR(50), `email` VARCHAR(50), `telefono` VARCHAR(50), `direccion` VARCHAR(50))  READS SQL DATA
BEGIN DECLARE error INT; 
START TRANSACTION; 
INSERT INTO personas VALUES(idPersona,nombre_per,apellido_per,email,telefono,direccion); 
SET error=(SELECT @error); 
IF(error=0)THEN ROLLBACK; 
ELSE COMMIT; 
END IF; 
END$$

DROP PROCEDURE IF EXISTS `ModificarCita`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarCita` (IN `idCitasM` INT, `idPersonaM` VARCHAR(50), `prioridadM` VARCHAR(50), `motivo_citaM` VARCHAR(50))  BEGIN 
 DECLARE error INT; 
 START TRANSACTION; 
 UPDATE mascotas SET idPersona=idPersonaM, prioridad=prioridadM, motivo_cita=motivo_citaM 
 WHERE idCitas=idCitasM; 
 SET error=(SELECT @error); 
 IF(error=0)THEN 
 ROLLBACK; 
 ELSE 
 COMMIT; 
 END IF; 
 END$$

DROP PROCEDURE IF EXISTS `ModificarMascota`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarMascota` (IN `idMascotasM` INT, `nombre_mascM` VARCHAR(50), `especieM` VARCHAR(50), `razaM` VARCHAR(50), `edadM` VARCHAR(50), `idPersonaM` INT)  BEGIN 
 DECLARE error INT; 
 START TRANSACTION; 
 UPDATE mascotas SET nombre_masc=nombre_mascM, especie=especieM, raza=razaM, edad=edadM, idPersona=idPersonaM 
 WHERE idMascotas=idMascotasM; 
 SET error=(SELECT @error); 
 IF(error=0)THEN 
 ROLLBACK; 
 ELSE 
 COMMIT; 
 END IF; 
 END$$

DROP PROCEDURE IF EXISTS `ModificarPersonas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarPersonas` (IN `idPersonaM` INT, `nombre_perM` VARCHAR(50), `apellido_perM` VARCHAR(50), `emailM` VARCHAR(50), `telefonoM` VARCHAR(50), `direccionM` VARCHAR(50))  BEGIN 
 DECLARE error INT; 
 START TRANSACTION; 
 UPDATE personas SET nombre_per=nombre_perM, apellido_per=apellido_perM, email=emailM, telefono=telefonoM, direccion=direccionM 
 WHERE idPersona=idPersonaM; 
 SET error=(SELECT @error); 
 IF(error=0)THEN 
 ROLLBACK; 
 ELSE 
 COMMIT; 
 END IF; 
 END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citas`
--

DROP TABLE IF EXISTS `citas`;
CREATE TABLE IF NOT EXISTS `citas` (
  `idCitas` int NOT NULL,
  `idPersona` int NOT NULL,
  `idMascotas` int NOT NULL,
  `hora` date NOT NULL,
  `prioridad` varchar(50) NOT NULL,
  `motivo_cita` varchar(45) NOT NULL,
  PRIMARY KEY (`idCitas`),
  KEY `idMascotas_idx` (`idMascotas`),
  KEY `idPersona_idx` (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

DROP TABLE IF EXISTS `facturas`;
CREATE TABLE IF NOT EXISTS `facturas` (
  `idFacturas` int NOT NULL,
  `idPersona` int NOT NULL,
  `idCitas` int NOT NULL,
  `total` varchar(45) NOT NULL,
  `idusuarios` int DEFAULT NULL,
  PRIMARY KEY (`idFacturas`),
  KEY `idPersona_idx` (`idPersona`),
  KEY `idCitas_idx` (`idCitas`),
  KEY `idusuarios_idx` (`idusuarios`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mascotas`
--

DROP TABLE IF EXISTS `mascotas`;
CREATE TABLE IF NOT EXISTS `mascotas` (
  `idMascotas` int NOT NULL,
  `nombre_masc` varchar(45) NOT NULL,
  `especie` varchar(45) NOT NULL,
  `raza` varchar(45) NOT NULL,
  `edad` varchar(45) NOT NULL,
  PRIMARY KEY (`idMascotas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

DROP TABLE IF EXISTS `personas`;
CREATE TABLE IF NOT EXISTS `personas` (
  `idPersona` int NOT NULL,
  `nombre_per` varchar(50) NOT NULL,
  `apellido_per` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `registromascota`
-- (Véase abajo para la vista actual)
--
DROP VIEW IF EXISTS `registromascota`;
CREATE TABLE IF NOT EXISTS `registromascota` (
`edad_Mascota` varchar(45)
,`especie` varchar(45)
,`idMascotas` int
,`nombre_Mascota` varchar(45)
,`raza` varchar(45)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `idRoles` int NOT NULL AUTO_INCREMENT,
  `tipo_rol` varchar(45) NOT NULL,
  PRIMARY KEY (`idRoles`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`idRoles`, `tipo_rol`) VALUES
(1, 'Administrador'),
(2, 'Usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `idusuarios` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `contraseña` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `ultimo_ingreso` datetime DEFAULT '0000-00-00 00:00:00',
  `idRoles` int DEFAULT NULL,
  PRIMARY KEY (`idusuarios`),
  KEY `idRoles_idx` (`idRoles`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idusuarios`, `usuario`, `contraseña`, `nombre`, `correo`, `ultimo_ingreso`, `idRoles`) VALUES
(1, 'Admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'Admin', 'admin@mail.com', '0000-00-00 00:00:00', 1),
(2, 'Usuario', 'b665e217b51994789b02b1838e730d6b93baa30f', 'Usuario', 'usuario@mail.com', '0000-00-00 00:00:00', 1),
(3, 'norman', '39dfa55283318d31afe5a3ff4a0e3253e2045e43', 'norman', 'norman@mail.com', '0000-00-00 00:00:00', 1),
(4, 'Empleado', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'Jose Cuervo', 'jose@mail.com', '0000-00-00 00:00:00', 1);

-- --------------------------------------------------------

--
-- Estructura para la vista `registromascota`
--
DROP TABLE IF EXISTS `registromascota`;

DROP VIEW IF EXISTS `registromascota`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `registromascota`  AS  select `mascotas`.`idMascotas` AS `idMascotas`,`mascotas`.`nombre_masc` AS `nombre_Mascota`,`mascotas`.`especie` AS `especie`,`mascotas`.`raza` AS `raza`,`mascotas`.`edad` AS `edad_Mascota` from `mascotas` ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
