package br.com.randomthings.services.address.web;

import javax.validation.Valid;

import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.dto.DeliveryAddressDTO;

public interface DeliveryAddressWebService {
	DeliveryAddress save(DeliveryAddressDTO addressDTO, Long idClient);

	DeliveryAddress update(@Valid DeliveryAddressDTO deliveryAddressDTO);
}
