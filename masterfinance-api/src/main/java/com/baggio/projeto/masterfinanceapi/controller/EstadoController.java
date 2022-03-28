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

import com.baggio.projeto.masterfinanceapi.dto.EstadoDTO;
import com.baggio.projeto.masterfinanceapi.service.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<EstadoDTO> list = estadoService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/paged")
	public ResponseEntity<Page<EstadoDTO>> findAllPaged(Pageable pageable) {
		Page<EstadoDTO> listDTO = estadoService.findAllPaged(pageable);
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(value = "/pagedByNome")
	public ResponseEntity<Page<EstadoDTO>> findByNome(
			Pageable pageable, 
			@RequestParam(value = "nome", defaultValue = "") String nome) {
		Page<EstadoDTO> listDTO = estadoService.findByNome(pageable, nome.trim()); 
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EstadoDTO> findById(@PathVariable Long id) {
		EstadoDTO estadoDTO = estadoService.findById(id); 
		return ResponseEntity.ok().body(estadoDTO);
	}
	
	@PostMapping
	public ResponseEntity<EstadoDTO> insert(@Valid @RequestBody EstadoDTO estadoDTO) {
		estadoDTO = estadoService.insert(estadoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(estadoDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(estadoDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EstadoDTO> update(@Valid @RequestBody EstadoDTO estadoDTO, @PathVariable Long id) {
		estadoDTO = estadoService.update(estadoDTO, id);
		return ResponseEntity.ok().body(estadoDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		estadoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
