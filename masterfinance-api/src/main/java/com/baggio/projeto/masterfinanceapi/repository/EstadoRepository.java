package com.baggio.projeto.masterfinanceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baggio.projeto.masterfinanceapi.entities.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	@Query("SELECT e FROM Estado e "
			+ "WHERE LOWER(e.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
	Page<Estado> findByNome(Pageable pageable, String nome);

	@Query("SELECT e FROM Estado e "
			+ "WHERE e.nome = :nome ")
	Estado findByNome(String nome);
}
