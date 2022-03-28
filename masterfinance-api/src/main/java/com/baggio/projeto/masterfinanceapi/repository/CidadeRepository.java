package com.baggio.projeto.masterfinanceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baggio.projeto.masterfinanceapi.entities.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	@Query("SELECT c FROM Cidade c "
			+ "WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
	Page<Cidade> findByNome(Pageable pageable, String nome);
	
}
