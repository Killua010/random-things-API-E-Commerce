package br.com.randomthings.strategy.credit_card;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.repository.ClientRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StCreditCardExistenceValidation implements IStrategy<CreditCard> {
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public String execute(CreditCard entity) {
		Optional<Client> clientOptional = clientRepository.findByCreditCardNumber(entity.getNumber());
		if(clientOptional.isPresent()) {
			return "Cartão já cadastrado no sistema !";
		}
		
		return "";
	}

}
