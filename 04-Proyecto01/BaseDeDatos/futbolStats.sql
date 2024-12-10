/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     10/12/2024 1:40:05                           */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EQUIPO') and o.name = 'FK_EQUIPO_REFERENCE_TORNEO')
alter table EQUIPO
   drop constraint FK_EQUIPO_REFERENCE_TORNEO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ESTADISTICA_PARTIDO') and o.name = 'FK_ESTADIST_RELATIONS_PARTIDO')
alter table ESTADISTICA_PARTIDO
   drop constraint FK_ESTADIST_RELATIONS_PARTIDO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ESTADISTICA_PARTIDO') and o.name = 'FK_ESTADIST_RELATIONS_JUGADOR')
alter table ESTADISTICA_PARTIDO
   drop constraint FK_ESTADIST_RELATIONS_JUGADOR
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('JUGADOR') and o.name = 'FK_JUGADOR_RELATIONS_EQUIPO')
alter table JUGADOR
   drop constraint FK_JUGADOR_RELATIONS_EQUIPO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PARTIDO') and o.name = 'FK_PARTIDO_REFERENCE_EQUIPO')
alter table PARTIDO
   drop constraint FK_PARTIDO_REFERENCE_EQUIPO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PARTIDO') and o.name = 'FK_PARTIDO_RELATIONS_TORNEO')
alter table PARTIDO
   drop constraint FK_PARTIDO_RELATIONS_TORNEO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TABLA_POSICIONES') and o.name = 'FK_TABLA_PO_REFERENCE_EQUIPO')
alter table TABLA_POSICIONES
   drop constraint FK_TABLA_PO_REFERENCE_EQUIPO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TABLA_POSICIONES') and o.name = 'FK_TABLA_PO_REFERENCE_TORNEO')
alter table TABLA_POSICIONES
   drop constraint FK_TABLA_PO_REFERENCE_TORNEO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('EQUIPO')
            and   type = 'U')
   drop table EQUIPO
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ESTADISTICA_PARTIDO')
            and   name  = 'RELATIONSHIP_6_FK'
            and   indid > 0
            and   indid < 255)
   drop index ESTADISTICA_PARTIDO.RELATIONSHIP_6_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ESTADISTICA_PARTIDO')
            and   name  = 'RELATIONSHIP_5_FK'
            and   indid > 0
            and   indid < 255)
   drop index ESTADISTICA_PARTIDO.RELATIONSHIP_5_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ESTADISTICA_PARTIDO')
            and   type = 'U')
   drop table ESTADISTICA_PARTIDO
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('JUGADOR')
            and   name  = 'RELATIONSHIP_2_FK'
            and   indid > 0
            and   indid < 255)
   drop index JUGADOR.RELATIONSHIP_2_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('JUGADOR')
            and   type = 'U')
   drop table JUGADOR
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PARTIDO')
            and   name  = 'RELATIONSHIP_3_FK'
            and   indid > 0
            and   indid < 255)
   drop index PARTIDO.RELATIONSHIP_3_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PARTIDO')
            and   type = 'U')
   drop table PARTIDO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TABLA_POSICIONES')
            and   type = 'U')
   drop table TABLA_POSICIONES
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TORNEO')
            and   type = 'U')
   drop table TORNEO
go

/*==============================================================*/
/* Table: EQUIPO                                                */
/*==============================================================*/
create table EQUIPO (
   ID_EQUIPO            int                  not null,
   ID_TORNEO            int                  null,
   NOM_EQUIPO           varchar(50)          not null,
   LOGO                 image                null,
   CIUDAD               varchar(50)          null,
   FUNDACION            datetime             null,
   constraint PK_EQUIPO primary key nonclustered (ID_EQUIPO)
)
go

