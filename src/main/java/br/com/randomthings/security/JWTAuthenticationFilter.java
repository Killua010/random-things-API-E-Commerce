package br.com.randomthings.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.randomthings.dto.specific.LoginDTO;
import br.com.randomthings.utils.SystemVariable;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	
	private AuthenticationManager authenticationManager;
	
	private JWTUtil jwtUtil;
	
	@Autowired
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			LoginDTO loginDTO = new ObjectMapper()
					.readValue(request.getInputStream(), LoginDTO.class);
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					loginDTO.getEmail(), SystemVariable.encrypt(loginDTO.getPassword()), new ArrayList<>());
			
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			
			return authentication;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String username = ((UserSecurity) authResult.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		response.addHeader("Authorization", "Bearer " + token );
	}

	
	
}
