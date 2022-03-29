package com.baggio.projeto.masterfinanceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.baggio.projeto.masterfinanceapi.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	@Query("SELECT e FROM Endereco e "
			+ "WHERE e.cep = :cep ")
	Page<Endereco> findByCep(Pageable pageable, String cep);

	/*@Query("SELECT e FROM Endereco e "
			+ "INNER JOIN Pessoa p "
			+ "WHERE p.id = :idPessoa")
	Page<Endereco> findByPessoa(Pageable pageable, Long idPessoa);*/
}
