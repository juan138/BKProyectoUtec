
use proyectoUtec;

-- es nesesario crear las SCHEMA antes de crear las tablas
CREATE SCHEMA person;
CREATE SCHEMA dbo;



-- tabla de usuario

create table person.usuarios(
pk_user int not null auto_increment primary key,
user_name varchar(25) not null,
user_pass varchar(25) not null,
status_user boolean
);

create table person.datos_personales(
pk_date_person int not null auto_increment primary key,
first_name_person varchar(50),
last_name_person varchar(50),
fk_user int null
);

create table dbo.producto(
pk_producto int not null auto_increment primary key,
code_product varchar(5) not null,
name_product varchar(50),
min_product int default 0,
max_pruduct int default 0,
status_product int default 3
);

create table dbo.solicitud(
pk_solicitud int not null auto_increment primary key,
cantidad int not null,
fk_product int not null,
fk_user int not null,
status_solicitud boolean not null default false
);
