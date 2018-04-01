/*==============================================================*/
/* Nom de SGBD :  ORACLE Version 11g                            */
/* Date de création :  20/03/2018 10:33:07                      */
/*==============================================================*/


alter table EMPLACEMENT
   drop constraint FK_EMPLACEM_TAUX_EXPO_TAUX_EXP;

alter table HISTORIQUE_BOUTIQUE
   drop constraint FK_HISTORIQ_BOUTIQUE__BOUTIQUE;

alter table HISTORIQUE_BOUTIQUE
   drop constraint FK_HISTORIQ_EMP_HISTO_EMPLACEM;

alter table HISTORIQUE_BOUTIQUE
   drop constraint FK_HISTORIQ_PRIX_EMPL_PRIX_M2;

alter table PRIX_M2
   drop constraint FK_PRIX_M2_PRIX_EMPL_HISTORIQ;

alter table STATUT_PERSONNE_BOUTIQUE
   drop constraint FK_STATUT_P_STATUT_PE_PERSONNE;

alter table STATUT_PERSONNE_BOUTIQUE
   drop constraint FK_STATUT_P_STATUT_PE_STATUT;

alter table STATUT_PERSONNE_BOUTIQUE
   drop constraint FK_STATUT_P_STATUT_PE_BOUTIQUE;

drop table BOUTIQUE cascade constraints;

drop index TAUX_EXPO_EMPLACEMENT_FK;

drop table EMPLACEMENT cascade constraints;

drop index PRIX_EMPLACEMENT_M3_FK;

drop index EMP_HISTORIQUEEMP_FK;

drop index BOUTIQUE_HISTORIQUE_FK;

drop table HISTORIQUE_BOUTIQUE cascade constraints;

drop table PERSONNE cascade constraints;

drop index PRIX_EMPLACEMENT_M2_FK;

drop table PRIX_M2 cascade constraints;

drop table STATUT cascade constraints;

drop index STATUT_PERSONNE_BOUTIQUE3_FK;

drop index STATUT_PERSONNE_BOUTIQUE2_FK;

drop index STATUT_PERSONNE_BOUTIQUE_FK;

drop table STATUT_PERSONNE_BOUTIQUE cascade constraints;

drop table TAUX_EXPOSITION cascade constraints;

/*==============================================================*/
/* Table : BOUTIQUE                                             */
/*==============================================================*/
create table BOUTIQUE 
(
   ID_BOUTIQUE          INTEGER              not null,
   NOM_BOUTIQUE         CHAR(20)             not null,
   TELEPHONE_BOUTIQUE   NUMBER(10)           not null,
   constraint PK_BOUTIQUE primary key (ID_BOUTIQUE)
);

/*==============================================================*/
/* Table : EMPLACEMENT                                          */
/*==============================================================*/
create table EMPLACEMENT 
(
   ID_EMPLACEMENT       INTEGER              not null,
   ID_TAUX_EXPO         INTEGER              not null,
   NUM_EMPLACEMENT      NUMBER(5)            not null,
   DISPONIBILITE        SMALLINT             not null,
   SUPERFICIE           FLOAT                not null,
   constraint PK_EMPLACEMENT primary key (ID_EMPLACEMENT)
);

/*==============================================================*/
/* Index : TAUX_EXPO_EMPLACEMENT_FK                             */
/*==============================================================*/
create index TAUX_EXPO_EMPLACEMENT_FK on EMPLACEMENT (
   ID_TAUX_EXPO ASC
);

/*==============================================================*/
/* Table : HISTORIQUE_BOUTIQUE                                  */
/*==============================================================*/
create table HISTORIQUE_BOUTIQUE 
(
   ID_HISTORIQUE        INTEGER              not null,
   ID_PRIX              INTEGER              not null,
   ID_EMPLACEMENT       INTEGER              not null,
   ID_BOUTIQUE          INTEGER              not null,
   DATE_DEBUT_CONTRAT   DATE                 not null,
   DATE_FIN             DATE,
   REDEVANCE_HISTORIQUE FLOAT                not null,
   constraint PK_HISTORIQUE_BOUTIQUE primary key (ID_HISTORIQUE)
);

/*==============================================================*/
/* Index : BOUTIQUE_HISTORIQUE_FK                               */
/*==============================================================*/
create index BOUTIQUE_HISTORIQUE_FK on HISTORIQUE_BOUTIQUE (
   ID_BOUTIQUE ASC
);

/*==============================================================*/
/* Index : EMP_HISTORIQUEEMP_FK                                 */
/*==============================================================*/
create index EMP_HISTORIQUEEMP_FK on HISTORIQUE_BOUTIQUE (
   ID_EMPLACEMENT ASC
);

