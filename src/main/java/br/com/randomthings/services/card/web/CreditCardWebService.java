package br.com.randomthings.services.card.web;

import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.dto.CreditCardDTO;

public interface CreditCardWebService {
	CreditCard save(CreditCardDTO creditCardDTO, Long idClient);

	CreditCard update(CreditCardDTO creditCardDTO);
}
