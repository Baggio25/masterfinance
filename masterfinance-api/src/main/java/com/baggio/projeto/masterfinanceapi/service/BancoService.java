package com.baggio.projeto.masterfinanceapi.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.projeto.masterfinanceapi.dto.BancoDTO;
import com.baggio.projeto.masterfinanceapi.entities.Banco;
import com.baggio.projeto.masterfinanceapi.repository.BancoRepository;
import com.baggio.projeto.masterfinanceapi.service.exceptions.ResourceNotFoundException;
import com.baggio.projeto.masterfinanceapi.service.generic.GenericService;

@Service
public class BancoService implements GenericService<Banco, BancoDTO, Long> {

	@Autowired
	private BancoRepository bancoRepository;

	@Override
	public JpaRepository<Banco, Long> getRepository() {
		return bancoRepository;
	}

	@Transactional(readOnly = true)
	public Page<BancoDTO> findByNome(Pageable pageable, String nome) {
		Page<Banco> page = bancoRepository.findByNome(pageable, nome);
		return page.map(banco -> new BancoDTO(banco));
	}

	@Transactional
	public BancoDTO insert(BancoDTO dto) {
		Banco banco = new Banco();
		dtoToEntity(dto, banco);

		banco = bancoRepository.save(banco);

		return new BancoDTO(banco);
	}

	@Transactional
	public BancoDTO update(BancoDTO dto, Long id) {
		try {
			Banco banco = bancoRepository.getById(id);
			dtoToEntity(dto, banco);

			banco = bancoRepository.save(banco);

			return new BancoDTO(banco);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	private void dtoToEntity(BancoDTO bancoDTO, Banco banco) {
		banco.setNome(bancoDTO.getNome());
		banco.setNumero(bancoDTO.getNumero());
	}

}
