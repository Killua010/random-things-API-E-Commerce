package br.com.randomthings.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.User;
import br.com.randomthings.services.user.UserService;

@Service
public class UserDetailIServicempl implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByEmail(username);
		Collection<? extends GrantedAuthority> authorities = user.getRoles().stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
		return new UserSecurity(user.getId(), user.getEmail(), user.getPassword(), authorities);
	}

}
