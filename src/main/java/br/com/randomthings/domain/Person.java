package br.com.randomthings.domain;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@AttributeOverride(name = "name", column = @Column(name = "first_name"))
public class Person extends NamedEntity {
	
	@Column(name = "last_name", length = 100, nullable = false)
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private LocalDate birthDate;
}
