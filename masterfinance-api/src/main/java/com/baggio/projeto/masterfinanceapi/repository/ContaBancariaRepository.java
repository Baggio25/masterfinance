package com.baggio.projeto.masterfinanceapi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baggio.projeto.masterfinanceapi.entities.ContaBancaria;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {

	@Query("SELECT c FROM ContaBancaria c "
			+ "WHERE LOWER(c.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))")
	Page<ContaBancaria> findByDescricao(Pageable pageable, String descricao);

	@Query("SELECT c FROM ContaBancaria c "
			+ "WHERE c.bancaria = true")
	Page<ContaBancaria> findAllPaged(Pageable pageable);
	
	@Query("SELECT c FROM ContaBancaria c "
			+ "WHERE c.id = :id "
			+ "AND c.bancaria = true")
	Optional<ContaBancaria> findAllId(Long id);
	
}
