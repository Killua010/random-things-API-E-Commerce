package br.com.randomthings.services.client.web;

import br.com.randomthings.domain.User;
import br.com.randomthings.dto.ClientDTO;

public interface ClientServiceWeb {
	ClientDTO findByUser(User user);
}
