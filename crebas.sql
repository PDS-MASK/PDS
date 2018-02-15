/*==============================================================*/
/* Nom de SGBD :  ORACLE Version 11g                            */
/* Date de création :  15/02/2018 22:21:11                      */
/*==============================================================*/


alter table COMMANDA_SOUSARTICLE
   drop constraint FK_COMMANDA_COMMANDA__SOUS_ART;

alter table COMMANDA_SOUSARTICLE
   drop constraint FK_COMMANDA_COMMANDA__COMMANDE;

alter table COMMANDE
   drop constraint FK_COMMANDE_PERSONNE__PERSONNE;

alter table EVENEMENT_BOUTIQUE
   drop constraint FK_EVENEMEN_EVENEMENT_EVENEMEN;

alter table EVENEMENT_BOUTIQUE
   drop constraint FK_EVENEMEN_EVENEMENT_BOUTIQUE;

alter table FOURNISSEUR
   drop constraint FK_FOURNISS_VILLE_FOU_VILLE;

alter table HISTORIQUE_EMPLACEMENT
   drop constraint FK_HISTORIQ_ASSOCIATI_EMPLACEM;

alter table HISTORIQUE_EMPLACEMENT
   drop constraint FK_HISTORIQ_BOUTIQUE__BOUTIQUE;

alter table LIVRAISON
   drop constraint FK_LIVRAISO_LIVRAISON_FOURNISS;

alter table LIVRAISON_SOUSARTICLE
   drop constraint FK_LIVRAISO_LIVRAISON_LIVRAISO;

alter table LIVRAISON_SOUSARTICLE
   drop constraint FK_LIVRAISO_LIVRAISON_SOUS_ART;

alter table MATIERE_ARTICLE
   drop constraint FK_MATIERE__MATIERE_A_MATIERE;

alter table MATIERE_ARTICLE
   drop constraint FK_MATIERE__MATIERE_A_ARTICLE;

alter table OUVERTURE_BOUTIQUE
   drop constraint FK_OUVERTUR_OUVERTURE_JOURS;

alter table OUVERTURE_BOUTIQUE
   drop constraint FK_OUVERTUR_OUVERTURE_BOUTIQUE;

alter table PERSONNE
   drop constraint FK_PERSONNE_VILLE_PER_VILLE;

alter table REDUCTION
   drop constraint FK_REDUCTIO_REDUCTION_SOUS_ART;

alter table SOUSCATEGORIE_ARTICLE
   drop constraint FK_SOUSCATE_SOUSCATEG_SOUS_CAT;

alter table SOUSCATEGORIE_ARTICLE
   drop constraint FK_SOUSCATE_SOUSCATEG_ARTICLE;

alter table SOUS_ARTICLE
   drop constraint FK_SOUS_ART_SOUSARTIC_ARTICLE;

alter table SOUS_ARTICLE
   drop constraint FK_SOUS_ART_SOUSARTIC_BOUTIQUE;

alter table SOUS_CATEGORIE
   drop constraint FK_SOUS_CAT_CATEGORIE_CATEGORI;

alter table STATUT_PERSONNE_BOUTIQUE
   drop constraint FK_STATUT_P_STATUT_PE_BOUTIQUE;

alter table STATUT_PERSONNE_BOUTIQUE
   drop constraint FK_STATUT_P_STATUT_PE_PERSONNE;

alter table STATUT_PERSONNE_BOUTIQUE
   drop constraint FK_STATUT_P_STATUT_PE_STATUT;

alter table VILLE
   drop constraint FK_VILLE_PAYS_VILL_PAYS;

drop table ARTICLE cascade constraints;

drop table BOUTIQUE cascade constraints;

drop table CATEGORIE cascade constraints;

drop table COMMANDA_SOUSARTICLE cascade constraints;

drop table COMMANDE cascade constraints;

drop table EMPLACEMENT cascade constraints;

drop table EVENEMENT cascade constraints;

drop table EVENEMENT_BOUTIQUE cascade constraints;

drop table FOURNISSEUR cascade constraints;

drop table HISTORIQUE_EMPLACEMENT cascade constraints;

drop table JOURS cascade constraints;

drop table LIVRAISON cascade constraints;

drop table LIVRAISON_SOUSARTICLE cascade constraints;

drop table MATIERE cascade constraints;

drop table MATIERE_ARTICLE cascade constraints;

