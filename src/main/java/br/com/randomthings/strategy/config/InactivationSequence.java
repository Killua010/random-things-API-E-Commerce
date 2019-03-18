package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Activation;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.inactivation.StInactivationValidateProduct;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class InactivationSequence {

	@Autowired
	StInactivationValidateProduct stInactivationValidateProduct;
	
	@Autowired
	StRegistration stRegistration;

	@Bean("SAVE_INACTIVATION")
	public Sequence<Activation> saveCategory() {
		return new Sequence<Activation>()
				.add(stInactivationValidateProduct)
				.add(stRegistration);
	}


}
