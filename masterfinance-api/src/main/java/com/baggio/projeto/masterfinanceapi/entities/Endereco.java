package com.baggio.projeto.masterfinanceapi.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_endereco")
@SequenceGenerator(name = "seq_endereco", sequenceName = "seq_endereco", allocationSize = 1, initialValue = 1)
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco")
	private Long id;

	private String rua;

	private String cep;

	private String bairro;

	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

}
