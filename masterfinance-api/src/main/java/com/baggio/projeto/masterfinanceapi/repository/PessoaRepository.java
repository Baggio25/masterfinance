package com.baggio.projeto.masterfinanceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baggio.projeto.masterfinanceapi.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Query("SELECT p FROM Pessoa p "
			+ "WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))  ")
	Page<Pessoa> findByNome(Pageable pageable, String nome);

}
