package com.baggio.projeto.masterfinanceapi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.baggio.projeto.masterfinanceapi.entities.enums.TipoPessoa;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_pessoa")
@SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1, initialValue = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String telefone;
	
	private String celular;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_pessoa")
	private TipoPessoa tipoPessoa; //Define qual será o tipo.. conforme selecionado indica se vai gravar na tabela fisica ou juridica
	
	public Pessoa() {
	}

	public Pessoa(Long id, String nome, String email, String telefone, String celular, TipoPessoa tipoPessoa) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.celular = celular;
		this.tipoPessoa = tipoPessoa;
	}
	
}
