package br.com.randomthings.domain;

import java.util.List;

import javax.persistence.Entity;

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
	private String email;
	private String password;
}
