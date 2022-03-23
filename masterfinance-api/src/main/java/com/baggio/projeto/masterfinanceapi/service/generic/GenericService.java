package com.baggio.projeto.masterfinanceapi.service.generic;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericService<DTO, ID> {

	List<DTO> findAll();

	Page<DTO> findAllPaged(Pageable pageable);

	DTO findById(ID id);

	DTO insert(DTO dto);

	DTO update(DTO dto, ID id);

	void delete(ID id);
}
