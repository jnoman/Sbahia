create table role(
  id serial NOT NULL primary key,
  nomRole varchar(20) NOT NULL
);

insert into Role values(1,'admin'),(2,'client');

create table users (
  id serial NOT NULL primary key,
  nom_complet varchar(20) NOT NULL,
  Email varchar(50) NOT NULL,
  Role int NOT NULL Default 2 REFERENCES Role (id),
  password varchar(30) NOT NULL
);

create table produit (
  id serial NOT NULL primary key,
  nom varchar(100) NOT NULL,
  description varchar(1000) NOT NULL,
  image bytea NOT NULL,
  quantite int not null,
  prix FLOAT
);

create table likes(
  idUser int NOT NULL REFERENCES users (id) ON DELETE CASCADE,
  idProduit int NOT NULL REFERENCES produit (id) ON DELETE CASCADE,
  PRIMARY KEY (idUser, idProduit)
);

INSERT into users values(1,'bahia admin','admin@bahia.com',1,'aaaaaaaa');

