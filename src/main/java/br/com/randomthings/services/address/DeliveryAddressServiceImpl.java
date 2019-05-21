package br.com.randomthings.services.address;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.repository.DeliveryAddressRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class DeliveryAddressServiceImpl extends AbstractService<DeliveryAddress, Long> implements DeliveryAddressService{

	public DeliveryAddressServiceImpl(DeliveryAddressRepository dao) {
		super(dao);
	}

}
