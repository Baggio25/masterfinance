package com.baggio.projeto.masterfinanceapi.service.validation.banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.baggio.projeto.masterfinanceapi.controller.exceptions.FieldMessage;
import com.baggio.projeto.masterfinanceapi.dto.BancoDTO;
import com.baggio.projeto.masterfinanceapi.entities.Banco;
import com.baggio.projeto.masterfinanceapi.repository.BancoRepository;

public class BancoValidator implements ConstraintValidator<BancoValid, BancoDTO> {

	private static final String NOME_JA_UTILIZADO = "Esse nome já está sendo utilizado";
	private static final String NUMERO_JA_UTILIZADO = "Esse número já está sendo utilizado";

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private BancoRepository bancoRepository;

	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(BancoDTO dto, ConstraintValidatorContext context) {
		var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		List<FieldMessage> list = new ArrayList<>();

		Banco bancoPorNome = buscaPorNome(dto.getNome());
		Banco bancoPorNumero = buscaPorNumero(dto.getNumero());

		if (!uriVars.isEmpty()) {
			long bancoId = Long.parseLong(uriVars.get("id"));

			if ((bancoPorNome != null) && (bancoId != bancoPorNome.getId())) {
				criaMsgErro(list, "nome", NOME_JA_UTILIZADO);
			}

			if ((bancoPorNumero != null) && (bancoId != bancoPorNumero.getId())) {
				criaMsgErro(list, "numero", NUMERO_JA_UTILIZADO);
			}
		} else {
			if (bancoPorNome != null) {
				criaMsgErro(list, "nome", NOME_JA_UTILIZADO);
			}

			if (bancoPorNumero != null) {
				criaMsgErro(list, "numero", NUMERO_JA_UTILIZADO);
			}
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}

	private Banco buscaPorNome(String nome) {
		return bancoRepository.findByNome(nome);
	}
	
	private Banco buscaPorNumero(String numero) {
		return bancoRepository.findByNumero(numero);
	}

	private void criaMsgErro(List<FieldMessage> list, String field, String msg) {
		list.add(new FieldMessage(field, msg));
	}

}
