package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Client;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.client.StClientExistenceValidation;
import br.com.randomthings.strategy.client.StClientPasswordEncrypt;
import br.com.randomthings.strategy.client.StClientValidatePassword;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class ClientSequence {

	@Autowired
	StClientPasswordEncrypt stClientPasswordEncrypt;
	
	@Autowired
	StClientValidatePassword stClientValidatePassword;
	
	@Autowired
	StClientExistenceValidation stClientExistenceValidation;
	
	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StLastUpdate stLastUpdate;

	@Bean("SAVE_CLIENT")
	public Sequence<Client> saveClient() {
		return new Sequence<Client>()
				.add(stClientExistenceValidation)
				.add(stClientValidatePassword)
				.add(stClientPasswordEncrypt)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_CLIENT")
	public Sequence<Client> updateClient() {
		return new Sequence<Client>()
				.add(stClientExistenceValidation)
				.add(stClientValidatePassword)
				.add(stClientPasswordEncrypt)
				.add(stLastUpdate);
	}

}
