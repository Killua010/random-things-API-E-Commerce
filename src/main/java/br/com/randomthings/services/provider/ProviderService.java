package br.com.randomthings.services.provider;

import java.util.List;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.Provider;
import br.com.randomthings.services.IService;

public interface ProviderService extends IService<Provider, Long> {
	
	public Provider findByName(String name);
	
	public List<Provider> findByCategory(Category category);
	
}
