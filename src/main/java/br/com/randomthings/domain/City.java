package br.com.randomthings.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="_city")
public class City extends NamedEntity {
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
	private State state;
}
