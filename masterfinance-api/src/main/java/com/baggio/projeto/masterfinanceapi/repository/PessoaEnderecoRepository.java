package com.baggio.projeto.masterfinanceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baggio.projeto.masterfinanceapi.entities.Endereco;
import com.baggio.projeto.masterfinanceapi.entities.Pessoa;
import com.baggio.projeto.masterfinanceapi.entities.PessoaEndereco;

public interface PessoaEnderecoRepository extends JpaRepository<PessoaEndereco, Long> {
	@Query("SELECT pe FROM PessoaEndereco pe "
			+ "WHERE pe.id.pessoa = :pessoa ")
	Page<PessoaEndereco> find(Pageable pageable, Pessoa pessoa);
	
	@Query("SELECT pe FROM PessoaEndereco pe "
			+ "WHERE pe.id.pessoa = :pessoa AND "
			+ "pe.id.endereco = :endereco ")
	PessoaEndereco find(Pessoa pessoa, Endereco endereco);
}
