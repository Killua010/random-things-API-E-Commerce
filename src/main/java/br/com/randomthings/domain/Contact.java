package br.com.randomthings.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

@Data
@Entity(name="_contacts")
public class Contact extends DomainEntity {
	private String ddd;
	private String number;
	private String email;
	
	@Enumerated(EnumType.STRING)
	private TelephoneType telephoneType;
}
