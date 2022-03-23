package com.baggio.projeto.masterfinanceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baggio.projeto.masterfinanceapi.entities.HistoricoFinanceiro;

public interface HistoricoFinanceiroRepository extends JpaRepository<HistoricoFinanceiro, Long> {

}
