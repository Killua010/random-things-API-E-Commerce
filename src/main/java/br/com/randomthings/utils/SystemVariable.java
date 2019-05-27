package br.com.randomthings.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class SystemVariable {

	public static String systemPath;
	
	private static String systemCrypt;
	
	@Value("${SYSTEMCRYPT}")
	private String crypt;
	
	@Value("${SYSTEMPATH}")
	public String path;
	
	@PostConstruct
	public void init() {
		this.systemPath = path;
		this.systemCrypt = crypt;
	}
	
	public static String encrypt(String password) {
		return Jwts.builder().setSubject(password)
				.signWith(SignatureAlgorithm.HS512, systemCrypt.getBytes()).compact();
	}
	
	public static String descrypt(String password) {
		return Jwts.parser().setSigningKey(systemCrypt.getBytes()).parseClaimsJws(password).getBody().getSubject();
	}
	
}
