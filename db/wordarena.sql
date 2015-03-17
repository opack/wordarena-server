-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 17, 2015 at 03:52 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `wordarena`
--

-- --------------------------------------------------------

--
-- Table structure for table `blessings`
--

CREATE TABLE IF NOT EXISTS `blessings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blessing` varchar(50) NOT NULL COMMENT 'Bénédiction',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Bénédictions que peut gagner un joueur' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE IF NOT EXISTS `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Heure de création de la partie',
  `type_id` varchar(1) NOT NULL COMMENT 'Type de partie',
  `cur_round` int(11) NOT NULL COMMENT 'Round courant',
  `cur_turn` int(11) NOT NULL COMMENT 'Tour courant',
  `cur_player` int(11) NOT NULL COMMENT 'Numéro d''ordre du joueur courant',
  `first_player` int(11) NOT NULL COMMENT 'Numéro d''ordre du premier joueur du round',
  `game_over` tinyint(1) NOT NULL COMMENT 'Indique si la partie est terminée',
  `arena` blob NOT NULL COMMENT 'JSON de l''arène au moment où la partie a été sauvegardée',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Une partie' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `game_chats`
--

CREATE TABLE IF NOT EXISTS `game_chats` (
  `game_id` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `message` int(255) NOT NULL,
  PRIMARY KEY (`game_id`,`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Les messages échangés entre les joueurs';

-- --------------------------------------------------------

--
-- Table structure for table `game_last_play`
--

CREATE TABLE IF NOT EXISTS `game_last_play` (
  `game_id` int(11) NOT NULL,
  `place` int(11) NOT NULL COMMENT 'Ordre de sélection de la lettre',
  `cell_x` int(11) NOT NULL COMMENT 'Abscisse de la cellule jouée',
  `cell_y` int(11) NOT NULL COMMENT 'Ordonnée de la cellule jouée',
  PRIMARY KEY (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Dernières cellules sélectionnées, dans l''ordre.';

-- --------------------------------------------------------

--
-- Table structure for table `game_players`
--

CREATE TABLE IF NOT EXISTS `game_players` (
  `game_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `place` int(11) NOT NULL COMMENT 'Ordre de jeu du joueur (premier à jouer, le second...)',
  PRIMARY KEY (`game_id`,`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Les joueurs participant à une partie';

-- --------------------------------------------------------

--
-- Table structure for table `game_words_played`
--

CREATE TABLE IF NOT EXISTS `game_words_played` (
  `game_id` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `player_id` int(11) NOT NULL,
  `word_id` int(11) NOT NULL,
  PRIMARY KEY (`game_id`,`time`,`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Les mots précédemment joués, dans l''ordre';

-- --------------------------------------------------------

--
-- Table structure for table `player`
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
-- Table structure for table `player_blessings`
--

CREATE TABLE IF NOT EXISTS `player_blessings` (
  `player_id` int(11) NOT NULL,
  `blessing_id` int(11) NOT NULL,
  PRIMARY KEY (`player_id`,`blessing_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Bénédictions possédées par un joueur';

-- --------------------------------------------------------

--
-- Table structure for table `titles`
--

CREATE TABLE IF NOT EXISTS `titles` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL COMMENT 'Libellé du titre',
  `nb_required_stars` int(11) NOT NULL DEFAULT '0' COMMENT 'Nombre d''étoiles requises pour obtenir le titre',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Titre gagné par le joueur';

-- --------------------------------------------------------

--
-- Table structure for table `words`
--

CREATE TABLE IF NOT EXISTS `words` (
  `id` int(11) NOT NULL,
  `word` varchar(255) NOT NULL COMMENT 'Le mot (en majuscules)',
  `status` int(11) NOT NULL COMMENT 'Indique si le mot est valide (V), en attente de validation (P) ou supprimé (R)',
  `version` int(11) NOT NULL COMMENT 'Indique la version dans laquelle a eut lieu la dernière modification de status'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Dictionnaire contenant les mots existants';

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
