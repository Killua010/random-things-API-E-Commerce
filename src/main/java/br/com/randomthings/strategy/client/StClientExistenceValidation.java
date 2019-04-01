package br.com.randomthings.strategy.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.repository.ClientRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StClientExistenceValidation implements IStrategy<Client> {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public String execute(Client entity) {
		Optional<Client> clientCPF = clientRepository.findByCpf(entity.getCpf());
		
		if(clientCPF.isPresent()) {
			if(!clientCPF.get().getId().equals(entity.getId())) {
				return "Cliente com CPF: " + entity.getCpf() + " já cadastrada no sistema";
			}
		}
		
		Optional<Client> clientEmail = clientRepository.findByEmail(entity.getUser().getEmail());
		
		if(clientEmail.isPresent()) {
			if(!clientEmail.get().getId().equals(entity.getId())) {
				return "Cliente com email: " + entity.getUser().getEmail() + " já cadastrada no sistema";
			}
		}
		
		return "";
	}



}
