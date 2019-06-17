package br.com.randomthings.strategy.client;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.Role;
import br.com.randomthings.exception.AuthorizationException;
import br.com.randomthings.security.UserSecurity;
import br.com.randomthings.services.user.UserSecurityService;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StClientValidateAuthorization implements IStrategy<Client> {
	
	@Override
	public String execute(Client entity) {
		UserSecurity userSecurity = UserSecurityService.authenticated();
		
		if(userSecurity.hasRole(Role.ADMIN)) {
			return "";
		}
		
		if(entity.getId() != null && !entity.getId().equals(userSecurity.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		return "";
	}



}
