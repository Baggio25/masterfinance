package com.baggio.projeto.masterfinanceapi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.baggio.projeto.masterfinanceapi.entities.enums.TipoEndereco;
import com.baggio.projeto.masterfinanceapi.entities.pk.PessoaEnderecoPK;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_pessoa_endereco")
public class PessoaEndereco implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PessoaEnderecoPK id = new PessoaEnderecoPK();	
	
	private String numero;
	
	private String complemento;
	
	@Column(name = "tipo_endereco")
	@Enumerated(EnumType.STRING)
	private TipoEndereco tipoEndereco;

	public PessoaEndereco() {
	}
	
	public PessoaEndereco(Pessoa pessoa, Endereco endereco, String numero, String complemento, TipoEndereco tipoEndereco) {
		id.setPessoa(pessoa);
		id.setEndereco(endereco);
		this.numero = numero;
		this.complemento = complemento;
		this.tipoEndereco = tipoEndereco;
	}
	
	
}
