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

import com.baggio.projeto.masterfinanceapi.dto.PessoaFisicaDTO;
import com.baggio.projeto.masterfinanceapi.service.PessoaFisicaService;

@RestController
@RequestMapping(value = "/pessoas/fisicas")
public class PessoaFisicaController {
	
	@Autowired
	private PessoaFisicaService pessoaFisicaService;

	@GetMapping(value = "/pagedByNome")
	public ResponseEntity<Page<PessoaFisicaDTO>> findByNome(
			Pageable pageable, 
			@RequestParam(value = "nome", defaultValue = "") String nome) {
		Page<PessoaFisicaDTO> listDTO = pessoaFisicaService.findByNome(pageable, nome.trim()); 
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaFisicaDTO> findById(@PathVariable Long id) {
		PessoaFisicaDTO pessoaFisicaDTO = pessoaFisicaService.findById(id); 
		return ResponseEntity.ok().body(pessoaFisicaDTO);
	}
	
	@PostMapping
	public ResponseEntity<PessoaFisicaDTO> insert(@Valid @RequestBody PessoaFisicaDTO pessoaFisicaDTO) {
		pessoaFisicaDTO = pessoaFisicaService.insert(pessoaFisicaDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoaFisicaDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(pessoaFisicaDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PessoaFisicaDTO> update(@Valid @RequestBody PessoaFisicaDTO pessoaFisicaDTO, @PathVariable Long id) {
		pessoaFisicaDTO = pessoaFisicaService.update(pessoaFisicaDTO, id);
		return ResponseEntity.ok().body(pessoaFisicaDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pessoaFisicaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
