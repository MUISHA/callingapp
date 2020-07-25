CREATE TABLE `entreprise_sos` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `nom_entreprise` varchar(30) NOT NULL,
 `responsable` varchar(20) NOT NULL,
 `phone` varchar(20) NOT NULL,
 `email` varchar(20) NOT NULL,
 `type` int(1) NOT NULL,
 `domicile` int(1) NOT NULL,
 `latitude` double NOT NULL,
 `logitude` double NOT NULL,
 `quartier` int(1) NOT NULL,
 `commune` int(1) NOT NULL,
 `avenue` varchar(50) NOT NULL,
 `numero_home` int(12) NOT NULL,
 `date_save` date DEFAULT NULL,
 `love` enum('true','false') NOT NULL DEFAULT 'false',
 `picture` varchar(100) NOT NULL DEFAULT 'http://172.20.10.2/demo_sos/sos_picture/sos_logo.png',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1

