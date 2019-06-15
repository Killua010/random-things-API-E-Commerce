package br.com.randomthings.strategy.delivery_address;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.repository.ClientRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StDeliveryAddressExistenceValidation implements IStrategy<DeliveryAddress>{

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public String execute(DeliveryAddress entity) {
		Optional<DeliveryAddress> addressOptional = clientRepository.findByZipCodeAndNumberAddress(entity.getZipCode(), entity.getNumber());
		
		if(addressOptional.isPresent() && entity.getId() != addressOptional.get().getId()) {
			return "Endereço já cadastrado";
		}
		
		return "";
	}

}
