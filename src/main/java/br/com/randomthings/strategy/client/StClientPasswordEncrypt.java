package br.com.randomthings.strategy.client;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.strategy.IStrategy;
import br.com.randomthings.utils.SystemVariable;

@Service
public class StClientPasswordEncrypt implements IStrategy<Client> {
	
	@Override
	public String execute(Client entity) {
		String newPassword = SystemVariable.encrypt(entity.getUser().getPassword());
		entity.getUser().setPassword(newPassword);
		return "";
	}



}
