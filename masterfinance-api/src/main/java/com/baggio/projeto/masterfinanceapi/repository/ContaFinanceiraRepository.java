package com.baggio.projeto.masterfinanceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baggio.projeto.masterfinanceapi.entities.ContaFinanceira;

public interface ContaFinanceiraRepository extends JpaRepository<ContaFinanceira, Long> {

	@Query("SELECT c FROM ContaFinanceira c "
			+ "WHERE LOWER(c.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))")
	Page<ContaFinanceira> findByDescricao(Pageable pageable, String descricao);
	
}
