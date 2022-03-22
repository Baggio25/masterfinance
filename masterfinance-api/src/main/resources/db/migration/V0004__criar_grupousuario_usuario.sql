create table tb_grupo_usuario (
	id serial not null,
	descricao varchar(80) not null,
	autorizacao varchar(80) not null,
	
	constraint grupo_usuario_id primary key (id)
);

create table tb_usuario (
	id serial not null,
	nome varchar(80) not null,
	email varchar(80) not null,
	senha varchar(80) not null,
	ativo boolean default true,
	grupo_usuario_id integer not null,
	
	constraint usuario_id primary key (id),
	constraint grupo_usuario_usuario_fk foreign key (grupo_usuario_id) references tb_grupo_usuario(id)
);