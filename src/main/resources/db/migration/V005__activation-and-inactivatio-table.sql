CREATE TABLE _activation (
	id bigint auto_increment NOT NULL,
	creation_date datetime NOT NULL,
	last_update datetime NOT NULL,
  	status bit(1) NOT NULL,
  	description varchar(100) NOT NULL,
  	product_id bigint NOT NULL,
  	status_activation varchar(255) NOT NULL,
  	FOREIGN KEY (product_id) REFERENCES _product(id),
	primary key (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE _inactivation (
	id bigint auto_increment NOT NULL,
	creation_date datetime NOT NULL,
	last_update datetime NOT NULL,
  	status bit(1) NOT NULL,
  	description varchar(100) NOT NULL,
  	product_id bigint NOT NULL,
  	status_inactivation varchar(255) NOT NULL,
  	FOREIGN KEY (product_id) REFERENCES _product(id),
	primary key (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
