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

import com.baggio.projeto.masterfinanceapi.dto.EnderecoDTO;
import com.baggio.projeto.masterfinanceapi.service.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping(value = "/pagedByCep")
	public ResponseEntity<Page<EnderecoDTO>> findByCep(
			Pageable pageable, 
			@RequestParam(value = "cep", defaultValue = "") String cep) {
		Page<EnderecoDTO> listDTO = enderecoService.findByCep(pageable, cep); 
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/pagedByPessoa/{idPessoa}")
	public ResponseEntity<Page<EnderecoDTO>> findByPessoa(Pageable pageable, @PathVariable Long idPessoa) {
		Page<EnderecoDTO> listDTO = enderecoService.findByPessoa(pageable, idPessoa); 
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id) {
		EnderecoDTO enderecoDTO = enderecoService.findById(id); 
		return ResponseEntity.ok().body(enderecoDTO);
	}
	
	@PostMapping
	public ResponseEntity<EnderecoDTO> insert(@Valid @RequestBody EnderecoDTO enderecoDTO) {
		enderecoDTO = enderecoService.insert(enderecoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(enderecoDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(enderecoDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EnderecoDTO> update(@Valid @RequestBody EnderecoDTO enderecoDTO, @PathVariable Long id) {
		enderecoDTO = enderecoService.update(enderecoDTO, id);
		return ResponseEntity.ok().body(enderecoDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		enderecoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
