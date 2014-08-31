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
insert into modulo values(7,'RESPALDO');
insert into modulo values(8,'CONTABILIDAD');

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
insert into permiso values(1,3,2);
insert into permiso values(1,3,3);

insert into permiso values(1,4,1);

insert into permiso values(1,5,5);

insert into permiso values(1,6,1);

insert into permiso values(1,7,1);
insert into permiso values(1,7,4);

insert into permiso values(1,8,1);


insert into permiso values(2,5,5);
insert into permiso values(2,3,1);
insert into permiso values(2,1,1);
insert into permiso values(2,1,2);
insert into permiso values(2,1,3);
insert into permiso values(2,1,4);
insert into permiso values(2,1,5);
insert into permiso values(2,7,1);

insert into permiso values(3,5,5);
insert into permiso values(3,1,1);
insert into permiso values(3,1,3);
insert into permiso values(3,1,4);
insert into permiso values(3,1,5);
insert into permiso values(3,7,1);

insert into permiso values(4,6,1);
insert into permiso values(4,7,1);




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
	auu_tarea varchar(50),
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
	CONSTRAINT FK_Auditoria_Articulo_id_modulo foreign key (aud_id_modulo)references modulo(mod_id_modulo)
);

CREATE INDEX IX_id_aud ON auditoria_articulo (aud_id_auditoria);
CREATE INDEX IX_id_usuario ON auditoria_articulo (aud_id_usuario);
CREATE INDEX IX_aud_terminal ON auditoria_articulo (aud_terminal);

create table auditoria_perfiles(
aup_id int not null,
aup_usuario varchar(50),
aup_perfil_id int,
aup_perfil_desc varchar(50),
ati_tarea varchar(50),
ati_fecha datetime,
ati_terminal varchar(50),
primary key (aup_id),
foreign key (aup_usuario) references usuario(usr_nombre_usuario)
);

create table auditoria_tasa_iva(
ati_id int not null,
ati_usuario varchar(50),
ati_tasa_clave int,
ati_tasa_desc varchar(50),
ati_tasa_sigla varchar(5),
ati_tarea varchar(50),
ati_fecha datetime,
ati_terminal varchar(50),
primary key (ati_id),
foreign key (ati_usuario) references usuario(usr_nombre_usuario)
);

create table auditoria_usuarios(
auu_id int not null,
auu_usuario_admin varchar(50),
auu_usuario_nuevo varchar(50),
auu_usuario_nombre varchar(50),
auu_usuario_apellido varchar(50),
auu_usuario_perfil int,
auu_usuario_existe bit,
auu_tarea varchar(50),
auu_fecha datetime,
auu_terminal varchar(50),
primary key (auu_id),
foreign key (auu_usuario_admin) references usuario(usr_nombre_usuario)
);






create table plan_cuentas(
pc_codigo_plan_cuenta varchar(30) not null,
pc_nro_cuenta int not null,
pc_nombre_cuenta varchar(30),
pc_imputable bit,
pc_id_padre int not null,
primary key (pc_nro_cuenta)
);

CREATE INDEX IX_nro_cuenta ON plan_cuentas (pc_nro_cuenta);
CREATE INDEX IX_codigo_plan_cuenta ON plan_cuentas (pc_codigo_plan_cuenta);

