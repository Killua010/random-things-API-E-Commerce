package br.com.randomthings.strategy.delivery_address;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.repository.ClientRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StDeliveryAddressOnlyOneFavority {

	@Autowired
	private ClientRepository clientRepository;
	
	public String execute(Client entity, DeliveryAddress address) {
		
		Optional<Client> clientOptional = clientRepository.findById(entity.getId());
		if(clientOptional.isPresent()) {
			Boolean favoriy = false;
			for(DeliveryAddress addr: clientOptional.get().getAddresses()) {
				if(addr.getFavorite() == true && !addr.equals(address)) {
					favoriy = true;
				}
				if(favoriy == true && address.getFavorite() == true) {
					return "Já existe um endereço favorito no sistema !";
				}
			}
		}
		
		return "";
	}

}
