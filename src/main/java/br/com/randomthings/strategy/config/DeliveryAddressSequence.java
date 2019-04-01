package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.delivery_address.StDeliveryAddressExistenceValidation;
import br.com.randomthings.strategy.delivery_address.StDeliveryAddressRegisterHelp;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class DeliveryAddressSequence {

	@Autowired
	StDeliveryAddressRegisterHelp stDeliveryAddressRegisterHelp;
	
	@Autowired
	StDeliveryAddressExistenceValidation stDeliveryAddressExistenceValidation;
	
	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StLastUpdate stLastUpdate;

	@Bean("SAVE_DELIVERYADDRESS")
	public Sequence<DeliveryAddress> saveDeliveryAddress() {
		return new Sequence<DeliveryAddress>()
				.add(stDeliveryAddressRegisterHelp)
				.add(stDeliveryAddressExistenceValidation)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_DELIVERYADDRESS")
	public Sequence<DeliveryAddress> updateDeliveryAddress() {
		return new Sequence<DeliveryAddress>()
				.add(stDeliveryAddressRegisterHelp)
				.add(stDeliveryAddressExistenceValidation)
				.add(stLastUpdate);
	}

}