drop table OUVERTURE_BOUTIQUE cascade constraints;

drop table PAYS cascade constraints;

drop table PERSONNE cascade constraints;

drop table REDUCTION cascade constraints;

drop table SOUSCATEGORIE_ARTICLE cascade constraints;

drop table SOUS_ARTICLE cascade constraints;

drop table SOUS_CATEGORIE cascade constraints;

drop table STATUT cascade constraints;

drop table STATUT_PERSONNE_BOUTIQUE cascade constraints;

drop table VILLE cascade constraints;

/*==============================================================*/
/* Table : ARTICLE                                              */
/*==============================================================*/
create table ARTICLE 
(
   ID_ARTICLE           INTEGER              not null,
   NOM_ARTICLE          CHAR(20)             not null,
   CATEGORIE_ARTICLE    CHAR(10)             not null,
   GENRE                CHAR(10)             not null,
   DESCRIPTION_ARTICLE  CLOB,
   constraint PK_ARTICLE primary key (ID_ARTICLE)
);

/*==============================================================*/
/* Table : BOUTIQUE                                             */
/*==============================================================*/
create table BOUTIQUE 
(
   ID_BOUTIQUE          INTEGER              not null,
   NOM_BOUTIQUE         CHAR(20)             not null,
   TEL_BOUTIQUE         CHAR(20),
   constraint PK_BOUTIQUE primary key (ID_BOUTIQUE)
);

/*==============================================================*/
/* Table : CATEGORIE                                            */
/*==============================================================*/
create table CATEGORIE 
(
   ID_CAEGORIE          INTEGER              not null,
   NOM_CATEGORIE        CHAR(20)             not null,
   constraint PK_CATEGORIE primary key (ID_CAEGORIE)
);

/*==============================================================*/
/* Table : COMMANDA_SOUSARTICLE                                 */
/*==============================================================*/
create table COMMANDA_SOUSARTICLE 
(
   ID_COMMANDE          INTEGER              not null,
   ID_SOUSARTICLE       INTEGER              not null,
   PRIX_COMMANDE        FLOAT(10),
   QUANTITE_COMMANDE    INTEGER,
   constraint PK_COMMANDA_SOUSARTICLE primary key (ID_COMMANDE, ID_SOUSARTICLE)
);

/*==============================================================*/
/* Table : COMMANDE                                             */
/*==============================================================*/
create table COMMANDE 
(
   ID_COMMANDE          INTEGER              not null,
   ID_PERSONNE          INTEGER,
   DATE_COMMANDE        DATE                 not null,
   constraint PK_COMMANDE primary key (ID_COMMANDE)
);

/*==============================================================*/
/* Table : EMPLACEMENT                                          */
/*==============================================================*/
create table EMPLACEMENT 
(
   ID_EMPLACEMENT       INTEGER              not null,
   NOM_EMPLACELEMENT    CHAR(20),
   DISPONIBILITE        RAW(1)               not null,
   SUPERFICIE           FLOAT(10)            not null,
   REDEVANCE            FLOAT(10)            not null,
   constraint PK_EMPLACEMENT primary key (ID_EMPLACEMENT)
);

/*==============================================================*/
/* Table : EVENEMENT                                            */
/*==============================================================*/
create table EVENEMENT 
(
   ID_EVENEMENT         INTEGER              not null,
   NOM_EVENEMENT        CHAR(20)             not null,
   DATE_DEBUT_EVNT      TIMESTAMP            not null,
   DATE_FIN_EVNT        TIMESTAMP            not null,
   DESCRIPTION_EVENEMENT CHAR(100),
   constraint PK_EVENEMENT primary key (ID_EVENEMENT)
);

/*==============================================================*/
/* Table : EVENEMENT_BOUTIQUE                                   */
/*==============================================================*/
create table EVENEMENT_BOUTIQUE 
(
   ID_BOUTIQUE          INTEGER              not null,
   ID_EVENEMENT         INTEGER              not null,
   constraint PK_EVENEMENT_BOUTIQUE primary key (ID_BOUTIQUE, ID_EVENEMENT)
);

