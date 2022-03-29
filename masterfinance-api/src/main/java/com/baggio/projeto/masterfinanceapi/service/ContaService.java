package com.baggio.projeto.masterfinanceapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.projeto.masterfinanceapi.dto.ContaDTO;
import com.baggio.projeto.masterfinanceapi.entities.Conta;
import com.baggio.projeto.masterfinanceapi.repository.ContaRepository;
import com.baggio.projeto.masterfinanceapi.service.exceptions.ResourceNotFoundException;

//Servirá apenas para buscar a conta nas funcionalidades de movimentação
@Service
public class ContaService  {

	@Autowired
	private ContaRepository contaRepository;
		
	@Transactional(readOnly = true)
	public Page<ContaDTO> findByDescricao(Pageable pageable, String descricao) {
		Page<Conta> page = contaRepository.findByDescricao(pageable, descricao);
		return page.map(conta -> new ContaDTO(conta));
	}
	
	@Transactional(readOnly = true)
	public ContaDTO findById(Long id) {
		Optional<Conta> optional = contaRepository.findById(id);
		Conta conta = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ContaDTO(conta);
	}
	
	
}
