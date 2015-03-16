-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 17 Mars 2015 à 00:00
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `wordarena`
--

-- --------------------------------------------------------

--
-- Structure de la table `blessings`
--

CREATE TABLE IF NOT EXISTS `blessings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blessing` varchar(50) NOT NULL COMMENT 'Bénédiction',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Bénédictions que peut gagner un joueur' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `game`
--

CREATE TABLE IF NOT EXISTS `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Heure de création de la partie',
  `type_id` varchar(1) NOT NULL COMMENT 'Type de partie',
  `cur_round` int(11) NOT NULL COMMENT 'Round courant',
  `cur_turn` int(11) NOT NULL COMMENT 'Tour courant',
  `cur_player_ox` int(11) NOT NULL COMMENT 'Numéro d''ordre du joueur courant',
  `first_player_ox` int(11) NOT NULL COMMENT 'Numéro d''ordre du premier joueur du round',
  `game_over` tinyint(1) NOT NULL COMMENT 'Indique si la partie est terminée',
  `arena` blob NOT NULL COMMENT 'JSON de l''arène au moment où la partie a été sauvegardée',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Une partie' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `player`
--

CREATE TABLE IF NOT EXISTS `player` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identifiant unique de joueur',
  `pseudo` varchar(25) NOT NULL COMMENT 'Pseudonyme',
  `title_id` int(11) NOT NULL COMMENT 'Identifiant du titre actuellement possédé',
  `score` int(11) NOT NULL COMMENT 'Nombre de points du joueur pour les ligues et le match-making',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pseudo` (`pseudo`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Données sur un joueur' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `player_blessings`
--

CREATE TABLE IF NOT EXISTS `player_blessings` (
  `player_id` int(11) NOT NULL,
  `blessing_id` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`blessing_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Bénédictions possédées par un joueur';

-- --------------------------------------------------------

--
-- Structure de la table `titles`
--

CREATE TABLE IF NOT EXISTS `titles` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL COMMENT 'Libellé du titre',
  `nb_required_stars` int(11) NOT NULL DEFAULT '0' COMMENT 'Nombre d''étoiles requises pour obtenir le titre',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Titre gagné par le joueur';

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
