drop table Article cascade constraints;
drop table Fournisseur cascade constraints;
drop table Boutique cascade constraints;
drop table Livraison cascade constraints;

create table Article
(
ID_Article integer,
Nom_Article varchar(30) not null,
Categorie varchar(30) not null,
Quantite integer not null,
ID_Boutique integer not null,
constraint PK_ARTICLE primary key (ID_Article)
)

create table Boutique 
(
ID_Boutique integer not null,
nom_boutique char(20) not null,
tel_boutique char(20),
constraint PK_BOUTIQUE primary key (ID_Boutique)
);

insert into Boutique values (1,'Darty','0666666666');
insert into Boutique values (2,'FNAC','0666666667');

--insertion des donnees dans la table Article pour test
insert into Article values(1,'Ordinateur','Electromenager',5,2);
insert into Article values(2,'Pelle','Construction',2,1);
insert into Article values(3,'Tapis','Decoration',4,2);
insert into Article values(4,'Carte Mere P4','Maintenance',2,1);
insert into Article values(5,'Cable Frein','Piece Rechange',1,2);


Create table Fournisseur
(
ID_Fournisseur int,
Nom_Fournisseur varchar(20),
Categorie_Fournie varchar(30),
Adresse varchar(255),
constraint PK_Fournisseur primary key (ID_Fournisseur)

)

insert into Fournisseur values(1,'CCBM','Electromenager','Dakar/Rte de Rufisque');
insert into Fournisseur values(2,'Senegal Construction','Construction','2 Rue Galandou Diof');
insert into Fournisseur values(3,'Design_Soft','Decoration','Dakar/ Rue Fleurus');
insert into Fournisseur values(4,'Info_Solutions','Maintenance','Dakar-Ville/Centre Comercial 4C');



create table Livraison
(
ID_Livraison integer,
Nom_Article varchar(30) not null,
Quantite integer not null,
ID_Fournisseur integer not null,
ID_Boutique integer not null,
constraint PK_Livraison primary key (ID_Livraison)

)

alter table Livraison
   add constraint FK_Livraison_Fournisseur foreign key (ID_Fournisseur)
       references Fournisseur (ID_Fournisseur);
alter table Livraison
    add constraint FK_Livraison_Boutique foreign key (ID_Boutique) references Boutique(ID_Boutique);


