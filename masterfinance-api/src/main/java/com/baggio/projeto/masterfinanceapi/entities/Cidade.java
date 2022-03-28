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

import com.baggio.projeto.masterfinanceapi.dto.CidadeDTO;
import com.baggio.projeto.masterfinanceapi.service.util.Convertible;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_cidade")
@SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade", allocationSize = 1, initialValue = 1)
public class Cidade implements Serializable, Convertible<CidadeDTO>{

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cidade")
	private Long id;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	@Override
	public CidadeDTO convert() {
		return new CidadeDTO(this);
	}
	
	
}
