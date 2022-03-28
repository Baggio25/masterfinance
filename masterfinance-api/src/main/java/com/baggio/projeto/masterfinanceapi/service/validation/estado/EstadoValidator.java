package com.baggio.projeto.masterfinanceapi.service.validation.estado;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.baggio.projeto.masterfinanceapi.controller.exceptions.FieldMessage;
import com.baggio.projeto.masterfinanceapi.dto.EstadoDTO;
import com.baggio.projeto.masterfinanceapi.entities.Estado;
import com.baggio.projeto.masterfinanceapi.repository.EstadoRepository;

public class EstadoValidator implements ConstraintValidator<EstadoValid, EstadoDTO> {

	private static final String NOME_JA_UTILIZADO = "Esse nome já está sendo utilizado";

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private EstadoRepository estadoRepository;

	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(EstadoDTO dto, ConstraintValidatorContext context) {
		var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		List<FieldMessage> list = new ArrayList<>();

		Estado estadoPorNome = buscaPorNome(dto.getNome());

		if (!uriVars.isEmpty()) {
			long bancoId = Long.parseLong(uriVars.get("id"));

			if ((estadoPorNome != null) && (bancoId != estadoPorNome.getId())) {
				criaMsgErro(list, "nome", NOME_JA_UTILIZADO);
			}

		} else {
			if (estadoPorNome != null) {
				criaMsgErro(list, "nome", NOME_JA_UTILIZADO);
			}

		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}

	private Estado buscaPorNome(String nome) {
		return estadoRepository.findByNome(nome);
	}

	private void criaMsgErro(List<FieldMessage> list, String field, String msg) {
		list.add(new FieldMessage(field, msg));
	}

}
