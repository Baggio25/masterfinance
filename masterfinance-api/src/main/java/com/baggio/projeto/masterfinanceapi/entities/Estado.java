package com.baggio.projeto.masterfinanceapi.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.baggio.projeto.masterfinanceapi.dto.EstadoDTO;
import com.baggio.projeto.masterfinanceapi.service.util.Convertible;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_estado")
@SequenceGenerator(name = "seq_estado", sequenceName = "seq_estado", allocationSize = 1, initialValue = 1)
public class Estado implements Serializable, Convertible<EstadoDTO>{


	private static final long serialVersionUID = 1L;

	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estado")
	private Long id;
	
	private String nome;

	@Override
	public EstadoDTO convert() {
		return new EstadoDTO(this);
	}
	
}
