package br.com.randomthings.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name="_address")
@Inheritance(strategy=InheritanceType.JOINED)
public class Address extends DomainEntity{
	
	private String street;
	
	private Integer number;
	
	private String neighborhood;
	
	private String zipCode;
	
	private String observation;
	
	private Boolean favorite;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residence_type_id")
	private ResidenceType residenceType;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
	private City city;
	
}
