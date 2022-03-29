package com.baggio.projeto.masterfinanceapi.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.projeto.masterfinanceapi.dto.ContaFinanceiraDTO;
import com.baggio.projeto.masterfinanceapi.entities.ContaFinanceira;
import com.baggio.projeto.masterfinanceapi.repository.ContaFinanceiraRepository;
import com.baggio.projeto.masterfinanceapi.service.exceptions.ResourceNotFoundException;
import com.baggio.projeto.masterfinanceapi.service.generic.GenericService;

@Service
public class ContaFinanceiraService implements GenericService<ContaFinanceira, ContaFinanceiraDTO, Long> {

	@Autowired
	private ContaFinanceiraRepository contaFinanceiraRepository;
	
	@Override
	public JpaRepository<ContaFinanceira, Long> getRepository() {
		return contaFinanceiraRepository;
	}
	
	@Transactional(readOnly = true)
	public Page<ContaFinanceiraDTO> findByDescricao(Pageable pageable, String descricao) {
		Page<ContaFinanceira> page = contaFinanceiraRepository.findByDescricao(pageable, descricao);
		return page.map(contaFinanceira -> new ContaFinanceiraDTO(contaFinanceira));
	}

	@Transactional
	public ContaFinanceiraDTO insert(ContaFinanceiraDTO dto) {
		ContaFinanceira contaFinanceira = new ContaFinanceira();
		dtoToEntity(dto, contaFinanceira);

		contaFinanceira.setBancaria(false);
		contaFinanceira = contaFinanceiraRepository.save(contaFinanceira);

		return new ContaFinanceiraDTO(contaFinanceira);
	}

	@Transactional
	public ContaFinanceiraDTO update(ContaFinanceiraDTO dto, Long id) {
		try {
			ContaFinanceira contaFinanceira = contaFinanceiraRepository.getById(id);
			dtoToEntity(dto, contaFinanceira);

			contaFinanceira.setBancaria(false);
			contaFinanceira = contaFinanceiraRepository.save(contaFinanceira);

			return new ContaFinanceiraDTO(contaFinanceira);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}

	}

	private void dtoToEntity(ContaFinanceiraDTO contaFinanceiraDTO, ContaFinanceira contaFinanceira) {
		contaFinanceira.setDescricao(contaFinanceiraDTO.getDescricao());
		contaFinanceira.setSaldo(contaFinanceiraDTO.getSaldo());
	}

}