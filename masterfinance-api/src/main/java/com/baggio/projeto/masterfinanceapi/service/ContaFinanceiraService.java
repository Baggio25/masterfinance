package com.baggio.projeto.masterfinanceapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.projeto.masterfinanceapi.dto.ContaFinanceiraDTO;
import com.baggio.projeto.masterfinanceapi.entities.ContaFinanceira;
import com.baggio.projeto.masterfinanceapi.repository.ContaFinanceiraRepository;
import com.baggio.projeto.masterfinanceapi.service.exceptions.DatabaseException;
import com.baggio.projeto.masterfinanceapi.service.exceptions.ResourceNotFoundException;
import com.baggio.projeto.masterfinanceapi.service.generic.GenericService;

@Service
public class ContaFinanceiraService implements GenericService<ContaFinanceiraDTO, Long> {

	@Autowired
	private ContaFinanceiraRepository contaFinanceiraRepository;

	@Transactional(readOnly = true)
	@Override
	public List<ContaFinanceiraDTO> findAll() {
		List<ContaFinanceira> list = contaFinanceiraRepository.findAll();
		return list.stream().map(contaFinanceira -> new ContaFinanceiraDTO(contaFinanceira))
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	@Override
	public Page<ContaFinanceiraDTO> findAllPaged(Pageable pageable) {
		Page<ContaFinanceira> page = contaFinanceiraRepository.findAll(pageable);
		return page.map(contaFinanceira -> new ContaFinanceiraDTO(contaFinanceira));
	}

	@Transactional(readOnly = true)
	public Page<ContaFinanceiraDTO> findByNome(Pageable pageable, String descricao) {
		Page<ContaFinanceira> page = contaFinanceiraRepository.findByDescricao(pageable, descricao);
		return page.map(contaFinanceira -> new ContaFinanceiraDTO(contaFinanceira));
	}

	@Transactional(readOnly = true)
	@Override
	public ContaFinanceiraDTO findById(Long id) {
		Optional<ContaFinanceira> optional = contaFinanceiraRepository.findById(id);
		ContaFinanceira contaFinanceira = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ContaFinanceiraDTO(contaFinanceira);
	}

	@Transactional
	@Override
	public ContaFinanceiraDTO insert(ContaFinanceiraDTO dto) {
		ContaFinanceira contaFinanceira = new ContaFinanceira();
		dtoToEntity(dto, contaFinanceira);

		contaFinanceira = contaFinanceiraRepository.save(contaFinanceira);

		return new ContaFinanceiraDTO(contaFinanceira);
	}

	@Override
	public ContaFinanceiraDTO update(ContaFinanceiraDTO dto, Long id) {
		try {
			ContaFinanceira contaFinanceira = contaFinanceiraRepository.getById(id);
			dtoToEntity(dto, contaFinanceira);

			contaFinanceira = contaFinanceiraRepository.save(contaFinanceira);

			return new ContaFinanceiraDTO(contaFinanceira);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Id not found " + id);
		}

	}

	@Override
	public void delete(Long id) {
		try {
			contaFinanceiraRepository.deleteById(id);
		}  catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}

	}

	private void dtoToEntity(ContaFinanceiraDTO contaFinanceiraDTO, ContaFinanceira contaFinanceira) {
		contaFinanceira.setDescricao(contaFinanceiraDTO.getDescricao());
		contaFinanceira.setSaldo(contaFinanceiraDTO.getSaldo());
	}

}
