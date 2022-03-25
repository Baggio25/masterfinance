package com.baggio.projeto.masterfinanceapi.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.baggio.projeto.masterfinanceapi.entities.Endereco;
import com.baggio.projeto.masterfinanceapi.entities.Pessoa;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class PessoaEnderecoPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	
	public PessoaEnderecoPK() {
	}

	public PessoaEnderecoPK(Pessoa pessoa, Endereco endereco) {
		this.pessoa = pessoa;
		this.endereco = endereco;
	}

}
