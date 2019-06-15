package br.com.randomthings.services.address.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.City;
import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.domain.ResidenceType;
import br.com.randomthings.dto.DeliveryAddressDTO;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.services.ExecuteStrategys;
import br.com.randomthings.services.address.DeliveryAddressService;
import br.com.randomthings.services.client.ClientService;
import br.com.randomthings.strategy.delivery_address.StDeliveryAddressOnlyOneFavority;

@Service
public class DeliveryAddressWebImpl extends ExecuteStrategys<DeliveryAddress> implements DeliveryAddressWebService {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private StDeliveryAddressOnlyOneFavority stDeliveryAddressOnlyOneFavority;
	
	@Autowired
	private DeliveryAddressService deliveryAddressService;
	
	@Override
	public DeliveryAddress save(DeliveryAddressDTO addressDTO, Long idClient) {
		DeliveryAddress deliveryAddress = addressDTO.fill();
		
		Client client = clientService.findById(idClient);
		client.getAddresses().add(deliveryAddress);
		deliveryAddress.setClient(client);
		
		StringBuilder errors = runStrategys(deliveryAddress, "SAVE");
		
		errors.append(stDeliveryAddressOnlyOneFavority.execute(client, deliveryAddress));
		
		if(errors.length() != 0) {
			throw new StrategyValidation(errors);
		}
		
		clientService.save(client);
		return deliveryAddressService.save(deliveryAddress);
	}

	
	
	@Override
	public DeliveryAddress update(@Valid DeliveryAddressDTO deliveryAddressDTO, Long idClient) {
		DeliveryAddress deliveryAddress = deliveryAddressDTO.fill(deliveryAddressDTO.getId());
		
		Client client = clientService.findById(idClient);
		
		deliveryAddress.setClient(client);
		StringBuilder errors = runStrategys(deliveryAddress, "Update");
		
		if(errors.length() != 0) {
			throw new StrategyValidation(errors);
		}
		
		return deliveryAddressService.save(deliveryAddress);
	}

}
