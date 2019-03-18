CREATE TABLE _category (
	id bigint auto_increment NOT NULL,
	creation_date datetime NOT NULL,
	last_update datetime NOT NULL,
  	status bit(1) NOT NULL,
  	name varchar(100) NOT NULL,
	primary key (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE _sub_category (
	id bigint auto_increment NOT NULL,
	creation_date datetime NOT NULL,
	last_update datetime NOT NULL,
  	status bit(1) NOT NULL,
  	name varchar(100) NOT NULL,
  	category_id bigint NOT NULL,
  	FOREIGN KEY (category_id) REFERENCES _sub_category(id),
	primary key (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO _category(creation_date, last_update, status, name) VALUES (NOW(), NOW(), 1, 'Moda Masculina');
INSERT INTO _category(creation_date, last_update, status, name) VALUES (NOW(), NOW(), 1, 'Moda Feminina');
INSERT INTO _category(creation_date, last_update, status, name) VALUES (NOW(), NOW(), 1, 'Livros');

INSERT INTO _sub_category(creation_date, last_update, status, name, category_id) VALUES (NOW(), NOW(), 1, 'Calça', 1);
INSERT INTO _sub_category(creation_date, last_update, status, name, category_id) VALUES (NOW(), NOW(), 1, 'Camisa', 1);
INSERT INTO _sub_category(creation_date, last_update, status, name, category_id) VALUES (NOW(), NOW(), 1, 'Meia', 1);

INSERT INTO _sub_category(creation_date, last_update, status, name, category_id) VALUES (NOW(), NOW(), 1, 'Vestido', 2);
INSERT INTO _sub_category(creation_date, last_update, status, name, category_id) VALUES (NOW(), NOW(), 1, 'Camisa', 2);
INSERT INTO _sub_category(creation_date, last_update, status, name, category_id) VALUES (NOW(), NOW(), 1, 'Saia', 2);

INSERT INTO _sub_category(creation_date, last_update, status, name, category_id) VALUES (NOW(), NOW(), 1, 'Romance', 3);
INSERT INTO _sub_category(creation_date, last_update, status, name, category_id) VALUES (NOW(), NOW(), 1, 'Terror', 3);
INSERT INTO _sub_category(creation_date, last_update, status, name, category_id) VALUES (NOW(), NOW(), 1, 'Ficção', 3);
