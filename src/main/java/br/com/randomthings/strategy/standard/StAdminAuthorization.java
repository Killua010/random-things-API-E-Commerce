package br.com.randomthings.strategy.standard;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.Role;
import br.com.randomthings.exception.AuthorizationException;
import br.com.randomthings.security.UserSecurity;
import br.com.randomthings.services.user.UserSecurityService;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StAdminAuthorization implements IStrategy<DomainEntity> {

	@Override
	public String execute(DomainEntity entity) {
		UserSecurity userSecurity = UserSecurityService.authenticated();
		if(userSecurity == null || !userSecurity.hasRole(Role.ADMIN)) {
			throw new AuthorizationException("Acesso negado");
		}
		
		return "";
	}

}
