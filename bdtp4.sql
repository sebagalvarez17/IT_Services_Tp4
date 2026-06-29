-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: it_services_db
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activos`
--

DROP TABLE IF EXISTS `activos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activos` (
  `id_activo` int NOT NULL AUTO_INCREMENT,
  `id_empresa` int DEFAULT NULL,
  `tipo_activo` varchar(255) DEFAULT NULL,
  `marca_modelo` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_activo`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activos`
--

LOCK TABLES `activos` WRITE;
/*!40000 ALTER TABLE `activos` DISABLE KEYS */;
INSERT INTO `activos` VALUES (1,2,'Hardware','Servidor HP','Activo'),(2,2,'Hardware','Switch Cisco','Activo'),(3,2,'Hardware','Router Mikrotik','Activo'),(4,2,'Software','Win Server','Activo'),(5,2,'Software','Antivirus','Activo'),(6,3,'Hardware','Servidor Dell','Activo'),(7,3,'Hardware','Switch HP','Activo'),(8,3,'Hardware','AP Ubiquiti','Activo'),(9,3,'Software','Office','Activo'),(10,3,'Software','SQL Server','Activo'),(11,4,'Hardware','Servidor Lenovo','Activo'),(12,4,'Hardware','Switch TP-Link','Activo'),(13,4,'Hardware','Router Ubiquiti','Activo'),(14,4,'Software','Win 11','Activo'),(15,4,'Software','Backup','Activo'),(16,5,'Hardware','Servidor Supermicro','Activo'),(17,5,'Hardware','Switch Dell','Activo'),(18,5,'Hardware','Firewall','Activo'),(19,5,'Software','Adobe','Activo'),(20,5,'Software','AutoCAD','Activo'),(21,6,'Hardware','Servidor HP','Activo'),(22,6,'Hardware','Switch D-Link','Activo'),(23,6,'Hardware','Router Asus','Activo'),(24,6,'Software','Win Server','Activo'),(25,6,'Software','Sistema','Activo'),(26,7,'Hardware','Servidor Rack','Activo'),(27,7,'Hardware','Switch Cisco','Activo'),(28,7,'Hardware','Router Cisco','Activo'),(29,7,'Software','Win 10','Activo'),(30,7,'Software','Logística','Activo'),(31,8,'Hardware','Servidor Tower','Activo'),(32,8,'Hardware','Switch Netgear','Activo'),(33,8,'Hardware','Router Tp-Link','Activo'),(34,8,'Software','Gestión','Activo'),(35,8,'Software','Win Server','Activo'),(36,9,'Hardware','Servidor Médico','Activo'),(37,9,'Hardware','Switch HP','Activo'),(38,9,'Hardware','Router Mikrotik','Activo'),(39,9,'Software','Salud','Activo'),(40,9,'Software','Debian','Activo'),(41,10,'Hardware','Servidor Central','Activo'),(42,10,'Hardware','Switch HP','Activo'),(43,10,'Hardware','Router Cisco','Activo'),(44,10,'Software','Oracle','Activo'),(45,10,'Software','Win 11','Activo'),(46,11,'Hardware','Servidor Local','Activo'),(47,11,'Hardware','Switch D-Link','Activo'),(48,11,'Hardware','Router Tenda','Activo'),(49,11,'Software','Taller','Activo'),(50,11,'Software','Win 10','Activo'),(51,12,'Hardware','Servidor Ventas','Activo'),(52,12,'Hardware','Switch TP-Link','Activo'),(53,12,'Hardware','Router Cisco','Activo'),(54,12,'Software','Win 10','Activo'),(55,12,'Software','Ventas','Activo'),(56,13,'Hardware','Servidor Dist.','Activo'),(57,13,'Hardware','Switch Cisco','Activo'),(58,13,'Hardware','Router Huawei','Activo'),(59,13,'Software','Win Server','Activo'),(60,13,'Software','Excel','Activo'),(61,14,'Hardware','Servidor Coop.','Activo'),(62,14,'Hardware','Switch HP','Activo'),(63,14,'Hardware','Router Cisco','Activo'),(64,14,'Software','Win 10','Activo'),(65,14,'Software','Office','Activo'),(66,15,'Hardware','Servidor Estación','Activo'),(67,15,'Hardware','Switch D-Link','Activo'),(68,15,'Hardware','Router TP-Link','Activo'),(69,15,'Software','Caja','Activo'),(70,15,'Software','Win 10','Activo'),(71,16,'Hardware','Servidor Farmacia','Activo'),(72,16,'Hardware','Switch HP','Activo'),(73,16,'Hardware','Router MikroTik','Activo'),(74,16,'Software','Farmacia','Activo'),(75,16,'Software','Win 11','Activo'),(76,17,'Hardware','Servidor Frigo.','Activo'),(77,17,'Hardware','Switch Cisco','Activo'),(78,17,'Hardware','Router Ubiquiti','Activo'),(79,17,'Software','Win Server','Activo'),(80,17,'Software','Inventario','Activo'),(81,18,'Hardware','Servidor Inmob.','Activo'),(82,18,'Hardware','Switch TP-Link','Activo'),(83,18,'Hardware','Router Cisco','Activo'),(84,18,'Software','Win 10','Activo'),(85,18,'Software','Office','Activo'),(86,19,'Hardware','Servidor Lab.','Activo'),(87,19,'Hardware','Switch HP','Activo'),(88,19,'Hardware','Router MikroTik','Activo'),(89,19,'Software','Bio','Activo'),(90,19,'Software','Win 11','Activo'),(91,20,'Hardware','Servidor Gim.','Activo'),(92,20,'Hardware','Switch TP-Link','Activo'),(93,20,'Hardware','Router D-Link','Activo'),(94,20,'Software','Gestión','Activo'),(95,20,'Software','Win 10','Activo'),(96,21,'Hardware','Servidor Medios','Activo'),(97,21,'Hardware','Switch Cisco','Activo'),(98,21,'Hardware','Router Juniper','Activo'),(99,21,'Software','Premiere','Activo'),(100,21,'Software','Win 11','Activo');
/*!40000 ALTER TABLE `activos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_labor`
--

DROP TABLE IF EXISTS `detalle_labor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_labor` (
  `id_labor` int NOT NULL AUTO_INCREMENT,
  `ticket_id` int DEFAULT NULL,
  `fecha_labor` varchar(20) DEFAULT NULL,
  `hora_inicio` varchar(10) DEFAULT NULL,
  `horas_invertidas` int DEFAULT NULL,
  `minutos_invertidos` int DEFAULT NULL,
  `resolucion` text,
  PRIMARY KEY (`id_labor`),
  KEY `ticket_id` (`ticket_id`),
  CONSTRAINT `detalle_labor_ibfk_1` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_labor`
