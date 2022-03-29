package com.baggio.projeto.masterfinanceapi.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.baggio.projeto.masterfinanceapi.dto.PessoaJuridicaDTO;
import com.baggio.projeto.masterfinanceapi.service.PessoaJuridicaService;

@RestController
@RequestMapping(value = "/pessoas/juridicas")
public class PessoaJuridicaController {
	
	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;

	@GetMapping(value = "/pagedByNome")
	public ResponseEntity<Page<PessoaJuridicaDTO>> findByNome(
			Pageable pageable, 
			@RequestParam(value = "nome", defaultValue = "") String nome) {
		Page<PessoaJuridicaDTO> listDTO = pessoaJuridicaService.findByNome(pageable, nome.trim()); 
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaJuridicaDTO> findById(@PathVariable Long id) {
		PessoaJuridicaDTO pessoaJuridicaDTO = pessoaJuridicaService.findById(id); 
		return ResponseEntity.ok().body(pessoaJuridicaDTO);
	}
	
	@PostMapping
	public ResponseEntity<PessoaJuridicaDTO> insert(@Valid @RequestBody PessoaJuridicaDTO pessoaJuridicaDTO) {
		pessoaJuridicaDTO = pessoaJuridicaService.insert(pessoaJuridicaDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoaJuridicaDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(pessoaJuridicaDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PessoaJuridicaDTO> update(@Valid @RequestBody PessoaJuridicaDTO pessoaJuridicaDTO, @PathVariable Long id) {
		pessoaJuridicaDTO = pessoaJuridicaService.update(pessoaJuridicaDTO, id);
		return ResponseEntity.ok().body(pessoaJuridicaDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pessoaJuridicaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
