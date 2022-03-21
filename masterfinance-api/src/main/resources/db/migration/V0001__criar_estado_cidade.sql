create table tb_estado (
	id serial not null,
	nome varchar(80) not null,
	
	constraint estado_id primary key (id)
);

create table tb_cidade (
	id serial not null,
	nome varchar(80) not null,
	estado_id integer not null,
	
	constraint cidade_id primary key (id),
	constraint estado_cidade_fk foreign key (estado_id) references tb_estado(id)
);