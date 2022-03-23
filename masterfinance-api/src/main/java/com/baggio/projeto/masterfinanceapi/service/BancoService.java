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

import com.baggio.projeto.masterfinanceapi.dto.BancoDTO;
import com.baggio.projeto.masterfinanceapi.entities.Banco;
import com.baggio.projeto.masterfinanceapi.repository.BancoRepository;
import com.baggio.projeto.masterfinanceapi.service.exceptions.DatabaseException;
import com.baggio.projeto.masterfinanceapi.service.exceptions.ResourceNotFoundException;
import com.baggio.projeto.masterfinanceapi.service.generic.GenericService;

@Service
public class BancoService implements GenericService<BancoDTO, Long> {

	@Autowired
	private BancoRepository bancoRepository;

	@Transactional(readOnly = true)
	@Override
	public List<BancoDTO> findAll() {
		List<Banco> list = bancoRepository.findAll();
		return list.stream().map(banco -> new BancoDTO(banco)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	@Override
	public Page<BancoDTO> findAllPaged(Pageable pageable) {
		Page<Banco> page = bancoRepository.findAll(pageable);
		return page.map(banco -> new BancoDTO(banco));
	}
	
	@Transactional(readOnly = true)
	public Page<BancoDTO> findByNome(Pageable pageable, String nome) {
		Page<Banco> page = bancoRepository.findByNome(pageable, nome);
		return page.map(banco -> new BancoDTO(banco));
	}

	@Transactional(readOnly = true)
	@Override
	public BancoDTO findById(Long id) {
		Optional<Banco> optional = bancoRepository.findById(id);
		Banco banco = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new BancoDTO(banco);
	}

	@Transactional
	@Override
	public BancoDTO insert(BancoDTO dto) {
		Banco banco = new Banco();
		dtoToEntity(dto, banco);

		banco = bancoRepository.save(banco);

		return new BancoDTO(banco);
	}

	@Transactional
	@Override
	public BancoDTO update(BancoDTO dto, Long id) {
		try {
			Banco banco = bancoRepository.getById(id);
			dtoToEntity(dto, banco);

			banco = bancoRepository.save(banco);

			return new BancoDTO(banco);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Id not found " + id);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			bancoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}

	}

	private void dtoToEntity(BancoDTO bancoDTO, Banco banco) {
		banco.setNome(bancoDTO.getNome());
		banco.setNumero(bancoDTO.getNumero());
	}

}
