-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost:3306
-- Généré le: Ven 31 Mai 2013 à 06:21
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `isitup`
--
DROP DATABASE IF EXISTS isitup;
CREATE DATABASE isitup;
USE isitup;
-- --------------------------------------------------------

--
-- Structure de la table `asso_ws_employees`
--

CREATE TABLE IF NOT EXISTS `asso_ws_employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_ws` int(11) NOT NULL,
  `id_employee` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_ws` (`id_ws`,`id_employee`),
  KEY `id_employee` (`id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `employees`
--

CREATE TABLE IF NOT EXISTS `employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `operating_systems`
--

CREATE TABLE IF NOT EXISTS `operating_systems` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `id_role` int(11) NOT NULL,
  `id_employee` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_role` (`id_role`),
  KEY `id_employee` (`id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `workstations`
--

CREATE TABLE IF NOT EXISTS `workstations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(30) NOT NULL,
  `date` datetime NOT NULL,
  `label` varchar(50) NOT NULL,
  `id_os` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_os` (`id_os`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `ws_availabilities`
--

CREATE TABLE IF NOT EXISTS `ws_availabilities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_ws` int(11) NOT NULL,
  `weekday` int(11) NOT NULL,
  `start_time` datetime NOT NULL,
  `stop_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_ws` (`id_ws`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `ws_recordings`
--

CREATE TABLE IF NOT EXISTS `ws_recordings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_ws` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `contacted` tinyint(1) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_ws` (`id_ws`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Structure de la table `properties`
--

CREATE TABLE IF NOT EXISTS `properties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

--
-- Contenu de la table `properties`
--

INSERT INTO `properties` (`id`, `name`, `value`) VALUES
(1, 'lundi_start_time1', 9),
(2, 'mardi_start_time1', 9),
(3, 'mercredi_start_time1', 9),
(4, 'jeudi_start_time1', 9),
(5, 'vendredi_start_time1', 9),
(6, 'samedi_start_time1', 9),
(7, 'dimanche_start_time1', 9),
(8, 'lundi_stop_time1', 12),
(9, 'mardi_stop_time1', 12),
(10, 'mercredi_stop_time1', 12),
(11, 'jeudi_stop_time1', 12),
(12, 'vendredi_stop_time1', 12),
(13, 'samedi_stop_time1', 12),
(14, 'dimanche_stop_time1', 12),
(15, 'lundi_start_time2', 14),
(16, 'mardi_start_time2', 14),
(17, 'mercredi_start_time2', 14),
(18, 'jeudi_start_time2', 14),
(19, 'vendredi_start_time2', 14),
(20, 'samedi_start_time2', 14),
(21, 'dimanche_start_time2', 14),
(22, 'lundi_stop_time2', 17),
(23, 'mardi_stop_time2', 17),
(24, 'mercredi_stop_time2', 17),
(25, 'jeudi_stop_time2', 17),
(26, 'vendredi_stop_time2', 17),
(27, 'samedi_stop_time2', 17),
(28, 'dimanche_stop_time2', 17),
(29, 'ping_frequency', 15);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `asso_ws_employees`
--
ALTER TABLE `asso_ws_employees`
  ADD CONSTRAINT `asso_ws_employees_ibfk_2` FOREIGN KEY (`id_employee`) REFERENCES `employees` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
  ADD CONSTRAINT `asso_ws_employees_ibfk_1` FOREIGN KEY (`id_ws`) REFERENCES `workstations` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`id_employee`) REFERENCES `employees` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;

--
-- Contraintes pour la table `workstations`
--
ALTER TABLE `workstations`
  ADD CONSTRAINT `workstations_ibfk_1` FOREIGN KEY (`id_os`) REFERENCES `operating_systems` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;

--
-- Contraintes pour la table `ws_availabilities`
--
ALTER TABLE `ws_availabilities`
  ADD CONSTRAINT `ws_availabilities_ibfk_1` FOREIGN KEY (`id_ws`) REFERENCES `workstations` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;

--
-- Contraintes pour la table `ws_recordings`
--
ALTER TABLE `ws_recordings`
  ADD CONSTRAINT `ws_recordings_ibfk_1` FOREIGN KEY (`id_ws`) REFERENCES `workstations` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