insert into plan_cuentas values('0',0,'PLAN DE CUENTAS',0,-1);
insert into plan_cuentas values('1',1,'ACTIVO',0,0);
insert into plan_cuentas values('1.1',2,'ACTIVO CORRIENTE',0,1);
insert into plan_cuentas values('1.1.01',3,'DISPONIBILIDADES',0,2);
insert into plan_cuentas values('1.1.01.01',4,'CAJA',1,3);
insert into plan_cuentas values('1.1.01.02',5,'BANCOS',0,3);
insert into plan_cuentas values('1.1.01.02.01',6,'Banco provincia c.c.',1,5);
insert into plan_cuentas values('1.1.01.02.02',7,'Banco nacion c. de ahorro',1,5);
insert into plan_cuentas values('1.1.01.02.03',92,'Banco nacion cuenta corriente',1,5);
insert into plan_cuentas values('1.1.01.03',66,'Valores a depositar',1,3);
insert into plan_cuentas values('1.1.02',8,'CUENTAS POR COBRAR',0,2);
insert into plan_cuentas values('1.1.02.01',9,'Deudores en c.c.',1,8);
insert into plan_cuentas values('1.1.02.02',10,'Documentos a cobrar',1,8);
insert into plan_cuentas values('1.1.02.03',11,'Iva credito fiscal',1,8);
insert into plan_cuentas values('1.1.02.04',85,'AFIP-Iva a favor',1,8);
insert into plan_cuentas values('1.1.02.05',88,'CLIENTES-CUENTAS CORRIENTES',1,8);
insert into plan_cuentas values('1.1.02.06',93,'SOCIOS - CUENTA SOCIAL',1,8);
insert into plan_cuentas values('1.1.03',12,'BIENES DE CAMBIO',0,2);
insert into plan_cuentas values('1.1.03.01',13,'Mercaderias',1,12);
insert into plan_cuentas values('1.1.03.01',78,'PA - Chupetines',1,12);
insert into plan_cuentas values('1.1.03.01',79,'PB - Garrapiñadas',1,12);
insert into plan_cuentas values('1.1.03.01',80,'PC - Alfajores',1,12);
insert into plan_cuentas values('1.1.03.01',81,'PD - Galletitas',1,12);
insert into plan_cuentas values('1.1.04',14,'INVERSIONES',0,2);
insert into plan_cuentas values('1.1.05',62,'OTROS CREDITOS',0,2);
insert into plan_cuentas values('1.1.05.01',63,'Socio Gomez Emanuel',1,62);
insert into plan_cuentas values('1.1.05.02',64,'Socio Banegas Rodrigo',1,62);
insert into plan_cuentas values('1.2',15,'ACTIVO NO CORRIENTE',0,1);
insert into plan_cuentas values('1.2.01',16,'BIENES DE USO',0,15);
insert into plan_cuentas values('1.2.01.01',17,'Rodados',1,16);
insert into plan_cuentas values('1.2.01.02',18,'Muebles y utiles',1,16);
insert into plan_cuentas values('2',19,'PASIVO',0,0);
insert into plan_cuentas values('2.1',20,'PASIVO CORRIENTE',0,19);
insert into plan_cuentas values('2.1.01',21,'DEUDAS',0,20);
insert into plan_cuentas values('2.1.01.01',22,'Deudas comerciales',0,21);
insert into plan_cuentas values('2.1.01.01.01',23,'Proveedores',1,22);
insert into plan_cuentas values('2.1.01.01.02',24,'Obligaciones a pagar',1,22);
insert into plan_cuentas values('2.1.01.02',25,'Deudas fiscales',0,21);
insert into plan_cuentas values('2.1.01.02.01',26,'Iva debito fiscal',1,25);
insert into plan_cuentas values('2.1.01.02.02',27,'Iva perc. no insc.',1,25);
insert into plan_cuentas values('2.1.01.02.03',28,'Ingresos brutos a pagar',1,25);
insert into plan_cuentas values('2.1.01.02.04',83,'Tasa Insp Segur e Hig a pagar',1,25);
insert into plan_cuentas values('2.1.01.02.05',84,'AFIP-IVA a pagar',1,25);
insert into plan_cuentas values('2.1.01.03',29,'DEUDAS LABORALES Y PREV.',0,21);
insert into plan_cuentas values('2.1.01.03.01',30,'Sueldos a pagar',1,29);
insert into plan_cuentas values('2.1.01.03.02',31,'DEUDAS POR CARGAS SOCIALES',0,29);
insert into plan_cuentas values('2.1.01.03.02.01',67,'Jubilacion a pagar',1,31);
insert into plan_cuentas values('2.1.01.03.02.02',68,'I.N.S.S.J.P. a pagar',1,31);
insert into plan_cuentas values('2.1.01.03.02.03',69,'Obra social a pagar',1,31);
insert into plan_cuentas values('2.1.01.03.02.04',70,'A.N.S.S.A.L. a pagar',1,31);
insert into plan_cuentas values('2.1.01.03.02.05',71,'Ex-cajas subs. fam. a pagar',1,31);
insert into plan_cuentas values('2.1.01.03.02.06',72,'F.N.E. a pagar',1,31);
insert into plan_cuentas values('2.1.01.03.02.07',73,'A.E.C. a pagar',1,31);
insert into plan_cuentas values('2.1.01.03.02.08',74,'Cuota sindical a pagar',1,31);
insert into plan_cuentas values('2.1.01.03.02.09',75,'F.A.E.C.Y.S. a pagar',1,31);
insert into plan_cuentas values('2.1.01.03.02.10',76,'La estrella a pagar',1,31);
insert into plan_cuentas values('2.1.01.03.03',77,'Cargas sociales a pagar',1,29);
insert into plan_cuentas values('2.1.01.04',32,'Deudas bancarias',0,21);
insert into plan_cuentas values('2.2',33,'PASIVO NO CORRIENTE',0,19);
insert into plan_cuentas values('3',34,'PATRIMONIO NETO',0,0);
insert into plan_cuentas values('3.1',35,'CAPITAL',0,34);
insert into plan_cuentas values('3.1.01',36,'Capital Social',1,35);
insert into plan_cuentas values('3.2',37,'RESERVAS',0,34);
insert into plan_cuentas values('3.3',38,'RESULTADOS ACUMULADOS',0,34);
insert into plan_cuentas values('3.3.01',39,'Resultados del ej. anterior',1,38);
insert into plan_cuentas values('4',40,'RESULTADO POSITIVO',0,0);
insert into plan_cuentas values('4.1',41,'INGRESOS ORDINARIOS',0,40);
insert into plan_cuentas values('4.1.01',42,'Ventas',1,41);
insert into plan_cuentas values('4.1.02',43,'Ingresos obtenidos',1,41);
insert into plan_cuentas values('4.1.03',44,'Descuentos obtenidos',1,41);
insert into plan_cuentas values('4.1.04',89,'Ingresos por serv. prestados',1,41);
insert into plan_cuentas values('4.1.05',90,'Ingresos por fletes',1,41);
insert into plan_cuentas values('4.2',45,'INGRESOS EXTRAORDINARIOS',0,40);
insert into plan_cuentas values('5',46,'RESULTADO NEGATIVO',0,0);
insert into plan_cuentas values('5.1',47,'GASTOS DE COMERCIALIZACION',0,46);
insert into plan_cuentas values('5.1.01',48,'Costo de venta',1,47);
insert into plan_cuentas values('5.2',49,'GASTOS ADMINISTRATIVOS',0,46);
insert into plan_cuentas values('5.2.01',50,'Impuestos nacionales',1,49);
insert into plan_cuentas values('5.2.02',51,'Agua, luz y gas',1,49);
insert into plan_cuentas values('5.2.03',52,'Telefono',1,49);
insert into plan_cuentas values('5.2.04',65,'Alquileres cedidos',1,49);
insert into plan_cuentas values('5.2.05',86,'Ingresos Brutos',1,49);
insert into plan_cuentas values('5.2.06',87,'Tasa por Insp. Seg. e Hig',1,49);
insert into plan_cuentas values('5.3',53,'GASTOS EN PERSONAL',0,46);
insert into plan_cuentas values('5.3.01',54,'Sueldos y jornales',1,53);
insert into plan_cuentas values('5.3.02',55,'Cargas sociales',1,53);
insert into plan_cuentas values('5.4',56,'GASTOS FINANCIEROS',0,46);
insert into plan_cuentas values('5.4.01',57,'Intereses cedidos',1,56);
insert into plan_cuentas values('5.4.02',58,'Descuentos cedidos',1,56);
insert into plan_cuentas values('5.4.03',59,'Gastos bancarios',1,56);
insert into plan_cuentas values('5.5',60,'OTROS GASTOS',0,46);
insert into plan_cuentas values('5.5.01',61,'Fletes pagados',1,60);
insert into plan_cuentas values('5.5.02',82,'Conservacion y mantenimiento',1,60);


