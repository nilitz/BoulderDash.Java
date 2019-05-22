-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 02 Juin 2016 à 23:02
-- Version du serveur :  5.7.11
-- Version de PHP :  5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `jpublankproject`
--
CREATE DATABASE `jpublankproject` ;

USE `jpublankproject` ;

DELIMITER $$
--
-- Procédures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `helloworldById` (IN `p_id` INT)  READS SQL DATA
    SQL SECURITY INVOKER
SELECT * FROM helloworld WHERE id = p_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `HelloworldByCode` (IN `p_code` VARCHAR(2))  READS SQL DATA
    SQL SECURITY INVOKER
SELECT * FROM jpublankproject.helloworld where `code`=p_code$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `helloworld`
--

CREATE TABLE `helloworld` (
  `id` int(11) NOT NULL,
  `code` varchar(2) NOT NULL,
  `message` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `helloworld`
--

INSERT INTO `helloworld` (`id`, `code`, `message`) VALUES
(1, 'GB', 'Hello world'),
(2, 'FR', 'Bonjour le monde'),
(3, 'DE', 'Hallo Welt'),
(4, 'ID', 'Salamat pagi dunia');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `helloworld`
--
ALTER TABLE `helloworld`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code_UNIQUE` (`code`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `helloworld`
--
ALTER TABLE `helloworld`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
