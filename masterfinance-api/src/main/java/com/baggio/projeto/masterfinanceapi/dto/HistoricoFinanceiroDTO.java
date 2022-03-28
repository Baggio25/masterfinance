package com.baggio.projeto.masterfinanceapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baggio.projeto.masterfinanceapi.entities.HistoricoFinanceiro;
import com.baggio.projeto.masterfinanceapi.entities.enums.TipoHistoricoFinanceiro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoricoFinanceiroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	private String descricao;
	
	private Boolean ativo;
	
	@NotNull(message = "Campo obrigatório")
	private TipoHistoricoFinanceiro tipoHistoricoFinanceiro;

	public HistoricoFinanceiroDTO() {

	}

	public HistoricoFinanceiroDTO(Long id, String descricao, Boolean ativo, TipoHistoricoFinanceiro tipoHistoricoFinanceiro) {
		this.id = id;
		this.descricao = descricao;
		this.ativo = ativo;
		this.tipoHistoricoFinanceiro = tipoHistoricoFinanceiro;
	}

	public HistoricoFinanceiroDTO(HistoricoFinanceiro historicoFinanceiro) {
		id = historicoFinanceiro.getId();
		descricao = historicoFinanceiro.getDescricao();
		ativo = historicoFinanceiro.getAtivo();
		tipoHistoricoFinanceiro = historicoFinanceiro.getTipoHistoricoFinanceiro();
	}

	
}
