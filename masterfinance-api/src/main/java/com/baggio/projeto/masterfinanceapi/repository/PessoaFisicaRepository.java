package com.baggio.projeto.masterfinanceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baggio.projeto.masterfinanceapi.entities.PessoaFisica;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

	@Query("SELECT p FROM PessoaFisica p "
			+ "WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%')) AND "
			+ "p.tipoPessoa = 'FISICA'")
	Page<PessoaFisica> findByNome(Pageable pageable, String nome);
	
	@Query("SELECT p FROM PessoaFisica p "
			+ "WHERE p.nome = :nome AND "
			+ "p.tipoPessoa = 'FISICA' ")
	PessoaFisica findByNome(String nome);
	
}
