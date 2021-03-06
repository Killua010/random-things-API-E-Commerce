package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Activation;
import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.standard.StAdminAuthorization;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class PromotionalCouponSequence {

	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StLastUpdate stLastUpdate;
	
	@Autowired
	StAdminAuthorization stAdminAuthorization;
	
	@Bean("SAVE_PROMOTIONALCOUPON")
	public Sequence<Category> savePromotionalCoupon() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_PROMOTIONALCOUPON")
	public Sequence<Category> updatePromotionalCoupon() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization)
				.add(stLastUpdate);
	}
	
	@Bean("DELETE_PROMOTIONALCOUPON")
	public Sequence<Activation> deletePromotionalCoupon() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}

}
