package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.State;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.standard.StAdminAuthorization;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class StateSequence {

	@Autowired
	StAdminAuthorization stAdminAuthorization;
	
	@Autowired
	StRegistration stRegistration;

	@Bean("SAVE_STATE")
	public Sequence<State> saveState() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_STATE")
	public Sequence<State> updateState() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}
	
	@Bean("DELETE_STATE")
	public Sequence<State> deleteState() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}


}
