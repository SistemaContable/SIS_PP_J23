CREATE TABLE Tasas_IVA
( 
	tasa_clave      	varchar(2) not null,
  	tasa_desc        	varchar(20) not null,
  	tasa_sigla		varchar(5) not null,
  	
	CONSTRAINT PK_Tasa_IVA primary key (tasa_clave)
 );

insert into Tasas_IVA values ('00','Excento','EXC');
insert into Tasas_IVA values ('01','Tasa General','TGR');
insert into Tasas_IVA values ('02','Tasa Diferencial','TDF');
insert into Tasas_IVA values ('03','Tasa Reducida','TRD');

CREATE TABLE Articulos
( 
	art_codigo      	varchar(15) not null,
  	art_desc        	varchar(25) not null,
  	art_proveedor		varchar(15) not null,
  	art_precio		numeric(9,2) not null CONSTRAINT DF_Articulos_art_precio DEFAULT(0),
	art_stock		numeric(4) not null CONSTRAINT DF_Articulos_stock DEFAULT(0),
	art_cod_tasa_iva	varchar (2) not null,
  	
	CONSTRAINT PK_Articulos primary key (art_codigo),
  	CONSTRAINT FK_Articulos_cod_tasa_iva foreign key (art_cod_tasa_iva) references Tasas_IVA(tasa_clave),
	CONSTRAINT CK_Articulos_art_precio CHECK (art_precio >= 0)
 );


CREATE TABLE modulo
(
	mod_id_modulo int not null,
	mod_descripcion varchar(50),

	CONSTRAINT PK_Modulos primary key (mod_id_modulo)
);

insert into modulo values(1,'ARTICULO');
insert into modulo values(2,'TASAS IVA');
insert into modulo values(3,'USUARIO');
insert into modulo values(4,'ADMINISTRADOR IMPRESORA');
insert into modulo values(5,'AUXILIARES');
insert into modulo values(6,'AUDITORIA');

CREATE TABLE tarea
(
	tar_id_tarea int not null,
	tar_descripcion varchar(50),
	
	CONSTRAINT PK_Tareas primary key (tar_id_tarea)
);

insert into tarea values(1,'ALTA');
insert into tarea values(2,'BAJA');
insert into tarea values(3,'MODIFICACION');
insert into tarea values(4,'CONSULTA');
insert into tarea values(5,'LISTADO');

CREATE TABLE perfil
(
	prf_id_perfil int not null,
	prf_descripcion varchar(50),
	CONSTRAINT PK_Perfiles primary key (prf_id_perfil)
);

insert into perfil values(1,'ADMINISTRADOR');
insert into perfil values(2,'SUPERVISOR');
insert into perfil values(3,'OPERADOR');
insert into perfil values(4,'AUDITOR');

CREATE TABLE usuario
(
	usr_nombre_usuario varchar(50) not null,
	usr_nombre varchar(50),
	usr_apellido varchar(50),
	usr_contrasenia varchar(20) not null,
	usr_id_perfil int not null,
	usr_existe	bit,
	
	CONSTRAINT PK_Usuarios primary key (usr_nombre_usuario),
	CONSTRAINT FK_Usuarios_id_perfil foreign key (usr_id_perfil) references perfil(prf_id_perfil)
);

CREATE TABLE permiso
(
	per_id_perfil int not null,
	per_id_modulo int not null,
	per_id_tarea int not null,
	
	CONSTRAINT PK_Permisos primary key (per_id_perfil,per_id_modulo,per_id_tarea),
	CONSTRAINT FK_Permisos_id_perfil foreign key (per_id_perfil) references perfil(prf_id_perfil),
	CONSTRAINT FK_Permisos_id_modulo foreign key (per_id_modulo) references modulo(mod_id_modulo),
	CONSTRAINT FK_Permisos_id_tarea foreign key (per_id_tarea) references tarea(tar_id_tarea)
);


insert into permiso values(1,1,1);
insert into permiso values(1,1,2);
insert into permiso values(1,1,3);
insert into permiso values(1,1,4);
insert into permiso values(1,1,5);

insert into permiso values(1,2,1);
insert into permiso values(1,2,2);
insert into permiso values(1,2,3);
insert into permiso values(1,2,4);
insert into permiso values(1,2,5);

insert into permiso values(1,3,1);
insert into permiso values(1,4,1);
insert into permiso values(1,5,5);
insert into permiso values(1,6,1);

insert into permiso values(2,5,5);
insert into permiso values(2,3,1);
insert into permiso values(2,1,1);
insert into permiso values(2,1,2);
insert into permiso values(2,1,3);
insert into permiso values(2,1,4);
insert into permiso values(2,1,5);

insert into permiso values(3,5,5);
insert into permiso values(3,1,1);
insert into permiso values(3,1,3);
insert into permiso values(3,1,4);
insert into permiso values(3,1,5);

insert into permiso values(4,6,1);

CREATE TABLE impresoras
(
	imp_nombre varchar(35) not null,
	imp_id_modulo int not null,
	
	CONSTRAINT PK_Impresoras primary key (imp_nombre,imp_id_modulo),
	CONSTRAINT PK_Impresoras_id_modulo foreign key (imp_id_modulo) references modulo(mod_id_modulo)
);

CREATE TABLE auditoria_articulo
(
	aud_id_auditoria int not null,
	aud_id_usuario varchar(50) not null,
	aud_id_modulo int not null,
	aud_id_tarea int not null,
	aud_fecha datetime not null,
	aud_terminal varchar(50) not null,
	aud_art_codigo varchar(15),
	aud_art_desc varchar(25),
	aud_art_proveedor varchar(15),
	aud_art_precio numeric(9,2),
	aud_art_stock numeric(4),
	aud_art_cod_tasa_iva varchar(2) not null,

	CONSTRAINT PK_Auditoria_Articulo primary key (aud_id_auditoria),
	CONSTRAINT FK_Auditoria_Articulo_id_usuario foreign key (aud_id_usuario)references usuario(usr_nombre_usuario),
	CONSTRAINT FK_Auditoria_Articulo_id_modulo foreign key (aud_id_modulo)references modulo(mod_id_modulo),
	CONSTRAINT FK_Auditoria_Articulo_id_tarea foreign key (aud_id_tarea)references tarea(tar_id_tarea)
);

CREATE INDEX IX_id_aud ON auditoria_articulo (aud_id_auditoria);
CREATE INDEX IX_id_usuario ON auditoria_articulo (aud_id_usuario);
CREATE INDEX IX_aud_terminal ON auditoria_articulo (aud_terminal);