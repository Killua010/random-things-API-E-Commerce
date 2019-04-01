package br.com.randomthings.strategy.client;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StClientValidatePassword implements IStrategy<Client> {
	
	@Override
	public String execute(Client entity) {
		
		String password = entity.getUser().getPassword();
		StringBuilder message = new StringBuilder();
		
		boolean findNumber = false;
	    boolean findLetter = false;
	    
	    for (char c : password.toCharArray()) {
	         if (c >= '0' && c <= '9') {
	        	 findNumber = true;
	         } else if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
	        	 findLetter = true;
	         } 
	    }
		
		if(password.length() < 8) {
			message.append("A senha tem que ter mais de 8 caracteres");
		}
		
		if(!findNumber) {
			message.append("A senha tem que ter no minimo um numero");
		}
		
		if(!findLetter) {
			message.append("A senha tem que ter no minimo uma letra");
		}
		
		return message.toString();
	}



}