/*==============================================================*/
/* Table : FOURNISSEUR                                          */
/*==============================================================*/
create table FOURNISSEUR 
(
   ID_FOURNISSEUR       INTEGER              not null,
   ID_VILLE             INTEGER,
   NOM_FOURNISSEUR      CHAR(20)             not null,
   TEL_FOURNISSEUR      CHAR(20)             not null,
   ADRESSE_FOURNISSEUR  CHAR(30)             not null,
   CODE_POSTALE         CHAR(10),
   MAIL_FOURNISSEUR     CHAR(20),
   constraint PK_FOURNISSEUR primary key (ID_FOURNISSEUR)
);

/*==============================================================*/
/* Table : HISTORIQUE_EMPLACEMENT                               */
/*==============================================================*/
create table HISTORIQUE_EMPLACEMENT 
(
   ID_HISTORIQUE        INTEGER              not null,
   ID_EMPLACEMENT       INTEGER              not null,
   ID_BOUTIQUE          INTEGER              not null,
   DATE_DEBUT           DATE                 not null,
   DATE_                DATE,
   REDEVANCE            FLOAT(10)            not null,
   constraint PK_HISTORIQUE_EMPLACEMENT primary key (ID_HISTORIQUE)
);

/*==============================================================*/
/* Table : JOURS                                                */
/*==============================================================*/
create table JOURS 
(
   ID_JOURS             INTEGER              not null,
   NOM_JOURS            CHAR(10),
   constraint PK_JOURS primary key (ID_JOURS)
);

/*==============================================================*/
/* Table : LIVRAISON                                            */
/*==============================================================*/
create table LIVRAISON 
(
   ID_LIVRAISON         INTEGER              not null,
   ID_FOURNISSEUR       INTEGER              not null,
   DATE_ACHAT           DATE                 not null,
   constraint PK_LIVRAISON primary key (ID_LIVRAISON)
);

/*==============================================================*/
/* Table : LIVRAISON_SOUSARTICLE                                */
/*==============================================================*/
create table LIVRAISON_SOUSARTICLE 
(
   ID_SOUSARTICLE       INTEGER              not null,
   ID_LIVRAISON         INTEGER              not null,
   PRIX_LIV             FLOAT(10),
   QUANTITE_LIV         INTEGER,
   constraint PK_LIVRAISON_SOUSARTICLE primary key (ID_SOUSARTICLE, ID_LIVRAISON)
);

/*==============================================================*/
/* Table : MATIERE                                              */
/*==============================================================*/
create table MATIERE 
(
   ID_MATIERE           INTEGER              not null,
   NOM_MATIERE          CHAR(20)             not null,
   constraint PK_MATIERE primary key (ID_MATIERE)
);

/*==============================================================*/
/* Table : MATIERE_ARTICLE                                      */
/*==============================================================*/
create table MATIERE_ARTICLE 
(
   ID_ARTICLE           INTEGER              not null,
   ID_MATIERE           INTEGER              not null,
   constraint PK_MATIERE_ARTICLE primary key (ID_ARTICLE, ID_MATIERE)
);

/*==============================================================*/
/* Table : OUVERTURE_BOUTIQUE                                   */
/*==============================================================*/
create table OUVERTURE_BOUTIQUE 
(
   ID_BOUTIQUE          INTEGER              not null,
   ID_JOURS             INTEGER              not null,
   H_OUVERTURE_MATIN    DATE,
   H_FERMETURE_MATIN    DATE,
   H_OUVERTURE_APREM    DATE,
   H_FERMETURE_APREM    DATE,
   constraint PK_OUVERTURE_BOUTIQUE primary key (ID_BOUTIQUE, ID_JOURS)
);

/*==============================================================*/
/* Table : PAYS                                                 */
/*==============================================================*/
create table PAYS 
(
   ID_PAYS              INTEGER              not null,
   NOM_PAYS             CHAR(30)             not null,
   constraint PK_PAYS primary key (ID_PAYS)
);

/*==============================================================*/
/* Table : PERSONNE                                             */
/*==============================================================*/
create table PERSONNE 
(
   ID_PERSONNE          INTEGER              not null,
   ID_VILLE             INTEGER,
   NOM_PERSONNE         CHAR(20)             not null,
   SEXE_PERSONNE        CHAR(1),
   DATE_NAISSANCE_PERSONNE DATE,
   TELEPHONE_PERSONNE   CHAR(20),
   ADRESSE_PERSONNE     CHAR(30),
   constraint PK_PERSONNE primary key (ID_PERSONNE)
);

