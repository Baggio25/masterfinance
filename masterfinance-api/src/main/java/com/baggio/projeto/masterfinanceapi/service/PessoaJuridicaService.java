package com.baggio.projeto.masterfinanceapi.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.projeto.masterfinanceapi.dto.PessoaJuridicaDTO;
import com.baggio.projeto.masterfinanceapi.entities.PessoaJuridica;
import com.baggio.projeto.masterfinanceapi.entities.enums.TipoPessoa;
import com.baggio.projeto.masterfinanceapi.repository.PessoaJuridicaRepository;
import com.baggio.projeto.masterfinanceapi.service.exceptions.ResourceNotFoundException;
import com.baggio.projeto.masterfinanceapi.service.generic.GenericService;

@Service
public class PessoaJuridicaService implements GenericService<PessoaJuridica, PessoaJuridicaDTO, Long> {

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@Override
	public JpaRepository<PessoaJuridica, Long> getRepository() {
		return pessoaJuridicaRepository;
	}

	@Transactional(readOnly = true)
	public Page<PessoaJuridicaDTO> findByNome(Pageable pageable, String nome) {
		Page<PessoaJuridica> page = pessoaJuridicaRepository.findByNome(pageable, nome);
		return page.map(pessoaJuridica -> new PessoaJuridicaDTO(pessoaJuridica));
	}

	@Transactional
	public PessoaJuridicaDTO insert(PessoaJuridicaDTO dto) {
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		dtoToEntity(dto, pessoaJuridica);

		pessoaJuridica.setTipoPessoa(TipoPessoa.JURIDICA);
		pessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);

		return new PessoaJuridicaDTO(pessoaJuridica);
	}

	@Transactional
	public PessoaJuridicaDTO update(PessoaJuridicaDTO dto, Long id) {
		try {
			PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.getById(id);
			dtoToEntity(dto, pessoaJuridica);

			pessoaJuridica.setTipoPessoa(TipoPessoa.JURIDICA);
			pessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);

			return new PessoaJuridicaDTO(pessoaJuridica);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}

	}

	private void dtoToEntity(PessoaJuridicaDTO pessoaJuridicaDTO, PessoaJuridica pessoaJuridica) {
		pessoaJuridica.setNome(pessoaJuridicaDTO.getNome());
		pessoaJuridica.setEmail(pessoaJuridicaDTO.getEmail());
		pessoaJuridica.setCnpj(pessoaJuridicaDTO.getCnpj());
		pessoaJuridica.setTelefone(pessoaJuridicaDTO.getTelefone());
		pessoaJuridica.setCelular(pessoaJuridicaDTO.getCelular());
		pessoaJuridica.setRazaoSocial(pessoaJuridicaDTO.getRazaoSocial());	
	}

}
