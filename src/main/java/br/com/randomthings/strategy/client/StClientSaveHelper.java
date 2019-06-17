package br.com.randomthings.strategy.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.Role;
import br.com.randomthings.repository.ClientRepository;
import br.com.randomthings.strategy.IStrategy;
import br.com.randomthings.strategy.standard.StRegistration;

@Service
public class StClientSaveHelper implements IStrategy<Client> {
	
	@Autowired
	StRegistration stRegistration;
	
	@Override
	public String execute(Client entity) {
		entity.getUser().addRole(Role.CLIENT);
		stRegistration.execute(entity.getUser());
		return "";
	}



}
