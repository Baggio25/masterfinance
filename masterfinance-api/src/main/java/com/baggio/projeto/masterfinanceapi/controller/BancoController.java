package com.baggio.projeto.masterfinanceapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	
}