--

LOCK TABLES `detalle_labor` WRITE;
/*!40000 ALTER TABLE `detalle_labor` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_labor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa_cliente`
--

DROP TABLE IF EXISTS `empresa_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa_cliente` (
  `id_empresa` int NOT NULL,
  `razon_social` varchar(255) DEFAULT NULL,
  `cuit` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_empresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa_cliente`
--

LOCK TABLES `empresa_cliente` WRITE;
/*!40000 ALTER TABLE `empresa_cliente` DISABLE KEYS */;
INSERT INTO `empresa_cliente` VALUES (2,'Servicios Petroleros KM3','30-55443322-1','Fray Luis Beltrán 320, Km 3'),(3,'Logística Rada Tilly S.R.L.','30-65884123-5','Av. Moyano 820, Rada Tilly'),(4,'Pesquera del Sur','30-12455678-2','Av. Ducós 950, Costanera'),(5,'Suministros Industriales S.A.','30-99887766-4','Ruta 3 y Constituyentes, Parque Industrial'),(6,'Hotel Austral Comodoro','30-50441122-8','Moreno 725, Centro'),(7,'Transportes Rada Tilly','30-44556677-3','Av. Almirante Brown 1200, Rada Tilly'),(8,'Panificadora Don Bosco','30-22334455-6','Alejandro Maíz 450, Km 8'),(9,'Clínica del Valle','30-11223344-9','Ameghino 946, Centro'),(10,'Supermercado La Anónima','30-70707070-1','Güemes y San Martín, Centro'),(11,'Talleres Mecánicos del Sur','30-88112233-4','Av. Kennedy 2500, Juan XXIII'),(12,'Concesionaria Automotriz KM5','30-33669911-2','Av. José Ingenieros 410, Km 5'),(13,'Distribuidora de Gas del Sur','30-55006600-7','Calle Máximo Abásolo 800, Centro'),(14,'Cooperativa de Trabajo KM12','30-44005500-2','Ruta 39, Km 12'),(15,'Estación de Servicio Sol','30-22119988-5','Av. Rivadavia 1600, 13 de Diciembre'),(16,'Farmacia Social Comodoro','30-66778899-0','San Martín 500, Centro'),(17,'Frigorífico Comodoro','30-55441199-3','Hipólito Yrigoyen 3500, Industrial'),(18,'Inmobiliaria del Golfo','30-33221100-4','Pellegrini 820, Centro'),(19,'Colegio Biología Marina','30-99008811-6','José Leonardo Rojas, Km 3'),(20,'Gimnasio Municipal Nro 1','30-88442266-7','Aristóbulo del Valle 200, Centro'),(21,'Multimedios Chubut','30-11559933-2','Ameghino 1250, Centro');
/*!40000 ALTER TABLE `empresa_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tecnicos`
--

DROP TABLE IF EXISTS `tecnicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tecnicos` (
  `id_tecnico` int NOT NULL,
  `nombre_completo` varchar(255) DEFAULT NULL,
  `especialidad` varchar(255) DEFAULT NULL,
  `disponibilidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_tecnico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnicos`
--

LOCK TABLES `tecnicos` WRITE;
/*!40000 ALTER TABLE `tecnicos` DISABLE KEYS */;
INSERT INTO `tecnicos` VALUES (1,'Sebastian Alvarez','Especialista en Soporte Tecnico y Servidores','Full-time'),(2,'Mariana Torres','Soporte Técnico Nivel 2','Jornada Completa'),(3,'Marcos Alcalde','Administrador de Redes y Seguridad','Jornada Completa'),(4,'Gustavo Inturias','Datacenter','Guardia Pasiva'),(5,'Juan Bonillo','Coordinador','Full-time');
/*!40000 ALTER TABLE `tecnicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `id_activo` int DEFAULT NULL,
  `id_tecnico` int DEFAULT NULL,
  `fecha_apertura` varchar(255) DEFAULT NULL,
  `prioridad` varchar(255) DEFAULT NULL,
  `descripcion_falla` varchar(255) DEFAULT NULL,
  `fecha_labor` varchar(10) DEFAULT NULL,
  `hora_inicio` varchar(5) DEFAULT NULL,
  `horas_invertidas` int DEFAULT NULL,
  `minutos_invertidos` int DEFAULT NULL,
  `resolucion` text,
  `estado` varchar(20) DEFAULT 'Abierto',
  `id_empresa` int DEFAULT NULL,
  PRIMARY KEY (`ticket_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (1,28,1,'2026-06-28','MEDIA','Se soluciono inconveniente con dhcp , el cual se habia quedado sin ips disponibles en el pool.','28/06/2026','18:00',2,0,'Se soluciono inconvenientes con rango de ip de DHCP ','Resuelto',7),(2,13,3,'2026-06-28','ALTA','Se soluciono inconveniente en base 1 , estaban sin internet ','28/06/2026','14:00',1,0,'Se soluciono inconveniente en el equipo de mariela , no  tenia internet. ','Resuelto',4),(3,13,4,'2026-06-28','BAJA','Se activo paquete office en equipo de Marianela','28/06/2026','16:00',1,0,'se soluciono inconveniente con puerto ethernet de la pc ','Resuelto',4),(4,38,4,'2026-06-28','ALTA','usuario informa que no tiene internet ',NULL,NULL,NULL,NULL,NULL,'Abierto',9);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-28 22:37:14
