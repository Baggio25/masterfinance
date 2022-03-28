package com.baggio.projeto.masterfinanceapi.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_grupo_usuario")
@SequenceGenerator(name = "seq_grupo_usuario", sequenceName = "seq_grupo_usuario", allocationSize = 1, initialValue = 1)
public class GrupoUsuario implements Serializable{


	private static final long serialVersionUID = 1L;

	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_grupo_usuario")
	private Long id;
	
	private String nome;
	
	private String autorizacao;
	
}
