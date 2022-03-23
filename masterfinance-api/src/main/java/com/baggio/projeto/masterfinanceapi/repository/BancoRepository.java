package com.baggio.projeto.masterfinanceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baggio.projeto.masterfinanceapi.entities.Banco;

public interface BancoRepository extends JpaRepository<Banco, Long> {

	@Query("SELECT b FROM Banco b "
			+ "WHERE LOWER(b.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
	Page<Banco> findByNome(Pageable pageable, String nome);
	
	@Query("SELECT b FROM Banco b "
			+ "WHERE b.nome = :nome ")
	Banco findByNome(String nome);

	@Query("SELECT b FROM Banco b "
			+ "WHERE b.numero = :numero ")
	Banco findByNumero(String numero);
	
}
