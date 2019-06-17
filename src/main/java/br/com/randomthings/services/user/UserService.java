package br.com.randomthings.services.user;

import br.com.randomthings.domain.User;
import br.com.randomthings.services.IService;

public interface UserService {
	public User findByEmail(String email);
}
