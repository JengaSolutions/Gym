/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     23/08/2014 12:32:19                          */
/*==============================================================*/


drop table CALCULOS;

drop table MES;

drop table REGISTRO;

drop table TIPOUSR;

drop table USUARIO;

/*==============================================================*/
/* Table: CALCULOS                                              */
/*==============================================================*/
create table CALCULOS (
   ID_CALCULO           INT8                 not null,
   ID_REG               INT8                 null,
   ICM                  NUMERIC              null,
   TMB                  NUMERIC              null,
   GET                  NUMERIC              null,
   DPM                  NUMERIC              null,
   constraint PK_CALCULOS primary key (ID_CALCULO)
);

/*==============================================================*/
/* Table: MES                                                   */
/*==============================================================*/
create table MES (
   ID_MES               INT8                 not null,
   ID_REG               INT8                 null,
   FECHA_I              DATE                 null,
   FECHA_F              DATE                 null,
   constraint PK_MES primary key (ID_MES)
);

/*==============================================================*/
/* Table: REGISTRO                                              */
/*==============================================================*/
create table REGISTRO (
   ID_REG               INT8                 not null,
   NOMBRES              TEXT                 null,
   APELLIDOS            TEXT                 null,
   CORREO               TEXT                 null,
   TELEFONO             TEXT                 null,
   EDAD                 INT2                 null,
   ALTURA               NUMERIC              null,
   PESO                 NUMERIC              null,
   SEXO                 TEXT                 null,
   constraint PK_REGISTRO primary key (ID_REG)
);

/*==============================================================*/
/* Table: TIPOUSR                                               */
/*==============================================================*/
create table TIPOUSR (
   ID_TIPOUSR           INT8                 not null,
   TIPO                 TEXT                 null,
   DESCRIPCION          TEXT                 null,
   constraint PK_TIPOUSR primary key (ID_TIPOUSR)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   ID_USR               INT8                 not null,
   ID_TIPOUSR           INT8                 null,
   NOMBRES              TEXT                 null,
   NICK                 TEXT                 null,
   PASS                 TEXT                 null,
   constraint PK_USUARIO primary key (ID_USR)
);

alter table CALCULOS
   add constraint FK_CALCULOS_REFERENCE_REGISTRO foreign key (ID_REG)
      references REGISTRO (ID_REG)
      on delete restrict on update restrict;

alter table MES
   add constraint FK_MES_REFERENCE_REGISTRO foreign key (ID_REG)
      references REGISTRO (ID_REG)
      on delete restrict on update restrict;

alter table USUARIO
   add constraint FK_USUARIO_REFERENCE_TIPOUSR foreign key (ID_TIPOUSR)
      references TIPOUSR (ID_TIPOUSR)
      on delete restrict on update restrict;

/*==============================================================*/
/* SECUENCIAS                                        		     */
/*==============================================================*/
CREATE SEQUENCE sec_calculos
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE sec_calculos
  OWNER TO postgres;
  
  CREATE SEQUENCE sec_mes
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE sec_mes
  OWNER TO postgres;
  
  CREATE SEQUENCE sec_registro
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE sec_registro
  OWNER TO postgres;
  
  CREATE SEQUENCE sec_tipousr
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE sec_tipousr
  OWNER TO postgres;
  
  CREATE SEQUENCE sec_usr
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE sec_usr
  OWNER TO postgres;
  
  ALTER TABLE tipousr
   ALTER COLUMN id_tipousr SET DEFAULT nextval('sec_tipousr');
   
   ALTER TABLE usuario
   ALTER COLUMN id_usr SET DEFAULT nextval('sec_usr');
ALTER TABLE usuario
  DROP CONSTRAINT fk_usuario_reference_tipousr;
ALTER TABLE usuario
  ADD CONSTRAINT fk_usuario_reference_tipousr FOREIGN KEY (id_tipousr)
      REFERENCES tipousr (id_tipousr) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

	  ALTER TABLE mes
   ALTER COLUMN id_mes SET DEFAULT nextval('sec_mes');
ALTER TABLE mes
  DROP CONSTRAINT fk_mes_reference_registro;
ALTER TABLE mes
  ADD CONSTRAINT fk_mes_reference_registro FOREIGN KEY (id_reg)
      REFERENCES registro (id_reg) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE calculos
   ALTER COLUMN id_calculo SET DEFAULT nextval('sec_calculos');
ALTER TABLE calculos
  DROP CONSTRAINT fk_calculos_reference_registro;
ALTER TABLE calculos
  ADD CONSTRAINT fk_calculos_reference_registro FOREIGN KEY (id_reg)
      REFERENCES registro (id_reg) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;
	  
ALTER TABLE registro
   ALTER COLUMN id_reg SET DEFAULT nextval('sec_registro');