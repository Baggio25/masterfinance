create table tb_pessoa (
	id serial not null,
	nome varchar(80) not null,
	email varchar(80) not null,
	telefone varchar(20),
	celular varchar(20),
	
	constraint pessoa_id primary key (id)
);

create table tb_endereco (
	id serial not null,
	rua varchar(80) not null,
	cep varchar(10) not null,	
	bairro varchar(80) not null,
	cidade_id integer not null,
	
	constraint endereco_id primary key (id),
	constraint endereco_cidade_fk foreign key (cidade_id) references tb_cidade(id)
);

create table tb_pessoa_endereco (
	pessoa_id integer not null,
	endereco_id integer not null,
	numero varchar(10) not null,
	complemento varchar(80) not null,
	tipo_endereco varchar(80) not null,
	
	constraint pessoa_endereco_id primary key (pessoa_id, endereco_id),
	constraint pessoa_endereco_fk foreign key (pessoa_id) references tb_pessoa(id),
	constraint endereco_pessoa_fk foreign key (endereco_id) references tb_endereco(id)
);

create table tb_pessoa_fisica (
	id integer not null,
	cpf varchar(14) not null,
	data_nascimento timestamp,
	
	constraint pessoa_fisica_id primary key (id),
	constraint pessoa_fisica_fk foreign key (id) references tb_pessoa(id)
);

create table tb_pessoa_juridica (
	id integer not null,
	cnpj varchar(20),
	razao_social varchar(80),
	
	constraint pessoa_juridica_id primary key (id),
	constraint pessoa_juridica_fk foreign key (id) references tb_pessoa(id)
);