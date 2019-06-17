package br.com.randomthings.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="_user")
public class User extends DomainEntity {
	
	@Column(unique = true)
	private String email;
	private String password;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="_roles")
	private Set<Integer> roles = new HashSet<>();
	
	public Set<Role> getRoles(){
		return roles.stream().map(x -> Role.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addRole(Role role) {
		this.roles.add(role.getCod());
	}
}
