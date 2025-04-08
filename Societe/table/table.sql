CREATE DATABASE servletexam;
use servletexam;

create table prevision(
    id int primary key AUTO_INCREMENT,
    libelle varchar(100),
    montant int
);

create table depense(
    id int primary key AUTO_INCREMENT,
    idPrevision int,
    montant int
);