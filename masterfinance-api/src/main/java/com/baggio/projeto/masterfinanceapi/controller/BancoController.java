package com.baggio.projeto.masterfinanceapi.controller;

import java.net.URI;
import java.util.List;

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

import com.baggio.projeto.masterfinanceapi.dto.BancoDTO;
import com.baggio.projeto.masterfinanceapi.service.BancoService;

@RestController
@RequestMapping(value = "/bancos")
public class BancoController {
	
	@Autowired
	private BancoService bancoService;
	
	@GetMapping
	public ResponseEntity<List<BancoDTO>> findAll() {
		List<BancoDTO> list = bancoService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/paged")
	public ResponseEntity<Page<BancoDTO>> findAllPaged(Pageable pageable) {
		Page<BancoDTO> listDTO = bancoService.findAllPaged(pageable);
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(value = "/pagedByNome")
	public ResponseEntity<Page<BancoDTO>> findByNome(
			Pageable pageable, 
			@RequestParam(value = "nome", defaultValue = "") String nome) {
		Page<BancoDTO> listDTO = bancoService.findByNome(pageable, nome.trim()); 
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<BancoDTO> findById(@PathVariable Long id) {
		BancoDTO bancoDTO = bancoService.findById(id); 
		return ResponseEntity.ok().body(bancoDTO);
	}
	
	@PostMapping
	public ResponseEntity<BancoDTO> insert(@Valid @RequestBody BancoDTO bancoDTO) {
		bancoDTO = bancoService.insert(bancoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bancoDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(bancoDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<BancoDTO> update(@Valid @RequestBody BancoDTO bancoDTO, @PathVariable Long id) {
		bancoDTO = bancoService.update(bancoDTO, id);
		return ResponseEntity.ok().body(bancoDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		bancoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
