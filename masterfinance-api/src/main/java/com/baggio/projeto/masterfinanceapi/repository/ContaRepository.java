package com.baggio.projeto.masterfinanceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baggio.projeto.masterfinanceapi.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

	@Query("SELECT c FROM Conta c "
			+ "WHERE LOWER(c.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))  ")
	Page<Conta> findByDescricao(Pageable pageable, String descricao);
	
}
