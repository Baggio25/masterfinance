create table tb_movimento_avulso (
	id serial not null,
	descricao varchar(80) not null,
	valor numeric(10,2) not null,
	data_movimento timestamp not null,
	data_competencia timestamp not null,
		
	conta_id integer not null,
	historico_financeiro_id integer not null,
	pessoa_id integer not null,
	
	constraint movimento_avulso_id primary key (id),
	constraint conta_movimento_avulso_fk foreign key (conta_id) references tb_conta(id),
	constraint historico_financeiro_movimento_avulso_fk foreign key (historico_financeiro_id) references tb_historico_financeiro(id),
	constraint pessoa_movimento_avulso_fk foreign key (pessoa_id) references tb_pessoa(id)
);