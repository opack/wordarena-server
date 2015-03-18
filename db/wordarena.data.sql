-- Ce script est destin� � initialiser la base. Celle-ci doit donc �tre vide et ne contenir aucune partie.

-- Table des titres
DELETE FROM wordarena.titles;
INSERT INTO wordarena.titles (id, title, nb_required_stars)
VALUES (0, 'provocator', 0),
	(1, 'thrace', 0),
	(2, 'mirmillon', 0),
	(3, 'homaque', 0),
	(4, 'secutor', 0),
	(5, 'retiaire', 0);

-- Table des b�n�dictions
DELETE FROM wordarena.blessings;
INSERT INTO wordarena.blessings (blessing)
VALUES ('venus'),
	('phebus'),
	('mars'),
	('diane'),
	('minerve'),
	('pluton'),
	('bacchus'),
	('vulcain'),
	('junon'),
	('mercure'),
	('neptune'),
	('jupiter');
