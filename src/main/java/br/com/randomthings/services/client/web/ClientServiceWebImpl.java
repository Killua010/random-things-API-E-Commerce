package br.com.randomthings.services.client.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.Contact;
import br.com.randomthings.domain.User;
import br.com.randomthings.dto.ClientDTO;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.facade.Facade;
import br.com.randomthings.services.client.ClientService;

@Service
public class ClientServiceWebImpl extends Facade<Client> implements ClientServiceWeb {
	
	@Autowired
	private ClientService clientService;
	
	@Override
	public Client save(ClientDTO clientDTO) {
		Client client = new Client();
		User user = new User();
		Contact contact = new Contact();
		
		client.setUser(user);
		client.setContact(contact);
		clientDTO.fill(client);
		
		StringBuilder errors = runStrategys(client, "Save");
		
		if(errors.length() != 0) {
			throw new StrategyValidation(errors);
		}
		
		return clientService.save(client);
	}

	@Override
	public Client update(ClientDTO clientDTO) throws StrategyValidation {
		Client client = clientService.findById(clientDTO.getId());
		System.err.println(client);
		System.err.println("oi");
		clientDTO.fill(client);
		
		StringBuilder errors = runStrategys(client, "Update");
		
		if(errors.length() != 0) {
			throw new StrategyValidation(errors);
		}
		
		return clientService.save(client);
	}

}
