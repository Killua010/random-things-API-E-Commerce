package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.PricingGroup;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.pricing_group.StPricingGroupValidateExistence;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class PricingGroupSequence {

	@Autowired
	StPricingGroupValidateExistence stPricingGroupValidateExistence;
	
	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StLastUpdate stLastUpdate;

	@Bean("SAVE_PRICINGGROUP")
	public Sequence<PricingGroup> savePricingGroup() {
		return new Sequence<PricingGroup>()
				.add(stPricingGroupValidateExistence)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_PRICINGGROUP")
	public Sequence<PricingGroup> updatePricingGroup() {
		return new Sequence<PricingGroup>()
				.add(stPricingGroupValidateExistence)
				.add(stLastUpdate);
	}

}
