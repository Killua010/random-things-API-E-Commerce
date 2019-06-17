package br.com.randomthings.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bytebuddy.implementation.bytecode.Throw;

@AllArgsConstructor
@Getter
public enum Role {
	ADMIN(1, "ROLE_ADMIN"),
	CLIENT(2, "ROLE_CLIENT");
	
	private int cod;
	private String description;
	
	public static Role toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Role role: Role.values()) {
			if(cod.equals(role.getCod())) {
				return role;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
