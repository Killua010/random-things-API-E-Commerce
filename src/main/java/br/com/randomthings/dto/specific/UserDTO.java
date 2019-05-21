package br.com.randomthings.dto.specific;

import br.com.randomthings.domain.User;

public class UserDTO {
	private String email;
	private String password;
	
	public void fill(User user) {
		user.setEmail(email);
		user.setPassword(password);
	}
}
