package com.baggio.projeto.masterfinanceapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baggio.projeto.masterfinanceapi.dto.PessoaDTO;
import com.baggio.projeto.masterfinanceapi.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")	
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping(value = "/pagedByDescricao")
	public ResponseEntity<Page<PessoaDTO>> findByDescricao(
			Pageable pageable, 
			@RequestParam(value = "nome", defaultValue = "") String nome) {
		Page<PessoaDTO> listDTO = pessoaService.findByNome(pageable, nome.trim()); 
		return ResponseEntity.ok().body(listDTO);
	}
		
}
