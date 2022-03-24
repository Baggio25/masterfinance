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

import com.baggio.projeto.masterfinanceapi.dto.ContaBancariaDTO;
import com.baggio.projeto.masterfinanceapi.service.ContaBancariaService;

@RestController
@RequestMapping(value = "/contas/bancarias")
public class ContaBancariaController {
	
	@Autowired
	private ContaBancariaService contaBancariaService;
	
	@GetMapping(value = "/pagedByDescricao")
	public ResponseEntity<Page<ContaBancariaDTO>> findByDescricao(
			Pageable pageable, 
			@RequestParam(value = "descricao", defaultValue = "") String descricao) {
		Page<ContaBancariaDTO> listDTO = contaBancariaService.findByDescricao(pageable, descricao.trim()); 
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ContaBancariaDTO> findById(@PathVariable Long id) {
		ContaBancariaDTO contaBancariaDTO = contaBancariaService.findById(id); 
		return ResponseEntity.ok().body(contaBancariaDTO);
	}
	
	@PostMapping
	public ResponseEntity<ContaBancariaDTO> insert(@Valid @RequestBody ContaBancariaDTO contaBancariaDTO) {
		contaBancariaDTO = contaBancariaService.insert(contaBancariaDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contaBancariaDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(contaBancariaDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ContaBancariaDTO> update(@Valid @RequestBody ContaBancariaDTO contaBancariaDTO, @PathVariable Long id) {
		contaBancariaDTO = contaBancariaService.update(contaBancariaDTO, id);
		return ResponseEntity.ok().body(contaBancariaDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		contaBancariaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
