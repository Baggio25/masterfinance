package com.baggio.projeto.masterfinanceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baggio.projeto.masterfinanceapi.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
