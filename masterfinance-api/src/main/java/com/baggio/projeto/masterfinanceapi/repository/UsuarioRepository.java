package com.baggio.projeto.masterfinanceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baggio.projeto.masterfinanceapi.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
