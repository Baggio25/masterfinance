package com.baggio.projeto.masterfinanceapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baggio.projeto.masterfinanceapi.entities.PessoaEndereco;
import com.baggio.projeto.masterfinanceapi.entities.enums.TipoEndereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaEnderecoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Campo obrigatório")
	private Long pessoaId;

	@NotNull(message = "Campo obrigatório")
	private Long enderecoId;

	@NotBlank(message = "Campo obrigatório")
	private String numero;

	@NotBlank(message = "Campo obrigatório")
	private String complemento;

	@NotNull(message = "Campo obrigatório")
	private TipoEndereco tipoEndereco;

	public PessoaEnderecoDTO() {

	}

	public PessoaEnderecoDTO(Long pessoaId, Long enderecoId, String numero, String complemento,
			TipoEndereco tipoEndereco) {
		this.pessoaId = pessoaId;
		this.enderecoId = enderecoId;
		this.numero = numero;
		this.complemento = complemento;
		this.tipoEndereco = tipoEndereco;
	}

	public PessoaEnderecoDTO(PessoaEndereco pessoaEndereco) {
		pessoaId = pessoaEndereco.getId().getPessoa().getId();
		enderecoId = pessoaEndereco.getId().getEndereco().getId();
		numero = pessoaEndereco.getNumero();
		complemento = pessoaEndereco.getComplemento();
		tipoEndereco = pessoaEndereco.getTipoEndereco();
	}

}