/*==============================================================*/
/* Table : REDUCTION                                            */
/*==============================================================*/
create table REDUCTION 
(
   ID_REDUCTION         INTEGER              not null,
   ID_SOUSARTICLE       INTEGER,
   POURCENTAGE          INTEGER              not null,
   DATE_DEBUT_REDUC     TIMESTAMP            not null,
   DATE_FIN_REDUC       TIMESTAMP            not null,
   constraint PK_REDUCTION primary key (ID_REDUCTION)
);

/*==============================================================*/
/* Table : SOUSCATEGORIE_ARTICLE                                */
/*==============================================================*/
create table SOUSCATEGORIE_ARTICLE 
(
   ID_ARTICLE           INTEGER              not null,
   ID_SOUSCATEGORIE     INTEGER              not null,
   constraint PK_SOUSCATEGORIE_ARTICLE primary key (ID_ARTICLE, ID_SOUSCATEGORIE)
);

/*==============================================================*/
/* Table : SOUS_ARTICLE                                         */
/*==============================================================*/
create table SOUS_ARTICLE 
(
   ID_SOUSARTICLE       INTEGER              not null,
   ID_BOUTIQUE          INTEGER              not null,
   ID_ARTICLE           INTEGER              not null,
   TAILLE               CHAR(10)             not null,
   COULEUR              CHAR(10)             not null,
   PRIX                 FLOAT(10)            not null,
   STOCK                INTEGER              not null,
   constraint PK_SOUS_ARTICLE primary key (ID_SOUSARTICLE)
);

/*==============================================================*/
/* Table : SOUS_CATEGORIE                                       */
/*==============================================================*/
create table SOUS_CATEGORIE 
(
   ID_SOUSCATEGORIE     INTEGER              not null,
   ID_CAEGORIE          INTEGER              not null,
   NOM_SOUSCATEGORIE    CHAR(20)             not null,
   constraint PK_SOUS_CATEGORIE primary key (ID_SOUSCATEGORIE)
);

/*==============================================================*/
/* Table : STATUT                                               */
/*==============================================================*/
create table STATUT 
(
   ID_STATUT            INTEGER              not null,
   NOM_STATUT           CHAR(20)             not null,
   constraint PK_STATUT primary key (ID_STATUT)
);

/*==============================================================*/
/* Table : STATUT_PERSONNE_BOUTIQUE                             */
/*==============================================================*/
create table STATUT_PERSONNE_BOUTIQUE 
(
   ID_PERSONNE          INTEGER              not null,
   ID_STATUT            INTEGER              not null,
   ID_BOUTIQUE          INTEGER              not null,
   constraint PK_STATUT_PERSONNE_BOUTIQUE primary key (ID_PERSONNE, ID_STATUT, ID_BOUTIQUE)
);

/*==============================================================*/
/* Table : VILLE                                                */
/*==============================================================*/
create table VILLE 
(
   ID_VILLE             INTEGER              not null,
   ID_PAYS              INTEGER              not null,
   NOM_VILLE            CHAR(30)             not null,
   constraint PK_VILLE primary key (ID_VILLE)
);

alter table COMMANDA_SOUSARTICLE
   add constraint FK_COMMANDA_COMMANDA__SOUS_ART foreign key (ID_SOUSARTICLE)
      references SOUS_ARTICLE (ID_SOUSARTICLE);

alter table COMMANDA_SOUSARTICLE
   add constraint FK_COMMANDA_COMMANDA__COMMANDE foreign key (ID_COMMANDE)
      references COMMANDE (ID_COMMANDE);

alter table COMMANDE
   add constraint FK_COMMANDE_PERSONNE__PERSONNE foreign key (ID_PERSONNE)
      references PERSONNE (ID_PERSONNE);

alter table EVENEMENT_BOUTIQUE
   add constraint FK_EVENEMEN_EVENEMENT_EVENEMEN foreign key (ID_EVENEMENT)
      references EVENEMENT (ID_EVENEMENT);

alter table EVENEMENT_BOUTIQUE
   add constraint FK_EVENEMEN_EVENEMENT_BOUTIQUE foreign key (ID_BOUTIQUE)
      references BOUTIQUE (ID_BOUTIQUE);

alter table FOURNISSEUR
   add constraint FK_FOURNISS_VILLE_FOU_VILLE foreign key (ID_VILLE)
      references VILLE (ID_VILLE);

