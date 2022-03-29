package com.baggio.projeto.masterfinanceapi.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.projeto.masterfinanceapi.dto.CidadeDTO;
import com.baggio.projeto.masterfinanceapi.entities.Cidade;
import com.baggio.projeto.masterfinanceapi.entities.Estado;
import com.baggio.projeto.masterfinanceapi.repository.CidadeRepository;
import com.baggio.projeto.masterfinanceapi.repository.EstadoRepository;
import com.baggio.projeto.masterfinanceapi.service.exceptions.ResourceNotFoundException;
import com.baggio.projeto.masterfinanceapi.service.generic.GenericService;

@Service
public class CidadeService implements GenericService<Cidade, CidadeDTO, Long> {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	public JpaRepository<Cidade, Long> getRepository() {
		return cidadeRepository;
	}
	
	@Transactional(readOnly = true)
	public Page<CidadeDTO> findByNome(Pageable pageable, String nome) {
		Page<Cidade> page = cidadeRepository.findByNome(pageable, nome);
		return page.map(cidade -> new CidadeDTO(cidade));
	}
	
	@Transactional
	public CidadeDTO insert(CidadeDTO dto) {
		Cidade cidade = new Cidade();
		dtoToEntity(dto, cidade);

		cidade = getRepository().save(cidade);

		return new CidadeDTO(cidade);
	}

	@Transactional
	public CidadeDTO update(CidadeDTO dto, Long id) {
		try {
			Cidade cidade = cidadeRepository.getById(id);
			dtoToEntity(dto, cidade);

			cidade = getRepository().save(cidade);

			return new CidadeDTO(cidade);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	private void dtoToEntity(CidadeDTO cidadeDTO, Cidade cidade) {
		Estado estado = estadoRepository.getById(cidadeDTO.getEstadoId()); 
		
		cidade.setNome(cidadeDTO.getNome());
		cidade.setEstado(estado);
	}


}
