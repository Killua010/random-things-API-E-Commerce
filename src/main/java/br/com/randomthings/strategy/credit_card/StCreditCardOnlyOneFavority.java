package br.com.randomthings.strategy.credit_card;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.repository.ClientRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StCreditCardOnlyOneFavority implements IStrategy<CreditCard> {
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public String execute(CreditCard entity) {
		Optional<Client> clientOptional = clientRepository.findById(entity.getClient().getId());
		if(clientOptional.isPresent()) {
			Boolean favoriy = false;
			for(CreditCard card: clientOptional.get().getCards()) {
				if(card.getFavorite() == true) {
					favoriy = true;
				}
				if(favoriy == true && entity.getFavorite() == true) {
					return "Já existe um cartão favorito no sistema !";
				}
			}
		}
		return "";
	}

}
