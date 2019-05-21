package br.com.randomthings.services.provider.web;

import br.com.randomthings.dto.ProviderDTO;

public interface ProviderServiceWeb {
	ProviderDTO findByName(String name);
}
