package com.baggio.projeto.masterfinanceapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.baggio.projeto.masterfinanceapi.dto.PessoaJuridicaDTO;
import com.baggio.projeto.masterfinanceapi.entities.enums.TipoPessoa;
import com.baggio.projeto.masterfinanceapi.service.util.Convertible;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_pessoa_juridica")
public class PessoaJuridica extends Pessoa implements Convertible<PessoaJuridicaDTO>{

	private static final long serialVersionUID = 1L;

	private String cnpj;

	@Column(name = "razao_social")
	private String razaoSocial;

	public PessoaJuridica() {

	}

	public PessoaJuridica(Long id, String nome, String email, String telefone, String celular, TipoPessoa tipoPessoa,
			String cnpj, String razaoSocial) {
		super(id, nome, email, telefone, celular, tipoPessoa);
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
	}

	@Override
	public PessoaJuridicaDTO convert() {
		return new PessoaJuridicaDTO(this);
	}

}
