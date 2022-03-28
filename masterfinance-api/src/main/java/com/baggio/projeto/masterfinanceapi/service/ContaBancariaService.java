package com.baggio.projeto.masterfinanceapi.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
public class ContaBancariaService implements GenericService<ContaBancaria, ContaBancariaDTO, Long> {

	@Autowired
	private ContaBancariaRepository contaBancariaRepository;

	@Autowired
	private BancoRepository bancoRepository;

	@Override
	public JpaRepository<ContaBancaria, Long> getRepository() {
		return contaBancariaRepository;
	}

	@Transactional(readOnly = true)
	public Page<ContaBancariaDTO> findByDescricao(Pageable pageable, String descricao) {
		Page<ContaBancaria> page = contaBancariaRepository.findByDescricao(pageable, descricao);
		return page.map(contaBancaria -> new ContaBancariaDTO(contaBancaria));
	}

	@Transactional
	public ContaBancariaDTO insert(ContaBancariaDTO dto) {
		ContaBancaria contaBancaria = new ContaBancaria();
		dtoToEntity(dto, contaBancaria);

		contaBancaria.setBancaria(true);
		contaBancaria = contaBancariaRepository.save(contaBancaria);

		return new ContaBancariaDTO(contaBancaria);
	}

	@Transactional
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

}
