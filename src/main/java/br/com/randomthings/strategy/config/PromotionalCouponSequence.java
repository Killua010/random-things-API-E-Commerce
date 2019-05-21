package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class PromotionalCouponSequence {

	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StLastUpdate stLastUpdate;
	
	@Bean("SAVE_PROMOTIONALCOUPON")
	public Sequence<Category> saveCategory() {
		return new Sequence<DomainEntity>()
				.add(stRegistration);
	}
	
	@Bean("UPDATE_PROMOTIONALCOUPON")
	public Sequence<Category> updateCategory() {
		return new Sequence<DomainEntity>()
				.add(stLastUpdate);
	}

}
