package br.com.randomthings.services.provider.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Provider;
import br.com.randomthings.dto.ProviderDTO;
import br.com.randomthings.services.ExecuteStrategys;
import br.com.randomthings.services.provider.ProviderService;

@Service
public class ProviderServiceWebImpl extends ExecuteStrategys<Provider> implements ProviderServiceWeb {
	
	@Autowired
	private ProviderService providerService;
	
	@Override
	public ProviderDTO findByName(String name) {
		return (ProviderDTO) new ProviderDTO().from(providerService.findByName(name));
	}

}
