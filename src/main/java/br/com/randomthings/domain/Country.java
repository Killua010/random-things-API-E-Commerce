package br.com.randomthings.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="_country")
public class Country extends NamedEntity {
	
	@OneToMany(mappedBy = "country")
	private Set<State> states = new HashSet<>();
}
