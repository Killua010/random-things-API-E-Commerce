package br.com.randomthings.services.card.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.CreditCardFlag;
import br.com.randomthings.dto.CreditCardDTO;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.services.ExecuteStrategys;
import br.com.randomthings.services.card.CreditCardService;
import br.com.randomthings.services.client.ClientService;

@Service
public class CreditCardWebServiceImpl extends ExecuteStrategys<CreditCard> implements CreditCardWebService {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CreditCardService creditCardService;
	
	@Override
	public CreditCard save(CreditCardDTO creditCardDTO, Long idClient) {
		CreditCard creditCard = new CreditCard();
		CreditCardFlag cardFlag = new CreditCardFlag();
		creditCard.setFlag(cardFlag);
		creditCardDTO.fill(creditCard);
		Client client = clientService.findById(idClient);
		creditCard.setClient(client);
		
		StringBuilder errors = runStrategys(creditCard, "Save");
		
		if(errors.length() != 0) {
			throw new StrategyValidation(errors);
		}
		
		return creditCardService.save(creditCard);
	}

	@Override
	public CreditCard update(CreditCardDTO creditCardDTO) {
		CreditCard creditCard = creditCardService.findById(creditCardDTO.getId());
		creditCardDTO.fill(creditCard);
		
		StringBuilder errors = runStrategys(creditCard, "Update");
		
		if(errors.length() != 0) {
			throw new StrategyValidation(errors);
		}
		
		return creditCardService.save(creditCard);
	}

}
