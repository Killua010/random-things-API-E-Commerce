package br.com.randomthings.services.client.web;

import br.com.randomthings.domain.Client;
import br.com.randomthings.dto.ClientDTO;
import br.com.randomthings.exception.StrategyValidation;

public interface ClientServiceWeb {
	Client save(ClientDTO clientDTO) throws StrategyValidation;
	
	Client update(ClientDTO clientDTO) throws StrategyValidation;
}
