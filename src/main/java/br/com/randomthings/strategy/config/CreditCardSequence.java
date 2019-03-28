package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.credit_card.StCreditCardRegisterHelp;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class CreditCardSequence {

	@Autowired
	StCreditCardRegisterHelp stCreditCardRegisterHelp;
	
	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StLastUpdate stLastUpdate;

	@Bean("SAVE_CREDITCARD")
	public Sequence<CreditCard> saveCreditCard() {
		return new Sequence<CreditCard>()
				.add(stCreditCardRegisterHelp)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_CREDITCARD")
	public Sequence<CreditCard> updateCreditCard() {
		return new Sequence<CreditCard>()
				.add(stCreditCardRegisterHelp)
				.add(stLastUpdate);
	}

}
