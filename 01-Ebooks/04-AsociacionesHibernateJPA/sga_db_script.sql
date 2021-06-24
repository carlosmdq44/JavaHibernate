-- MySQL dump 
--
-- Host: localhost    Database: sga_db
-- ------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Create schema sga_db
--

CREATE DATABASE IF NOT EXISTS sga_db;
USE sga_db;

--
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumno` (
  `id_alumno` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `id_domicilio` int(11) DEFAULT NULL,
  `id_contacto` int(11) DEFAULT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido_paterno` varchar(45) NOT NULL,
  `apellido_materno` varchar(45) DEFAULT NULL,
  `genero` varchar(1) DEFAULT NULL COMMENT 'mujer (M), hombre (H)',
  `fecha_nacimiento` datetime DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_alumno`),
  KEY `fk_alumno_usuario` (`id_usuario`),
  KEY `fk_alumno_direccion` (`id_domicilio`),
  KEY `fk_alumno_contacto` (`id_contacto`),
  CONSTRAINT `fk_alumno_contacto` FOREIGN KEY (`id_contacto`) REFERENCES `contacto` (`id_contacto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_alumno_direccion` FOREIGN KEY (`id_domicilio`) REFERENCES `domicilio` (`id_domicilio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_alumno_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asignacion`
--

DROP TABLE IF EXISTS `asignacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asignacion` (
  `id_asignacion` int(11) NOT NULL AUTO_INCREMENT,
  `id_alumno` int(11) DEFAULT NULL,
  `id_curso` int(11) DEFAULT NULL,
  `id_sucursal` int(11) DEFAULT NULL,
  `fecha_inicio` datetime DEFAULT NULL,
  `fecha_termino` datetime DEFAULT NULL,
  `horario` varchar(45) DEFAULT NULL COMMENT 'Matutino, Vespertino, Nocturno',
  `precio_alumno` float DEFAULT NULL COMMENT 'precio final al alumno, ya incluyendo descuentos y promociones',
  `tipo_cuota` varchar(1) DEFAULT NULL COMMENT 'Esquema mensual(M), semanal(S), diaria(D) etc',
  `tipo_esquema` varchar(1) DEFAULT NULL COMMENT 'Intensivo (I), Semi-intensivo(S), sabatino(W), asesoría(A)',
  `tipo_promocion` varchar(1) DEFAULT NULL COMMENT 'promocion por red social, twitter(T),facebook (F),linkedin(L), amigo(A), cliente frecuente(C),  etc',
  `tipo_descuento` varchar(1) DEFAULT NULL COMMENT 'Enumeracion: estudiante (E), profesor (P), tamaño grupo(G)',
  `tipo_metodologia` varchar(1) DEFAULT NULL COMMENT 'Curso Presencial (P), curso por video (V), curso elearning (E)',
  `fecha_pago_inicial` datetime DEFAULT NULL COMMENT 'indica el dia de pago inicial, y se calcula con base en el tipo de cuota, si la cuota es mensual, con base en le fecha de pago, se envia cada mes un reporte o un aviso. Si el tipo de cuota es semanal, se envia un aviso cada semana basado en esta fecha',
  `equipo_computo` tinyint(4) DEFAULT NULL COMMENT 'Indica si se le asigna una pc al alumno o si el proporciona la suya',
  `version` int(11) NOT NULL DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_asignacion`),
  KEY `fk_Asignacion_Alumno` (`id_alumno`),
  KEY `fk_Asignacion_Curso` (`id_curso`),
  KEY `fk_Asignacion_Sucursal` (`id_sucursal`),
  CONSTRAINT `fk_Asignacion_Alumno` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id_alumno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Asignacion_Curso` FOREIGN KEY (`id_curso`) REFERENCES `curso` (`id_curso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Asignacion_Sucursal` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignacion`
--

LOCK TABLES `asignacion` WRITE;
/*!40000 ALTER TABLE `asignacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `asignacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacto`
--

DROP TABLE IF EXISTS `contacto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contacto` (
  `id_contacto` int(11) NOT NULL AUTO_INCREMENT,
  `telefono` varchar(45) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `oficina` varchar(45) DEFAULT NULL,
  `extension` varchar(45) DEFAULT NULL,
  `email1` varchar(45) NOT NULL,
  `email2` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_contacto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacto`
--

LOCK TABLES `contacto` WRITE;
/*!40000 ALTER TABLE `contacto` DISABLE KEYS */;
/*!40000 ALTER TABLE `contacto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso` (
  `id_curso` int(11) NOT NULL AUTO_INCREMENT,
  `id_instructor` int(11) DEFAULT NULL,
  `nombre` varchar(45) NOT NULL,
  `horario` varchar(45) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_curso`),
  KEY `fk_Curso_Instructor` (`id_instructor`),
  CONSTRAINT `fk_Curso_Instructor` FOREIGN KEY (`id_instructor`) REFERENCES `instructor` (`id_instructor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domicilio`
--

DROP TABLE IF EXISTS `domicilio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domicilio` (
  `id_domicilio` int(11) NOT NULL AUTO_INCREMENT,
  `calle` varchar(45) NOT NULL,
  `no_externo` varchar(45) DEFAULT NULL,
  `no_interno` varchar(45) DEFAULT NULL,
  `cp` int(11) DEFAULT NULL,
  `municipio` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `pais` varchar(45) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id_domicilio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domicilio`
--

LOCK TABLES `domicilio` WRITE;
/*!40000 ALTER TABLE `domicilio` DISABLE KEYS */;
/*!40000 ALTER TABLE `domicilio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instructor` (
  `id_instructor` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `id_domicilio` int(11) DEFAULT NULL,
  `id_contacto` int(11) DEFAULT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido_paterno` varchar(45) NOT NULL,
  `apellido_materno` varchar(45) DEFAULT NULL,
  `genero` varchar(45) DEFAULT NULL,
  `fecha_nacimiento` datetime DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_instructor`),
  KEY `fk_instructor_usuario` (`id_usuario`),
  KEY `fk_instructor_domicilio` (`id_domicilio`),
  KEY `fk_instructor_contacto` (`id_contacto`),
  CONSTRAINT `fk_instructor_contacto` FOREIGN KEY (`id_contacto`) REFERENCES `contacto` (`id_contacto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_instructor_domicilio` FOREIGN KEY (`id_domicilio`) REFERENCES `domicilio` (`id_domicilio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_instructor_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pago` (
  `id_pago` int(11) NOT NULL AUTO_INCREMENT,
  `id_asignacion` int(11) DEFAULT NULL,
  `monto` float DEFAULT NULL,
  `tipo_forma_pago` varchar(1) DEFAULT NULL COMMENT 'efectivo (E), Debito(D),Credito(C),paypal(P)',
  `pago_parcial` varchar(1) DEFAULT NULL COMMENT 'Indica si es pago parcial (P) o pago total (T) o final de su cuota. ',
  `version` int(11) NOT NULL DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_pago`),
  KEY `fk_pago_asignacion` (`id_asignacion`),
  CONSTRAINT `fk_pago_asignacion` FOREIGN KEY (`id_asignacion`) REFERENCES `asignacion` (`id_asignacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_rol`),
  KEY `fk_Roles_Usuario` (`id_usuario`),
  CONSTRAINT `fk_Roles_Usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sucursal` (
  `id_sucursal` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_sucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

