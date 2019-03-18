CREATE TABLE _product (
	id bigint auto_increment NOT NULL,
	creation_date datetime NOT NULL,
	last_update datetime NOT NULL,
  	status bit(1) NOT NULL,
  	name varchar(100) NOT NULL,
  	description varchar(10000) NOT NULL,
  	bar_code  varchar(255),
  	price float,
  	pricing_group_id bigint NOT NULL,
  	FOREIGN KEY (pricing_group_id) REFERENCES _pricing_group(id),
	primary key (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE _technical_row (
	id bigint auto_increment NOT NULL,
	creation_date datetime NOT NULL,
	last_update datetime NOT NULL,
  	status bit(1) NOT NULL,
  	description varchar(100) NOT NULL,
  	technical_field_id bigint NOT NULL,
  	product_id bigint NOT NULL,
  	FOREIGN KEY (technical_field_id) REFERENCES _technical_field(id),
  	FOREIGN KEY (product_id) REFERENCES _product(id),
	primary key (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE _product_sub_category (
	product_id2 bigint NOT NULL,
	sub_category_id bigint NOT NULL,
  	FOREIGN KEY (product_id2) REFERENCES _product(id),
  	FOREIGN KEY (sub_category_id) REFERENCES _sub_category(id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO _product(creation_date, last_update, status, name, description, bar_code, pricing_group_id) VALUES (NOW(), NOW(), 1, 'Camisa Polo', 'A Camisa Polo RG 518 Piquet Bordado Contraste Color garante estilo para você aproveitar todos os momentos. Feita em tecido macio, oferece um visual moderno e um caimento confortável.', '0250101972', 2);
INSERT INTO _product(creation_date, last_update, status, name, description, bar_code, pricing_group_id) VALUES (NOW(), NOW(), 1, 'Camisa Lisa', 'A Camisa Lisa é confeccionada em algodão e elastano. Possui mangas longas, fechamento por abotoamento e gola dobrada. Seu tecido leve e macio permite uma produção confortável e elegante para festas formais ou casamentos. Combine com calça de alfaiataria e sapato.', '0250108572', 1);
INSERT INTO _product(creation_date, last_update, status, name, description, bar_code, pricing_group_id) VALUES (NOW(), NOW(), 1, 'O Alquimista', 'O alquimista é a mágica história de Santiago, um menino pastor andaluz que anseia por viajar em busca do tesouro mais magnífico do mundo. De sua casa na Espanha ele parte para os mercados do Tânger e através do deserto egípcio para um encontro do destino com o alquimista. A história dos tesouros que Santiago encontra ao longo de sua jornada nos ensina, como poucas histórias fizeram, sobre a sabedoria de escutarmos nossos corações, aprendendo a ler os sinais que aparecem ao longo do caminho de nossas vidas e, acima de tudo, a seguir nossos sonhos.', '0250101992', 3);
INSERT INTO _product(creation_date, last_update, status, name, description, bar_code, pricing_group_id) VALUES (NOW(), NOW(), 1, 'A Revolução Dos Bichos', 'Verdadeiro clássico moderno, concebido por um dos mais influentes escritores do século 20, "A Revolução dos Bichos" é uma fábula sobre o poder. Narra a insurreição dos animais de uma granja contra seus donos. Progressivamente, porém, a revolução degenera numa tirania ainda mais opressiva que a dos humanos Escrita em plena Segunda Guerra Mundial e publicada em 1945 depois de ter sido rejeitada por várias editoras, essa pequena narrativa causou desconforto ao satirizar ferozmente a ditadura stalinista numa época em que os soviéticos ainda eram aliados do Ocidente na luta contra o eixo nazifascista. De fato, são claras as referências: o despótico Napoleão seria Stálin, o banido Bola-de-Neve seria Trotsky, e os eventos políticos - expurgos, instituição de um estado policial, deturpação tendenciosa da História - mimetizam os que estavam em curso na União Soviética. Com o acirramento da Guerra Fria, as mesmas razões que causaram constrangimento na época de sua publicação levaram A revolução dos bichos a ser amplamente usada pelo Ocidente nas décadas seguintes como arma ideológica contra o comunismo. O próprio Orwell, adepto do socialismo e inimigo de qualquer forma de manipulação política, sentiu-se incomodado com a utilização de sua fábula como panfleto.', '0256931972', 4);

INSERT INTO _product_sub_category(product_id2, sub_category_id) VALUES (1, 2);
INSERT INTO _product_sub_category(product_id2, sub_category_id) VALUES (2, 2);
INSERT INTO _product_sub_category(product_id2, sub_category_id) VALUES (2, 5);
INSERT INTO _product_sub_category(product_id2, sub_category_id) VALUES (3, 9);
INSERT INTO _product_sub_category(product_id2, sub_category_id) VALUES (4, 9); 

INSERT INTO _technical_row(creation_date, last_update, status, description, technical_field_id, product_id) VALUES (NOW(), NOW(), 1, 'M', 2, 1);
INSERT INTO _technical_row(creation_date, last_update, status, description, technical_field_id, product_id) VALUES (NOW(), NOW(), 1, 'Algodão', 3, 1);
INSERT INTO _technical_row(creation_date, last_update, status, description, technical_field_id, product_id) VALUES (NOW(), NOW(), 1, '0.3 Kg', 1, 1);

INSERT INTO _technical_row(creation_date, last_update, status, description, technical_field_id, product_id) VALUES (NOW(), NOW(), 1, 'M', 2, 2);
INSERT INTO _technical_row(creation_date, last_update, status, description, technical_field_id, product_id) VALUES (NOW(), NOW(), 1, 'Algodão', 3, 2);
INSERT INTO _technical_row(creation_date, last_update, status, description, technical_field_id, product_id) VALUES (NOW(), NOW(), 1, '0.3 Kg', 1, 2);

INSERT INTO _technical_row(creation_date, last_update, status, description, technical_field_id, product_id) VALUES (NOW(), NOW(), 1, '0.5 Kg', 1, 3);
INSERT INTO _technical_row(creation_date, last_update, status, description, technical_field_id, product_id) VALUES (NOW(), NOW(), 1, 'Paulo Coelho', 4, 3);
INSERT INTO _technical_row(creation_date, last_update, status, description, technical_field_id, product_id) VALUES (NOW(), NOW(), 1, 'Paralela', 6, 3);

INSERT INTO _technical_row(creation_date, last_update, status, description, technical_field_id, product_id) VALUES (NOW(), NOW(), 1, '0.8 Kg', 1, 4);
INSERT INTO _technical_row(creation_date, last_update, status, description, technical_field_id, product_id) VALUES (NOW(), NOW(), 1, 'George Orwell', 4, 4);
INSERT INTO _technical_row(creation_date, last_update, status, description, technical_field_id, product_id) VALUES (NOW(), NOW(), 1, 'Companhia Das Letras', 5, 4);
INSERT INTO _technical_row(creation_date, last_update, status, description, technical_field_id, product_id) VALUES (NOW(), NOW(), 1, '152', 6, 4);

