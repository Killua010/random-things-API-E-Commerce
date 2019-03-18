package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Activation;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.activation.StActivationValidateProduct;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class ActivationSequence {

	@Autowired
	StActivationValidateProduct stActivationValidateProduct;
	
	@Autowired
	StRegistration stRegistration;

	@Bean("SAVE_ACTIVATION")
	public Sequence<Activation> saveCategory() {
		return new Sequence<Activation>()
				.add(stActivationValidateProduct)
				.add(stRegistration);
	}


}
