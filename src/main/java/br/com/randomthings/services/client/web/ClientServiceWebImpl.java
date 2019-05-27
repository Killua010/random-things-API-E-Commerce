package br.com.randomthings.services.client.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.User;
import br.com.randomthings.dto.ClientDTO;
import br.com.randomthings.services.client.ClientService;
import br.com.randomthings.utils.SystemVariable;

@Service
public class ClientServiceWebImpl implements ClientServiceWeb {

	@Autowired
	private ClientService clientService;
	
	@Override
	public ClientDTO findByUser(User user) {
		String password = SystemVariable.encrypt(user.getPassword());
		user.setPassword(password);
		
		Client client = clientService.findByUser(user);
		
		return (ClientDTO) new ClientDTO().from(client);
	}

}
