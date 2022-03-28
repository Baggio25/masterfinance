package com.baggio.projeto.masterfinanceapi.service.generic;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.projeto.masterfinanceapi.service.exceptions.DatabaseException;
import com.baggio.projeto.masterfinanceapi.service.exceptions.ResourceNotFoundException;
import com.baggio.projeto.masterfinanceapi.service.util.Convertible;

public interface GenericService<T extends Convertible<DTO>, DTO, ID> {
	
	JpaRepository<T, ID> getRepository();

	@Transactional(readOnly = true)
	default List<DTO> findAll() {
		List<T> list = getRepository().findAll();
		return list.stream().map(x -> x.convert()).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	default DTO findById(ID id) {
		Optional<T> optional = getRepository().findById(id);
		T t = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return t.convert();
	}
	
	@Transactional(readOnly = true)
	default Page<DTO> findAllPaged(Pageable pageable) {
		Page<T> page = getRepository().findAll(pageable);
		return page.map(banco -> banco.convert());
	}
	
	default void delete(ID id) {
		try {
			getRepository().deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}

	}	
}
