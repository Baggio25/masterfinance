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

import com.baggio.projeto.masterfinanceapi.dto.CidadeDTO;
import com.baggio.projeto.masterfinanceapi.service.CidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping(value = "/pagedByNome")
	public ResponseEntity<Page<CidadeDTO>> findByNome(
			Pageable pageable, 
			@RequestParam(value = "nome", defaultValue = "") String nome) {
		Page<CidadeDTO> listDTO = cidadeService.findByNome(pageable, nome.trim()); 
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CidadeDTO> findById(@PathVariable Long id) {
		CidadeDTO cidadeDTO = cidadeService.findById(id); 
		return ResponseEntity.ok().body(cidadeDTO);
	}
	
	@PostMapping
	public ResponseEntity<CidadeDTO> insert(@Valid @RequestBody CidadeDTO cidadeDTO) {
		cidadeDTO = cidadeService.insert(cidadeDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cidadeDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(cidadeDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CidadeDTO> update(@Valid @RequestBody CidadeDTO cidadeDTO, @PathVariable Long id) {
		cidadeDTO = cidadeService.update(cidadeDTO, id);
		return ResponseEntity.ok().body(cidadeDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		cidadeService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
