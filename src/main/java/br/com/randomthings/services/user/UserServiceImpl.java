package br.com.randomthings.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.User;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! email: " + email
		 + ", tipo: " + User.class.getSimpleName()));
	}

}
