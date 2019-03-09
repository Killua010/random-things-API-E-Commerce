CREATE TABLE _pricing_group (
	id bigint auto_increment NOT NULL,
	creation_date datetime NOT NULL,
	last_update datetime NOT NULL,
  	status bit(1) NOT NULL,
  	name varchar(100) NOT NULL,
  	profit_percentage float NOT NULL,
	primary key (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO _pricing_group(creation_date, last_update, status, name, profit_percentage) VALUES (NOW(), NOW(), b'1', "Bronze", 30.0);
INSERT INTO _pricing_group(creation_date, last_update, status, name, profit_percentage) VALUES (NOW(), NOW(), b'1', "Silver", 20.0);
INSERT INTO _pricing_group(creation_date, last_update, status, name, profit_percentage) VALUES (NOW(), NOW(), b'1', "Gold", 10.0);
INSERT INTO _pricing_group(creation_date, last_update, status, name, profit_percentage) VALUES (NOW(), NOW(), b'1', "Platinum", 7.5);