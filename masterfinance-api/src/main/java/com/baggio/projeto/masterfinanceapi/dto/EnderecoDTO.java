package com.baggio.projeto.masterfinanceapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baggio.projeto.masterfinanceapi.entities.Endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Campo obrigatório")
	private String rua;

	@NotBlank(message = "Campo obrigatório")
	private String cep;

	@NotBlank(message = "Campo obrigatório")
	private String bairro;

	@NotNull(message = "Campo obrigatório")
	private Long cidadeId;

	@NotNull(message = "Campo obrigatório")
	private Long pessoaId;

	public EnderecoDTO() {
	}

	public EnderecoDTO(Long id, String rua, String cep, String bairro, Long cidadeId, Long pessoaId) {
		this.id = id;
		this.rua = rua;
		this.cep = cep;
		this.bairro = bairro;
		this.cidadeId = cidadeId;
		this.pessoaId = pessoaId;
	}

	public EnderecoDTO(Endereco endereco) {
		id = endereco.getId();
		rua = endereco.getRua();
		cep = endereco.getCep();
		bairro = endereco.getBairro();
		cidadeId = endereco.getCidade().getId();
		pessoaId = endereco.getPessoa().getId();
	}

}
