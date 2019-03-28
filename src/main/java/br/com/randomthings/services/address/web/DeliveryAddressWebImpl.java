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

@Service
public class DeliveryAddressWebImpl extends ExecuteStrategys<DeliveryAddress> implements DeliveryAddressWebService {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private DeliveryAddressService deliveryAddressService;
	
	@Override
	public DeliveryAddress save(DeliveryAddressDTO addressDTO, Long idClient) {
		DeliveryAddress deliveryAddress = new DeliveryAddress();
		City city = new City();
		ResidenceType residenceType = new ResidenceType();
		deliveryAddress.setResidenceType(residenceType);
		deliveryAddress.setCity(city);
		addressDTO.fill(deliveryAddress);
		Client client = clientService.findById(idClient);
		client.getAddresses().add(deliveryAddress);
		deliveryAddress.setClient(client);
		runStrategys(deliveryAddress, "SAVE");
		clientService.save(client);
		return deliveryAddressService.save(deliveryAddress);
	}

	
	
	@Override
	public DeliveryAddress update(@Valid DeliveryAddressDTO deliveryAddressDTO) {
		DeliveryAddress deliveryAddress = deliveryAddressService.findById(deliveryAddressDTO.getId());
		deliveryAddressDTO.fill(deliveryAddress);
		
		StringBuilder errors = runStrategys(deliveryAddress, "Update");
		
		if(errors.length() != 0) {
			throw new StrategyValidation(errors);
		}
		
		return deliveryAddressService.save(deliveryAddress);
	}

}
