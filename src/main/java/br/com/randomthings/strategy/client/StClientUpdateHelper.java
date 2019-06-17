package br.com.randomthings.strategy.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.repository.ClientRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StClientUpdateHelper implements IStrategy<Client> {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public String execute(Client entity) {
		Optional<Client> client = clientRepository.findById(entity.getId());
		
		if(!client.isPresent()) {
			return "Cliente Inexistende";
		}
		
		client.get().getUser().setEmail(entity.getUser().getEmail());
		client.get().getUser().setPassword(entity.getUser().getPassword());
		entity.setUser(client.get().getUser());
		
		return "";
	}



}
