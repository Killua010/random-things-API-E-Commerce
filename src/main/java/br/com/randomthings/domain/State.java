package br.com.randomthings.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="_state")
public class State extends NamedEntity {
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
	private Country country;
	
	@Column(name="sc")
	private String stateCode;
	
	@OneToMany(mappedBy = "state")
	private Set<City> cities = new HashSet<>();
	
}