alter table HISTORIQUE_EMPLACEMENT
   add constraint FK_HISTORIQ_ASSOCIATI_EMPLACEM foreign key (ID_EMPLACEMENT)
      references EMPLACEMENT (ID_EMPLACEMENT);

alter table HISTORIQUE_EMPLACEMENT
   add constraint FK_HISTORIQ_BOUTIQUE__BOUTIQUE foreign key (ID_BOUTIQUE)
      references BOUTIQUE (ID_BOUTIQUE);

alter table LIVRAISON
   add constraint FK_LIVRAISO_LIVRAISON_FOURNISS foreign key (ID_FOURNISSEUR)
      references FOURNISSEUR (ID_FOURNISSEUR);

alter table LIVRAISON_SOUSARTICLE
   add constraint FK_LIVRAISO_LIVRAISON_LIVRAISO foreign key (ID_LIVRAISON)
      references LIVRAISON (ID_LIVRAISON);

alter table LIVRAISON_SOUSARTICLE
   add constraint FK_LIVRAISO_LIVRAISON_SOUS_ART foreign key (ID_SOUSARTICLE)
      references SOUS_ARTICLE (ID_SOUSARTICLE);

alter table MATIERE_ARTICLE
   add constraint FK_MATIERE__MATIERE_A_MATIERE foreign key (ID_MATIERE)
      references MATIERE (ID_MATIERE);

alter table MATIERE_ARTICLE
   add constraint FK_MATIERE__MATIERE_A_ARTICLE foreign key (ID_ARTICLE)
      references ARTICLE (ID_ARTICLE);

alter table OUVERTURE_BOUTIQUE
   add constraint FK_OUVERTUR_OUVERTURE_JOURS foreign key (ID_JOURS)
      references JOURS (ID_JOURS);

alter table OUVERTURE_BOUTIQUE
   add constraint FK_OUVERTUR_OUVERTURE_BOUTIQUE foreign key (ID_BOUTIQUE)
      references BOUTIQUE (ID_BOUTIQUE);

alter table PERSONNE
   add constraint FK_PERSONNE_VILLE_PER_VILLE foreign key (ID_VILLE)
      references VILLE (ID_VILLE);

alter table REDUCTION
   add constraint FK_REDUCTIO_REDUCTION_SOUS_ART foreign key (ID_SOUSARTICLE)
      references SOUS_ARTICLE (ID_SOUSARTICLE);

alter table SOUSCATEGORIE_ARTICLE
   add constraint FK_SOUSCATE_SOUSCATEG_SOUS_CAT foreign key (ID_SOUSCATEGORIE)
      references SOUS_CATEGORIE (ID_SOUSCATEGORIE);

alter table SOUSCATEGORIE_ARTICLE
   add constraint FK_SOUSCATE_SOUSCATEG_ARTICLE foreign key (ID_ARTICLE)
      references ARTICLE (ID_ARTICLE);

alter table SOUS_ARTICLE
   add constraint FK_SOUS_ART_SOUSARTIC_ARTICLE foreign key (ID_ARTICLE)
      references ARTICLE (ID_ARTICLE);

alter table SOUS_ARTICLE
   add constraint FK_SOUS_ART_SOUSARTIC_BOUTIQUE foreign key (ID_BOUTIQUE)
      references BOUTIQUE (ID_BOUTIQUE);

alter table SOUS_CATEGORIE
   add constraint FK_SOUS_CAT_CATEGORIE_CATEGORI foreign key (ID_CAEGORIE)
      references CATEGORIE (ID_CAEGORIE);

alter table STATUT_PERSONNE_BOUTIQUE
   add constraint FK_STATUT_P_STATUT_PE_BOUTIQUE foreign key (ID_BOUTIQUE)
      references BOUTIQUE (ID_BOUTIQUE);

alter table STATUT_PERSONNE_BOUTIQUE
   add constraint FK_STATUT_P_STATUT_PE_PERSONNE foreign key (ID_PERSONNE)
      references PERSONNE (ID_PERSONNE);

alter table STATUT_PERSONNE_BOUTIQUE
   add constraint FK_STATUT_P_STATUT_PE_STATUT foreign key (ID_STATUT)
      references STATUT (ID_STATUT);

alter table VILLE
   add constraint FK_VILLE_PAYS_VILL_PAYS foreign key (ID_PAYS)
      references PAYS (ID_PAYS);

