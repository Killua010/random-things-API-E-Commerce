package br.com.randomthings.strategy.client;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StClientValidatePassword implements IStrategy<Client> {
	
	@Override
	public String execute(Client entity) {
		
		if(entity.getUser().getPassword().length() < 6) {
			return "A senha tem que ter no minimo 6 digitos";
		}
		
		return "";
	}



}
