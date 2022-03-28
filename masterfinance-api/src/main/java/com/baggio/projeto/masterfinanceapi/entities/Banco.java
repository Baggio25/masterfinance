package com.baggio.projeto.masterfinanceapi.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.baggio.projeto.masterfinanceapi.dto.BancoDTO;
import com.baggio.projeto.masterfinanceapi.service.util.Convertible;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_banco")
@SequenceGenerator(name = "seq_banco", sequenceName = "seq_banco", allocationSize = 1, initialValue = 1)
public class Banco implements Serializable, Convertible<BancoDTO>{


	private static final long serialVersionUID = 1L;

	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_banco")
	private Long id;
	
	private String nome;
	
	private String numero;

	@Override
	public BancoDTO convert() {
		return new BancoDTO(this);
	}

}