/*==============================================================*/
/* Index : PRIX_EMPLACEMENT_M3_FK                               */
/*==============================================================*/
create index PRIX_EMPLACEMENT_M3_FK on HISTORIQUE_BOUTIQUE (
   ID_PRIX ASC
);

/*==============================================================*/
/* Table : PERSONNE                                             */
/*==============================================================*/
create table PERSONNE 
(
   ID_PERSONNE          INTEGER              not null,
   NOM_PERSONNE         CHAR(60)             not null,
   SEXE_PERSONNE        CHAR(1)              not null,
   DATE_NAISSANCE_PERSONNE DATE                 not null,
   TELEPHONE_PERSONNE   NUMBER(10),
   ADRESSE_PERSONNE     CHAR(100)            not null,
   constraint PK_PERSONNE primary key (ID_PERSONNE)
);

/*==============================================================*/
/* Table : PRIX_M2                                              */
/*==============================================================*/
create table PRIX_M2 
(
   ID_PRIX              INTEGER              not null,
   ID_HISTORIQUE        INTEGER,
   PRIX_M               FLOAT                not null,
   DATE_DEBUT_PRIX      DATE                 not null,
   constraint PK_PRIX_M2 primary key (ID_PRIX)
);

/*==============================================================*/
/* Index : PRIX_EMPLACEMENT_M2_FK                               */
/*==============================================================*/
create index PRIX_EMPLACEMENT_M2_FK on PRIX_M2 (
   ID_HISTORIQUE ASC
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
/* Index : STATUT_PERSONNE_BOUTIQUE_FK                          */
/*==============================================================*/
create index STATUT_PERSONNE_BOUTIQUE_FK on STATUT_PERSONNE_BOUTIQUE (
   ID_PERSONNE ASC
);

/*==============================================================*/
/* Index : STATUT_PERSONNE_BOUTIQUE2_FK                         */
/*==============================================================*/
create index STATUT_PERSONNE_BOUTIQUE2_FK on STATUT_PERSONNE_BOUTIQUE (
   ID_STATUT ASC
);

/*==============================================================*/
/* Index : STATUT_PERSONNE_BOUTIQUE3_FK                         */
/*==============================================================*/
create index STATUT_PERSONNE_BOUTIQUE3_FK on STATUT_PERSONNE_BOUTIQUE (
   ID_BOUTIQUE ASC
);

/*==============================================================*/
/* Table : TAUX_EXPOSITION                                      */
/*==============================================================*/
create table TAUX_EXPOSITION 
(
   ID_TAUX_EXPO         INTEGER              not null,
   TAUX_EXPO            FLOAT                not null,
   constraint PK_TAUX_EXPOSITION primary key (ID_TAUX_EXPO)
);

alter table EMPLACEMENT
   add constraint FK_EMPLACEM_TAUX_EXPO_TAUX_EXP foreign key (ID_TAUX_EXPO)
      references TAUX_EXPOSITION (ID_TAUX_EXPO);

alter table HISTORIQUE_BOUTIQUE
   add constraint FK_HISTORIQ_BOUTIQUE__BOUTIQUE foreign key (ID_BOUTIQUE)
      references BOUTIQUE (ID_BOUTIQUE);

alter table HISTORIQUE_BOUTIQUE
   add constraint FK_HISTORIQ_EMP_HISTO_EMPLACEM foreign key (ID_EMPLACEMENT)
      references EMPLACEMENT (ID_EMPLACEMENT);

alter table HISTORIQUE_BOUTIQUE
   add constraint FK_HISTORIQ_PRIX_EMPL_PRIX_M2 foreign key (ID_PRIX)
      references PRIX_M2 (ID_PRIX);

alter table PRIX_M2
   add constraint FK_PRIX_M2_PRIX_EMPL_HISTORIQ foreign key (ID_HISTORIQUE)
      references HISTORIQUE_BOUTIQUE (ID_HISTORIQUE);

alter table STATUT_PERSONNE_BOUTIQUE
   add constraint FK_STATUT_P_STATUT_PE_PERSONNE foreign key (ID_PERSONNE)
      references PERSONNE (ID_PERSONNE);

alter table STATUT_PERSONNE_BOUTIQUE
   add constraint FK_STATUT_P_STATUT_PE_STATUT foreign key (ID_STATUT)
      references STATUT (ID_STATUT);

alter table STATUT_PERSONNE_BOUTIQUE
   add constraint FK_STATUT_P_STATUT_PE_BOUTIQUE foreign key (ID_BOUTIQUE)
      references BOUTIQUE (ID_BOUTIQUE);

