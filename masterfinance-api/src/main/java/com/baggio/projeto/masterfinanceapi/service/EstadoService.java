package com.baggio.projeto.masterfinanceapi.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.projeto.masterfinanceapi.dto.EstadoDTO;
import com.baggio.projeto.masterfinanceapi.entities.Estado;
import com.baggio.projeto.masterfinanceapi.repository.EstadoRepository;
import com.baggio.projeto.masterfinanceapi.service.exceptions.ResourceNotFoundException;
import com.baggio.projeto.masterfinanceapi.service.generic.GenericService;

@Service
public class EstadoService implements GenericService<Estado, EstadoDTO, Long> {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	public JpaRepository<Estado, Long> getRepository() {
		return estadoRepository;
	}
		
	@Transactional(readOnly = true)
	public Page<EstadoDTO> findByNome(Pageable pageable, String nome) {
		Page<Estado> page = estadoRepository.findByNome(pageable, nome);
		return page.map(estado -> new EstadoDTO(estado));
	}

	@Transactional
	public EstadoDTO insert(EstadoDTO dto) {
		Estado estado = new Estado();
		dtoToEntity(dto, estado);

		estado = estadoRepository.save(estado);

		return new EstadoDTO(estado);
	}

	@Transactional
	public EstadoDTO update(EstadoDTO dto, Long id) {
		try {
			Estado estado = estadoRepository.getById(id);
			dtoToEntity(dto, estado);

			estado = estadoRepository.save(estado);

			return new EstadoDTO(estado);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	private void dtoToEntity(EstadoDTO estadoDTO, Estado estado) {
		estado.setNome(estadoDTO.getNome());
	}

	
}
