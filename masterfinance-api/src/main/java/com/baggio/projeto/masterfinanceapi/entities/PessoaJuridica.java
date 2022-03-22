package com.baggio.projeto.masterfinanceapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_pessoa_juridica")
@PrimaryKeyJoinColumn( name = "id")
public class PessoaJuridica extends Pessoa{
	
	private static final long serialVersionUID = 1L;

	private String cnpj;
	
	@Column(name = "razao_social")
	private String razaoSocial;
	
	public PessoaJuridica() {

	}

	public PessoaJuridica(Long id, String nome, String email, String telefone, String celular, String cnpj,
			String razaoSocial) {
		super(id, nome, email, telefone, celular);
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
	}

}
