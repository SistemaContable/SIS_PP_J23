CREATE TABLE Empresas
(
	razon_social varchar(50) not null,
	denominacion_interna varchar(20) not null,
	
	CONSTRAINT PK_Empresas primary key (razon_social)
);

CREATE INDEX IX_Empresas_razon_social ON Empresas (razon_social);
CREATE INDEX IX_Empresas_denominacion_interna ON Empresas (denominacion_interna);

CREATE TABLE Directorios
(
	descripcion varchar(30),
	directorio varchar(150) not null,
	
	CONSTRAINT PK_Directorios primary key (directorio)
);

CREATE TABLE Usuarios
(
	usr_nombre_usuario varchar(30) not null,
	usr_contrasenia varchar(20) not null,
	
	CONSTRAINT PK_Usuarios primary key (usr_nombre_usuario)
);

CREATE INDEX IX_Usuarios_usr_nombre_usuario ON Usuarios (usr_nombre_usuario);