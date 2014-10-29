CREATE TABLE tipo_tasas_iva
( 
	tasa_clave      	varchar(2) not null,
  	tasa_desc        	varchar(20) not null,
  	tasa_sigla		varchar(5) not null,
  	
	CONSTRAINT PK_Tasa_IVA primary key (tasa_clave)
 );

		CREATE INDEX  IX_Descripcion ON tipo_tasas_iva (tasa_desc);

		insert into tipo_tasas_iva values ('00','Excento','EXC');
		insert into tipo_tasas_iva values ('01','Tasa General','TGR');
		insert into tipo_tasas_iva values ('02','Tasa Diferencial','TDF');
		insert into tipo_tasas_iva values ('03','Tasa Reducida','TRD');


CREATE TABLE tasas_iva
(
	tasa_id int IDENTITY(1,1),
	tasa_tipo varchar(2) not NULL,
 	tasa_desde date not NULL,
 	tasa_hasta date not NULL,
 	tasa_tasa float,
	tasa_sobretasa float

	PRIMARY KEY (tasa_id),
	FOREIGN KEY (tasa_tipo) REFERENCES tipo_tasas_iva (tasa_clave)
)
		 CREATE INDEX  IX_Tasa_ID ON tasas_iva (tasa_id);
		 CREATE INDEX  IX_Tipo ON tasas_iva (tasa_tipo);
		 CREATE INDEX  IX_Desde ON tasas_iva (tasa_desde);
		 CREATE INDEX  IX_Tasa ON tasas_iva (tasa_tasa);
		 
		 insert into tasas_iva values ('00','01-01-1900','31-12-2500',0,0);
		 insert into tasas_iva values ('01','01-01-1992','31-12-1995',18,9);
		 insert into tasas_iva values ('01','01-01-1996','31-12-2000',15,7.5);
		 insert into tasas_iva values ('01','01-01-2001','31-12-2500',21,10.5);
		 insert into tasas_iva values ('02','01-01-2007','31-12-2500',27,13.5);
		 insert into tasas_iva values ('03','01-01-2007','31-12-2500',10.5,null);
		 

