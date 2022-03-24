package com.baggio.projeto.masterfinanceapi.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.projeto.masterfinanceapi.dto.ContaBancariaDTO;
import com.baggio.projeto.masterfinanceapi.entities.Banco;
import com.baggio.projeto.masterfinanceapi.entities.ContaBancaria;
import com.baggio.projeto.masterfinanceapi.repository.BancoRepository;
import com.baggio.projeto.masterfinanceapi.repository.ContaBancariaRepository;
import com.baggio.projeto.masterfinanceapi.service.exceptions.DatabaseException;
import com.baggio.projeto.masterfinanceapi.service.exceptions.ResourceNotFoundException;
import com.baggio.projeto.masterfinanceapi.service.generic.GenericService;

@Service
public class ContaBancariaService implements GenericService<ContaBancariaDTO, Long> {

	@Autowired
	private ContaBancariaRepository contaBancariaRepository;	
	
	@Autowired
	private BancoRepository bancoRepository;

	@Transactional(readOnly = true)
	@Override
	public Page<ContaBancariaDTO> findAllPaged(Pageable pageable) {
		Page<ContaBancaria> page = contaBancariaRepository.findAllPaged(pageable);
		return page.map(contaBancaria -> new ContaBancariaDTO(contaBancaria));
	}

	@Transactional(readOnly = true)
	public Page<ContaBancariaDTO> findByDescricao(Pageable pageable, String descricao) {
		Page<ContaBancaria> page = contaBancariaRepository.findByDescricao(pageable, descricao);
		return page.map(contaBancaria -> new ContaBancariaDTO(contaBancaria));
	}

	@Transactional(readOnly = true)
	@Override
	public ContaBancariaDTO findById(Long id) {
		Optional<ContaBancaria> optional = contaBancariaRepository.findById(id);
		ContaBancaria contaBancaria = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ContaBancariaDTO(contaBancaria);
	}

	@Transactional
	@Override
	public ContaBancariaDTO insert(ContaBancariaDTO dto) {
		ContaBancaria contaBancaria = new ContaBancaria();
		dtoToEntity(dto, contaBancaria);

		contaBancaria.setBancaria(true);
		contaBancaria = contaBancariaRepository.save(contaBancaria);

		return new ContaBancariaDTO(contaBancaria);
	}

	@Override
	public ContaBancariaDTO update(ContaBancariaDTO dto, Long id) {
		try {
			ContaBancaria contaBancaria = contaBancariaRepository.getById(id);
			dtoToEntity(dto, contaBancaria);

			contaBancaria.setBancaria(true);
			contaBancaria = contaBancariaRepository.save(contaBancaria);

			return new ContaBancariaDTO(contaBancaria);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("BancoId not found " + dto.getBancoId());
		}

	}

	@Override
	public void delete(Long id) {
		try {
			contaBancariaRepository.deleteById(id);
		}  catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}

	}

	private void dtoToEntity(ContaBancariaDTO contaBancariaDTO, ContaBancaria contaBancaria) {
		Banco banco = bancoRepository.getById(contaBancariaDTO.getBancoId());
		
		contaBancaria.setDescricao(contaBancariaDTO.getDescricao());
		contaBancaria.setSaldo(contaBancariaDTO.getSaldo());
		contaBancaria.setNumero(contaBancariaDTO.getNumero());
		contaBancaria.setDigito(contaBancariaDTO.getDigito());
		contaBancaria.setAgencia(contaBancariaDTO.getAgencia());
		contaBancaria.setDigitoAgencia(contaBancariaDTO.getDigitoAgencia());		
		contaBancaria.setBanco(banco);
	}

	@Override
	public List<ContaBancariaDTO> findAll() {
		return null;
	}
	
}
