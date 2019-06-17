package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Activation;
import br.com.randomthings.domain.Activation;
import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.activation.StActivationValidateProduct;
import br.com.randomthings.strategy.standard.StAdminAuthorization;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class ActivationSequence {

	@Autowired
	StActivationValidateProduct stActivationValidateProduct;
	
	@Autowired
	StAdminAuthorization stAdminAuthorization;
	
	@Autowired
	StRegistration stRegistration;

	@Bean("SAVE_ACTIVATION")
	public Sequence<Activation> saveActivation() {
		return new Sequence<Activation>()
				.add(stActivationValidateProduct)
				.add(stAdminAuthorization)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_ACTIVATION")
	public Sequence<Activation> updateActivation() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}
	
	@Bean("FIND_ACTIVATION")
	public Sequence<Activation> findActivation() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}
	
	@Bean("DELETE_ACTIVATION")
	public Sequence<Activation> deleteActivation() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}


}
