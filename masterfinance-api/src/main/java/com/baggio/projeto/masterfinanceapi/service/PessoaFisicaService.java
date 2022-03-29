package com.baggio.projeto.masterfinanceapi.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.projeto.masterfinanceapi.dto.PessoaFisicaDTO;
import com.baggio.projeto.masterfinanceapi.entities.PessoaFisica;
import com.baggio.projeto.masterfinanceapi.entities.enums.TipoPessoa;
import com.baggio.projeto.masterfinanceapi.repository.PessoaFisicaRepository;
import com.baggio.projeto.masterfinanceapi.service.exceptions.ResourceNotFoundException;
import com.baggio.projeto.masterfinanceapi.service.generic.GenericService;

@Service
public class PessoaFisicaService implements GenericService<PessoaFisica, PessoaFisicaDTO, Long> {

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Override
	public JpaRepository<PessoaFisica, Long> getRepository() {
		return pessoaFisicaRepository;
	}

	@Transactional(readOnly = true)
	public Page<PessoaFisicaDTO> findByNome(Pageable pageable, String nome) {
		Page<PessoaFisica> page = pessoaFisicaRepository.findByNome(pageable, nome);
		return page.map(pessoaFisica -> new PessoaFisicaDTO(pessoaFisica));
	}

	@Transactional
	public PessoaFisicaDTO insert(PessoaFisicaDTO dto) {
		PessoaFisica pessoaFisica = new PessoaFisica();
		dtoToEntity(dto, pessoaFisica);

		pessoaFisica.setTipoPessoa(TipoPessoa.FISICA);
		pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);

		return new PessoaFisicaDTO(pessoaFisica);
	}

	@Transactional
	public PessoaFisicaDTO update(PessoaFisicaDTO dto, Long id) {
		try {
			PessoaFisica pessoaFisica = pessoaFisicaRepository.getById(id);
			dtoToEntity(dto, pessoaFisica);

			pessoaFisica.setTipoPessoa(TipoPessoa.FISICA);
			pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);

			return new PessoaFisicaDTO(pessoaFisica);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}

	}

	private void dtoToEntity(PessoaFisicaDTO pessoaFisicaDTO, PessoaFisica pessoaFisica) {
		pessoaFisica.setNome(pessoaFisicaDTO.getNome());
		pessoaFisica.setEmail(pessoaFisicaDTO.getEmail());
		pessoaFisica.setCpf(pessoaFisicaDTO.getCpf());
		pessoaFisica.setTelefone(pessoaFisicaDTO.getTelefone());
		pessoaFisica.setCelular(pessoaFisicaDTO.getCelular());
		pessoaFisica.setDataNascimento(pessoaFisicaDTO.getDataNascimento());	
	}

}
