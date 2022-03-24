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

import com.baggio.projeto.masterfinanceapi.dto.ContaFinanceiraDTO;
import com.baggio.projeto.masterfinanceapi.service.ContaFinanceiraService;

@RestController
@RequestMapping(value = "/contas/financeiras")
public class ContaFinanceiraController {
	
	@Autowired
	private ContaFinanceiraService contaFinanceiraService;

	@GetMapping(value = "/pagedByDescricao")
	public ResponseEntity<Page<ContaFinanceiraDTO>> findByDescricao(
			Pageable pageable, 
			@RequestParam(value = "descricao", defaultValue = "") String descricao) {
		Page<ContaFinanceiraDTO> listDTO = contaFinanceiraService.findByDescricao(pageable, descricao.trim()); 
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ContaFinanceiraDTO> findById(@PathVariable Long id) {
		ContaFinanceiraDTO contaFinanceiraDTO = contaFinanceiraService.findById(id); 
		return ResponseEntity.ok().body(contaFinanceiraDTO);
	}
	
	@PostMapping
	public ResponseEntity<ContaFinanceiraDTO> insert(@Valid @RequestBody ContaFinanceiraDTO contaFinanceiraDTO) {
		contaFinanceiraDTO = contaFinanceiraService.insert(contaFinanceiraDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contaFinanceiraDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(contaFinanceiraDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ContaFinanceiraDTO> update(@Valid @RequestBody ContaFinanceiraDTO contaFinanceiraDTO, @PathVariable Long id) {
		contaFinanceiraDTO = contaFinanceiraService.update(contaFinanceiraDTO, id);
		return ResponseEntity.ok().body(contaFinanceiraDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		contaFinanceiraService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
