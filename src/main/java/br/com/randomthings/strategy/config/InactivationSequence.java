package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Activation;
import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.Inactivation;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.inactivation.StInactivationValidateProduct;
import br.com.randomthings.strategy.standard.StAdminAuthorization;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class InactivationSequence {

	@Autowired
	StInactivationValidateProduct stInactivationValidateProduct;
	
	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StAdminAuthorization stAdminAuthorization;

	@Bean("SAVE_INACTIVATION")
	public Sequence<Inactivation> saveInactivation() {
		return new Sequence<Inactivation>()
				.add(stInactivationValidateProduct)
				.add(stAdminAuthorization)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_INACTIVATION")
	public Sequence<Activation> updateInactivation() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}
	
	@Bean("FIND_INACTIVATION")
	public Sequence<Activation> findInactivation() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}
	
	@Bean("DELETE_INACTIVATION")
	public Sequence<Activation> deleteInactivation() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}


}
