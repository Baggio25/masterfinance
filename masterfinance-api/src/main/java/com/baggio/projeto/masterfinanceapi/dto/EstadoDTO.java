package com.baggio.projeto.masterfinanceapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.baggio.projeto.masterfinanceapi.entities.Estado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	private String nome;
	
	public EstadoDTO() {
	}

	public EstadoDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public EstadoDTO(Estado estado) {
		id = estado.getId();
		nome = estado.getNome();
	}
	
}
