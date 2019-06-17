package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Provider;
import br.com.randomthings.domain.Provider;
import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.Provider;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.provider.StProviderRegisterHelp;
import br.com.randomthings.strategy.standard.StAdminAuthorization;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class ProviderSequence {

	@Autowired
	StProviderRegisterHelp stProviderRegisterHelp;
	
	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StLastUpdate stLastUpdate;
	
	@Autowired
	StAdminAuthorization stAdminAuthorization;
	
	@Bean("SAVE_PROVIDER")
	public Sequence<Provider> saveProvider() {
		return new Sequence<Provider>()
				.add(stProviderRegisterHelp)
				.add(stAdminAuthorization)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_PROVIDER")
	public Sequence<Provider> updateProvider() {
		return new Sequence<Provider>()
				.add(stProviderRegisterHelp)
				.add(stAdminAuthorization)
				.add(stLastUpdate);
	}
	
	@Bean("FIND_PROVIDER")
	public Sequence<Provider> findProvider() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}
	
	@Bean("DELETE_PROVIDER")
	public Sequence<Provider> deleteProvider() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}

}
