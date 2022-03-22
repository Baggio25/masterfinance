create table tb_conta_receber (
	id serial not null,
	numero varchar(80) not null,
	parcela varchar(80) not null,
	descricao varchar(80) not null,
	valor numeric(10,2) not null,
	saldo_pagar numeric(10,2) not null,
	data_vencimento timestamp not null,
	data_pagamento timestamp not null,
	observacao text,	
	status_pagamento varchar(80) not null,
	anexo varchar(300) not null,
	
	conta_id integer not null,
	historico_financeiro_id integer not null,
	pessoa_id integer not null,
	usuario_id integer not null,
	
	constraint conta_receber_id primary key (id),
	constraint conta_conta_receber_fk foreign key (conta_id) references tb_conta(id),
	constraint historico_conta_receber_fk foreign key (historico_financeiro_id) references tb_historico_financeiro(id),
	constraint pessoa_conta_receber_fk foreign key (pessoa_id) references tb_pessoa(id),
	constraint usuario_conta_receber_fk foreign key (usuario_id) references tb_usuario(id)
);