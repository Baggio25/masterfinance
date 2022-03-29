package com.baggio.projeto.masterfinanceapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baggio.projeto.masterfinanceapi.dto.ContaDTO;
import com.baggio.projeto.masterfinanceapi.service.ContaService;

@RestController
@RequestMapping(value = "/contas")	
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	@GetMapping(value = "/pagedByDescricao")
	public ResponseEntity<Page<ContaDTO>> findByDescricao(
			Pageable pageable, 
			@RequestParam(value = "descricao", defaultValue = "") String descricao) {
		Page<ContaDTO> listDTO = contaService.findByDescricao(pageable, descricao.trim()); 
		return ResponseEntity.ok().body(listDTO);
	}
		
}
