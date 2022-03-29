package com.baggio.projeto.masterfinanceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baggio.projeto.masterfinanceapi.entities.PessoaJuridica;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

	@Query("SELECT p FROM PessoaJuridica p "
			+ "WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%')) AND "
			+ "p.tipoPessoa = 'JURIDICA'")
	Page<PessoaJuridica> findByNome(Pageable pageable, String nome);
	
	@Query("SELECT p FROM PessoaJuridica p "
			+ "WHERE p.nome = :nome AND "
			+ "p.tipoPessoa = 'JURIDICA' ")
	PessoaJuridica findByNome(String nome);
	
}
