create table tb_banco (
	id serial not null,
	nome varchar(80) not null,
	numero varchar(3) not null,
	
	constraint banco_id primary key (id)
);

create table tb_conta (
	id serial not null,
	descricao varchar(80) not null,
	saldo numeric(10,2) not null,
	
	constraint conta_id primary key (id)
);

create table tb_conta_bancaria (
	id integer not null,
	numero varchar(10) not null,
	digito varchar(2) not null,
	agencia varchar(10),
	digito_agencia varchar(2),
  	banco_id integer not null,
	
	constraint conta_bancaria_id primary key (id),
	constraint conta_bancaria_fk foreign key (id) references tb_conta(id),
 	constraint conta_bancaria_banco_fk foreign key (banco_id) references tb_banco(id) 
);

create table tb_conta_financeira (
	id integer not null,
	
	constraint conta_financeira_id primary key (id),
	constraint conta_financeira_fk foreign key (id) references tb_conta(id)
);

create table tb_historico_financeiro (
	id serial not null,
	descricao varchar(80) not null,
	tipo_historico_financeiro varchar(80) not null,
	ativo boolean default true,
	
	constraint historico_financeiro_id primary key (id)
);




