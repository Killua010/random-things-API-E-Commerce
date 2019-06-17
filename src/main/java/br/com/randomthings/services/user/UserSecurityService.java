package br.com.randomthings.services.user;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.randomthings.security.UserSecurity;

public class UserSecurityService {
	
	public static UserSecurity authenticated() {
		try {
			return (UserSecurity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
	
}