CREATE TABLE Articulos
( 
	art_codigo      	varchar(15) not null,
  	art_desc        	varchar(25) not null,
  	art_proveedor		varchar(15) not null,
  	art_precio		numeric(9,2) not null CONSTRAINT DF_Articulos_art_precio DEFAULT(0),
	art_stock		numeric(4) not null CONSTRAINT DF_Articulos_stock DEFAULT(0),
	art_cod_tasa_iva	varchar (2) not null,
  	
	CONSTRAINT PK_Articulos primary key (art_codigo),
  	CONSTRAINT FK_Articulos_cod_tasa_iva foreign key (art_cod_tasa_iva) references tipo_tasas_iva(tasa_clave),
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
		insert into modulo values(9,'FACTURACION');

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
		

CREATE TABLE auditoria_perfiles(
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

CREATE TABLE auditoria_tasa_iva(
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

CREATE TABLE auditoria_usuarios(
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


CREATE TABLE plan_cuentas(
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
		insert into plan_cuentas values('1.1.03.01',79,'PB - Garrapi�adas',1,12);
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


CREATE TABLE borrador_asientos(
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
		CREATE INDEX IX_bo_fecha_contabilidad ON borrador_asientos (ba_fecha_contabilidad);


CREATE TABLE asientos(
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

		CREATE INDEX IX_as_asiento_cta ON asientos (as_nro_cuenta);
		CREATE INDEX IX_as_fecha_contabilidad ON asientos (as_fecha_contabilidad);


CREATE TABLE parametros_contables(
	pc_fecha_inicio date,
	pc_fecha_cierre date,
	pc_nro_ultimo_asiento int,
	pc_fecha_impresion_diario date,
	pc_nro_renglon_diario int,
	pc_nro_folio int,
	pc_saldo_transporte float
)

CREATE TABLE libro_mayor(
	lm_nro_asiento int not null,
	lm_nro_renglon int,
	lm_fecha_contabilidad date,
	lm_tipo varchar(30),
	lm_nro_cuenta int,
	lm_fecha_operacion date,
	lm_fecha_vencimiento date,
	lm_nro_comprobante varchar(30),
	lm_leyenda varchar(30),
	lm_debe float,
	lm_haber float,
	lm_saldo float,

	primary key (lm_nro_asiento,lm_nro_renglon,lm_nro_cuenta),
	foreign key (lm_nro_cuenta)references plan_cuentas(pc_nro_cuenta)
)

		CREATE INDEX IX_as_asiento_cta ON libro_mayor (lm_nro_cuenta);
		CREATE INDEX IX_as_fecha_contabilidad ON libro_mayor(lm_fecha_contabilidad);


CREATE TABLE balance(
	blc_cuenta int,
	blc_codigo_plan_cuenta varchar(30),
	blc_nombre_cta varchar(30),
	blc_saldo_inicial float,
	blc_debito float,
	blc_haber float,
	blc_saldo_acumulado float,
	blc_saldo_cierre float,
	primary key (blc_cuenta),
	foreign key (blc_cuenta)references plan_cuentas(pc_nro_cuenta)
);


-- ####################  NUEVO  ############################


CREATE TABLE productos(
  	prod_codigo int not NULL,
	prod_descripcion varchar(60),
	prod_cantidad int,
	prod_costo float,
	prod_precio_neto_venta float,
	prod_tasa_iva varchar(2),
	prod_impuesto_porcentaje float,
	prod_impuesto_valor float
	
	PRIMARY KEY (prod_codigo),
	FOREIGN KEY (prod_tasa_iva) REFERENCES tipo_tasas_iva (tasa_clave)
 )
 
		CREATE INDEX  PK_Producto_Codigo ON productos (prod_codigo);
		CREATE INDEX  PK_Producto_Cantidad ON productos (prod_cantidad);
		CREATE INDEX  PK_Producto_Costo ON productos (prod_costo);
		CREATE INDEX  PK_Producto_Tasa ON productos (prod_tasa_iva);
 

CREATE TABLE tipo_comprobante(
	tc_codigo int not null,
	tc_descripcion varchar(70),
	tc_activo bit,
	primary key(tc_codigo)
);

		CREATE INDEX PK_tc_codigo ON tipo_comprobante (tc_codigo);
		CREATE INDEX IX_tc_descripcion ON tipo_comprobante (tc_descripcion);

		insert into tipo_comprobante values ('01','FACTURA A',1);
		insert into tipo_comprobante values ('02','NOTA DE DEBITO A',1);
		insert into tipo_comprobante values ('03','NOTA DE CREDITO A',0);
		insert into tipo_comprobante values ('04','RECIBOS A',1);
		insert into tipo_comprobante values ('05','NOTA DE VENTA AL CONTADO A',1);
		insert into tipo_comprobante values ('06','FACTURA B',1);
		insert into tipo_comprobante values ('07','NOTA DE DEBITO B',1);
		insert into tipo_comprobante values ('08','NOTA DE CREDITO B',0);
		insert into tipo_comprobante values ('09','RECIBO B',1);
		insert into tipo_comprobante values ('10','NOTA DE VENTA AL CONTADO B',1);
		insert into tipo_comprobante values ('11','FACTURA C',1);
		insert into tipo_comprobante values ('12','NOTA DE DEBITO C',1);
		insert into tipo_comprobante values ('13','NOTA DE CREDITO C',0);
		insert into tipo_comprobante values ('14','DOCUMENTO ADUANERO',1);
		insert into tipo_comprobante values ('15','RECIBO C',1);
		insert into tipo_comprobante values ('16','NOTA DE VENTA AL CONTADO C',1);
		insert into tipo_comprobante values ('19','FACTURA DE EXPORTACION',1);

		insert into tipo_comprobante values ('51','FACTURA M',1);
		insert into tipo_comprobante values ('52','NOTA DE DEBITO M',1);
		insert into tipo_comprobante values ('53','NOTA DE CREDITO M',0);
		insert into tipo_comprobante values ('54','RECIBO M',1);
		insert into tipo_comprobante values ('55','NOTA DE VENTA AL CONTADO M',1);

		insert into tipo_comprobante values ('80','COMPROBANTE DIARIO DE CIERRE (ZETA)',1);
		insert into tipo_comprobante values ('81','TIQUE FACTURA CONTROLADORES FISCALES',1);
		insert into tipo_comprobante values ('82','TIQUE FACTURA B',1);
		insert into tipo_comprobante values ('83','TIQUE',1);

		insert into tipo_comprobante values ('91','REMITO R',1);

CREATE TABLE punto_venta(
	pv_codigo int not null,
	pv_descripcion varchar(20),
	primary key(pv_codigo)
);
		CREATE INDEX PK_pv_codigo ON punto_venta (pv_codigo);
		CREATE INDEX PK_pv_descripcion ON punto_venta (pv_descripcion);

		insert into punto_venta values(1,'PV01');
		insert into punto_venta values(2,'PV02');
		insert into punto_venta values(3,'PV03');

CREATE TABLE localidades(
	loc_id int not null,
	loc_codigo_postal int not null,
	loc_descripcion varchar(20),
	primary key(loc_id)
);
		CREATE INDEX PK_loc_id ON localidades (loc_id);
		CREATE INDEX PK_codigo_postal ON localidades (loc_codigo_postal);
		CREATE INDEX PK_loc_descripcion ON localidades (loc_descripcion);

		insert into localidades values(1,8150,'Coronel Dorrego');
		insert into localidades values(2,8000,'Bahia Blanca');
		insert into localidades values(3,7500,'Tres Arroyos');
		insert into localidades values(4,6310,'General Campos');


CREATE TABLE situacion_frente_iva(
	sfi_id int not null,
	sfi_descripcion varchar(40),
	sfi_sigla varchar(3),
	primary key(sfi_id)
);
		CREATE INDEX PK_sfi_id ON situacion_frente_iva (sfi_id);
		CREATE INDEX PK_sfi_descripcion ON situacion_frente_iva (sfi_descripcion);

		insert into situacion_frente_iva values(1,'Responsable Inscripto','RI');
		insert into situacion_frente_iva values(2,'Responsable No Inscripto','RNI');
		insert into situacion_frente_iva values(3,'Exento','EX');
		insert into situacion_frente_iva values(4,'No Responsable','NR');
		insert into situacion_frente_iva values(5,'Consumidor Final','CF');
		insert into situacion_frente_iva values(6,'Monotributo','Mt');
		insert into situacion_frente_iva values(7,'Sujeto No Categorizado','SNC');


CREATE TABLE ptoventa_x_tipocomprobante(
	vxc_id_pto_venta int not null,
	vxc_id_tipo_comprobante int not null,
	vxc_numero int,		
	primary key(vxc_id_pto_venta,vxc_id_tipo_comprobante),
	foreign key(vxc_id_pto_venta)references punto_venta(pv_codigo),
	foreign key(vxc_id_tipo_comprobante)references tipo_comprobante(tc_codigo)
)
		CREATE INDEX  IX_vxc_pto_venta ON ptoventa_x_tipocomprobante (vxc_id_pto_venta);
		CREATE INDEX  IX_vxc_tipo_comprobante ON ptoventa_x_tipocomprobante (vxc_id_tipo_comprobante);

		insert into ptoventa_x_tipocomprobante values(1,1,0);


CREATE TABLE clientes(
	cli_codigo varchar(20) not null,
	cli_nombre varchar(20),
	cli_apellido varchar(20),
	cli_fecha_nac date,
	cli_cuit varchar(11),
	cli_localidad int,
	cli_direccion varchar(50),
	cli_calle int,
	cli_sit_frente_iva int,
	primary key(cli_codigo),
	foreign key (cli_localidad) references localidades(loc_id),
	foreign key (cli_sit_frente_iva) references situacion_frente_iva(sfi_id)
)

		CREATE INDEX  IX_Clientes_id ON clientes (cli_codigo);
		CREATE INDEX  IX_Clientes_nom ON clientes (cli_nombre);

CREATE TABLE situacion_x_tipocomprobante(
	sfi_id	int not null,
	tc_codigo	int	not null

	primary key(sfi_id,tc_codigo),
	foreign key (sfi_id) references situacion_frente_iva(sfi_id),
	foreign key (tc_codigo) references tipo_comprobante(tc_codigo)
)

		CREATE INDEX  IX_SFI ON situacion_x_tipocomprobante (sfi_id);

		INSERT INTO situacion_x_tipocomprobante VALUES (1,1);
		INSERT INTO situacion_x_tipocomprobante VALUES (1,2);
		INSERT INTO situacion_x_tipocomprobante VALUES (1,3);
		INSERT INTO situacion_x_tipocomprobante VALUES (1,4);
		INSERT INTO situacion_x_tipocomprobante VALUES (1,5);

		INSERT INTO situacion_x_tipocomprobante VALUES (2,1);
		INSERT INTO situacion_x_tipocomprobante VALUES (2,2);
		INSERT INTO situacion_x_tipocomprobante VALUES (2,3);
		INSERT INTO situacion_x_tipocomprobante VALUES (2,4);
		INSERT INTO situacion_x_tipocomprobante VALUES (2,5);

		INSERT INTO situacion_x_tipocomprobante VALUES (3,6);
		INSERT INTO situacion_x_tipocomprobante VALUES (3,7);
		INSERT INTO situacion_x_tipocomprobante VALUES (3,8);
		INSERT INTO situacion_x_tipocomprobante VALUES (3,9);
		INSERT INTO situacion_x_tipocomprobante VALUES (3,10);

		INSERT INTO situacion_x_tipocomprobante VALUES (5,6);
		INSERT INTO situacion_x_tipocomprobante VALUES (5,7);
		INSERT INTO situacion_x_tipocomprobante VALUES (5,8);
		INSERT INTO situacion_x_tipocomprobante VALUES (5,9);
		INSERT INTO situacion_x_tipocomprobante VALUES (5,10);

		INSERT INTO situacion_x_tipocomprobante VALUES (6,6);
		INSERT INTO situacion_x_tipocomprobante VALUES (6,7);
		INSERT INTO situacion_x_tipocomprobante VALUES (6,8);
		INSERT INTO situacion_x_tipocomprobante VALUES (6,9);
		INSERT INTO situacion_x_tipocomprobante VALUES (6,10);

		INSERT INTO situacion_x_tipocomprobante VALUES (7,1);
		INSERT INTO situacion_x_tipocomprobante VALUES (7,2);
		INSERT INTO situacion_x_tipocomprobante VALUES (7,3);
		INSERT INTO situacion_x_tipocomprobante VALUES (7,4);
		INSERT INTO situacion_x_tipocomprobante VALUES (7,5);


CREATE TABLE parametros_facturacion (
	pf_fecha_ultima_factura date,
	pf_numero_control int 
);

insert into parametros_facturacion values('01/01/2014',0);

create table encabezado_factura(
	ef_encabezado_factura_id int not null,
	ef_tipo_comprobante int not null,
	ef_punto_venta int not null,
	ef_num_ptoVenta_tipoComp int not null,
	ef_numero_control int not null,
	ef_cliente varchar(20) not null,
	ef_fecha_facturacion date,
	ef_iva_general float,
	ef_tasa_diferencial float,
	ef_sobretasa float,
	ef_exento float,
	ef_tasa_reducida float,
	ef_no_gravado float,
	ef_impuesto_interno float,
	ef_subtotal float,
	ef_total float,
	ef_confirmado bit,
	primary key(ef_encabezado_factura_id),
	foreign key(ef_tipo_comprobante)references tipo_comprobante(tc_codigo),
	foreign key(ef_punto_venta)references punto_venta(pv_codigo),
	foreign key(ef_cliente) references clientes(cli_codigo)
);

create table renglon_factura(
rf_encabezado_factura_id int not null,
rf_num_renglon int not null,
rf_codigo_producto int not null,
rf_cantidad int not null,
rf_iva_general float,
rf_tasa_diferencial float,
rf_sobretasa float,
rf_exento float,
rf_tasa_reducida float,
rf_no_gravado float,
rf_impuesto_interno float,
rf_importe float,
rf_confirmado bit,
primary key (rf_encabezado_factura_id,rf_num_renglon),
foreign key (rf_encabezado_factura_id) references encabezado_factura(ef_encabezado_factura_id),
foreign key (rf_codigo_producto) references productos(prod_codigo)
)
