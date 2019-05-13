package br.com.randomthings.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name="_shipping_price")
public class ShippingPrice extends DomainEntity {
	
	private Float value;
	
	private Integer businessDays;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.REFRESH)
    @JoinColumn(name = "address_id")
	private Address address;
}
