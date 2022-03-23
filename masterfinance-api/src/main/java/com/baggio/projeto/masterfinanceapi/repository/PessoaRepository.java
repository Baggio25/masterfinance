package com.baggio.projeto.masterfinanceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baggio.projeto.masterfinanceapi.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