create table borrador_asientos(
	ba_nro_asiento int not null,
	ba_nro_renglon int,
	ba_fecha_contabilidad date,
	ba_tipo varchar(30),
	ba_nro_cuenta int,
	ba_fecha_operacion date,
	ba_fecha_vencimiento date,
	ba_nro_comprobante varchar(30),
	ba_leyenda varchar(30),
	ba_debe float,
	ba_haber float,
	ba_ok_carga bit,
	ba_ok_registrado bit,

	primary key (ba_nro_asiento,ba_nro_renglon),
	foreign key (ba_nro_cuenta)references plan_cuentas(pc_nro_cuenta)
)

CREATE INDEX IX_bo_asiento_cta ON borrador_asientos (ba_nro_cuenta);



create table asientos(
	as_nro_asiento int not null,
	as_nro_renglon int,
	as_fecha_contabilidad date,
	as_tipo varchar(30),
	as_nro_cuenta int,
	as_fecha_operacion date,
	as_fecha_vencimiento date,
	as_nro_comprobante varchar(30),
	as_leyenda varchar(30),
	as_debe float,
	as_haber float,
	as_ok_carga bit,
	as_ok_registrado bit,

	primary key (as_nro_asiento,as_nro_renglon),
	foreign key (as_nro_cuenta)references plan_cuentas(pc_nro_cuenta)
)

CREATE INDEX IX_as_asiento_cta ON borrador_asientos (ba_nro_cuenta);


create table parametros_contables(
	pc_fecha_inicio date,
	pc_fecha_cierre date,
	pc_nro_ultimo_asiento int,
	pc_fecha_impresion_diario date,
	pc_nro_renglon_diario int,
	pc_nro_folio int,
	pc_saldo_transporte float	
)

