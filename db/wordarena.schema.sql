-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 17 Mars 2015 à 23:54
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
-- Structure de la table `games`
--

CREATE TABLE IF NOT EXISTS `games` (
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
-- Structure de la table `game_chats`
--

CREATE TABLE IF NOT EXISTS `game_chats` (
  `game_id` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `message` int(255) NOT NULL,
  PRIMARY KEY (`game_id`,`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Les messages échangés entre les joueurs';

-- --------------------------------------------------------

--
-- Structure de la table `game_last_play`
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
-- Structure de la table `game_players`
--

CREATE TABLE IF NOT EXISTS `game_players` (
  `game_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `place` int(11) NOT NULL COMMENT 'Ordre de jeu du joueur (premier à jouer, le second...)',
  PRIMARY KEY (`game_id`,`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Les joueurs participant à une partie';

-- --------------------------------------------------------

--
-- Structure de la table `game_words_played`
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
-- Structure de la table `players`
--

CREATE TABLE IF NOT EXISTS `players` (
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

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(50) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `fullname`, `address`, `email`, `mobile`) VALUES
(2, 'Séverine Chaibriant', '5 PL ACACIAS\r\n77420 CHAMPS SUR MARNE', 'severine.chaibriant@gmail.com', '0689162634'),
(3, 'Full Name', 'Test', 'test@gmail.com', '12411515');

-- --------------------------------------------------------

--
-- Structure de la table `words`
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
