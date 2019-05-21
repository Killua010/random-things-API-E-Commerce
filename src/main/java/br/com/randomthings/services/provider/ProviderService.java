package br.com.randomthings.services.provider;

import br.com.randomthings.domain.Provider;
import br.com.randomthings.services.IService;

public interface ProviderService extends IService<Provider, Long> {
	
	public Provider findByName(String name);
	
}
