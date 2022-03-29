package com.baggio.projeto.masterfinanceapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.projeto.masterfinanceapi.dto.PessoaDTO;
import com.baggio.projeto.masterfinanceapi.entities.Pessoa;
import com.baggio.projeto.masterfinanceapi.repository.PessoaRepository;
import com.baggio.projeto.masterfinanceapi.service.exceptions.ResourceNotFoundException;

//Servirá apenas para buscar a pessoa nas funcionalidades de movimentação
@Service
public class PessoaService  {

	@Autowired
	private PessoaRepository pessoaRepository;
		
	@Transactional(readOnly = true)
	public Page<PessoaDTO> findByNome(Pageable pageable, String nome) {
		Page<Pessoa> page = pessoaRepository.findByNome(pageable, nome);
		return page.map(pessoa -> new PessoaDTO(pessoa));
	}
	
	@Transactional(readOnly = true)
	public PessoaDTO findById(Long id) {
		Optional<Pessoa> optional = pessoaRepository.findById(id);
		Pessoa pessoa = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new PessoaDTO(pessoa);
	}
	
	
}
