CREATE TABLE _technical_field (
	id bigint auto_increment NOT NULL,
	creation_date datetime NOT NULL,
	last_update datetime NOT NULL,
  	status bit(1) NOT NULL,
  	name varchar(100) NOT NULL,
	primary key (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO _technical_field(creation_date, last_update, status, name) VALUES (NOW(), NOW(), b'1', "Peso");
INSERT INTO _technical_field(creation_date, last_update, status, name) VALUES (NOW(), NOW(), b'1', "Tamanho");
INSERT INTO _technical_field(creation_date, last_update, status, name) VALUES (NOW(), NOW(), b'1', "Material");
INSERT INTO _technical_field(creation_date, last_update, status, name) VALUES (NOW(), NOW(), b'1', "Autor");
INSERT INTO _technical_field(creation_date, last_update, status, name) VALUES (NOW(), NOW(), b'1', "Quantidade de páginas");
INSERT INTO _technical_field(creation_date, last_update, status, name) VALUES (NOW(), NOW(), b'1', "Editora");