/*==============================================================*/
/* Table: ESTADISTICA_PARTIDO                                   */
/*==============================================================*/
create table ESTADISTICA_PARTIDO (
   ID_ESTADISTICA       int                  not null,
   ID_PARTIDO           int                  not null,
   ID_JUGADOR           int                  not null,
   GOLES                int                  not null,
   T_AMARILLAS          int                  null,
   T_ROJAS              int                  null,
   constraint PK_ESTADISTICA_PARTIDO primary key nonclustered (ID_ESTADISTICA)
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_5_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_5_FK on ESTADISTICA_PARTIDO (
ID_PARTIDO ASC
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_6_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_6_FK on ESTADISTICA_PARTIDO (
ID_JUGADOR ASC
)
go

/*==============================================================*/
/* Table: JUGADOR                                               */
/*==============================================================*/
create table JUGADOR (
   ID_JUGADOR           int                  not null,
   ID_EQUIPO            int                  not null,
   NOM_JUGADOR          varchar(100)         not null,
   POSICION             varchar(50)          not null,
   DORSAL               int                  null,
   FECHA_NAC            datetime             null,
   FOTO                 image                null,
   constraint PK_JUGADOR primary key nonclustered (ID_JUGADOR)
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_2_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_2_FK on JUGADOR (
ID_EQUIPO ASC
)
go

/*==============================================================*/
/* Table: PARTIDO                                               */
/*==============================================================*/
create table PARTIDO (
   ID_PARTIDO           int                  not null,
   ID_TORNEO            int                  not null,
   ID_EQUIPO            int                  null,
   FECHA                datetime             not null,
   HORA                 datetime             not null,
   ESTADO               varchar(16)          not null,
   GOLES_LOCAL          int                  not null,
   GOLES_VISITA         int                  not null,
   constraint PK_PARTIDO primary key nonclustered (ID_PARTIDO)
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_3_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_3_FK on PARTIDO (
ID_TORNEO ASC
)
go

/*==============================================================*/
/* Table: TABLA_POSICIONES                                      */
/*==============================================================*/
create table TABLA_POSICIONES (
   ID_TABLA             int                  not null,
   ID_EQUIPO            int                  null,
   ID_TORNEO            int                  null,
   PUNTOS               int                  not null,
   P_JUGADOS            int                  not null,
   VICTORIAS            int                  not null,
   EMPATES              int                  not null,
   DERROTAS             int                  not null,
   GOLES_FAVOR          int                  not null,
   GOLES_CONTRA         int                  not null,
   DIFERENCIA_GOL       int                  not null,
   constraint PK_TABLA_POSICIONES primary key nonclustered (ID_TABLA)
)
go

/*==============================================================*/
/* Table: TORNEO                                                */
/*==============================================================*/
create table TORNEO (
   ID_TORNEO            int                  not null,
   NOM_TORNEO           varchar(50)          not null,
   TIPO                 varchar(20)          not null,
   FECHA_INICIO         datetime             null,
   FECHA_FIN            datetime             null,
   constraint PK_TORNEO primary key nonclustered (ID_TORNEO)
)
go

alter table EQUIPO
   add constraint FK_EQUIPO_REFERENCE_TORNEO foreign key (ID_TORNEO)
      references TORNEO (ID_TORNEO)
go

alter table ESTADISTICA_PARTIDO
   add constraint FK_ESTADIST_RELATIONS_PARTIDO foreign key (ID_PARTIDO)
      references PARTIDO (ID_PARTIDO)
go

alter table ESTADISTICA_PARTIDO
   add constraint FK_ESTADIST_RELATIONS_JUGADOR foreign key (ID_JUGADOR)
      references JUGADOR (ID_JUGADOR)
go

alter table JUGADOR
   add constraint FK_JUGADOR_RELATIONS_EQUIPO foreign key (ID_EQUIPO)
      references EQUIPO (ID_EQUIPO)
go

alter table PARTIDO
   add constraint FK_PARTIDO_REFERENCE_EQUIPO foreign key (ID_EQUIPO)
      references EQUIPO (ID_EQUIPO)
go

alter table PARTIDO
   add constraint FK_PARTIDO_RELATIONS_TORNEO foreign key (ID_TORNEO)
      references TORNEO (ID_TORNEO)
go

alter table TABLA_POSICIONES
   add constraint FK_TABLA_PO_REFERENCE_EQUIPO foreign key (ID_EQUIPO)
      references EQUIPO (ID_EQUIPO)
go

alter table TABLA_POSICIONES
   add constraint FK_TABLA_PO_REFERENCE_TORNEO foreign key (ID_TORNEO)
      references TORNEO (ID_TORNEO)
go

