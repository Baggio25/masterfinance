package com.baggio.projeto.masterfinanceapi.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.projeto.masterfinanceapi.dto.EnderecoDTO;
import com.baggio.projeto.masterfinanceapi.entities.Cidade;
import com.baggio.projeto.masterfinanceapi.entities.Endereco;
import com.baggio.projeto.masterfinanceapi.repository.CidadeRepository;
import com.baggio.projeto.masterfinanceapi.repository.EnderecoRepository;
import com.baggio.projeto.masterfinanceapi.service.exceptions.ResourceNotFoundException;
import com.baggio.projeto.masterfinanceapi.service.generic.GenericService;

@Service
public class EnderecoService implements GenericService<Endereco, EnderecoDTO, Long> {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public JpaRepository<Endereco, Long> getRepository() {
		return enderecoRepository;
	}

	@Transactional(readOnly = true)
	public Page<EnderecoDTO> findByCep(Pageable pageable, String cep) {
		Page<Endereco> page = enderecoRepository.findByCep(pageable, cep);
		return page.map(endereco -> new EnderecoDTO(endereco));
	}

	@Transactional
	public EnderecoDTO insert(EnderecoDTO dto) {
		Endereco endereco = new Endereco();
		dtoToEntity(dto, endereco);

		endereco = getRepository().save(endereco);

		return new EnderecoDTO(endereco);
	}

	@Transactional
	public EnderecoDTO update(EnderecoDTO dto, Long id) {
		try {
			Endereco endereco = enderecoRepository.getById(id);
			dtoToEntity(dto, endereco);

			endereco = getRepository().save(endereco);

			return new EnderecoDTO(endereco);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	private void dtoToEntity(EnderecoDTO enderecoDTO, Endereco endereco) {
		Cidade cidade = cidadeRepository.getById(enderecoDTO.getCidadeId());

		endereco.setRua(enderecoDTO.getRua());
		endereco.setCep(enderecoDTO.getCep());
		endereco.setBairro(enderecoDTO.getBairro());
		endereco.setCidade(cidade);

	}

}
