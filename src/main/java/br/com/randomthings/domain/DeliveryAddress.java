package br.com.randomthings.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name="_delivery_address")
public class DeliveryAddress extends Address {
	private String fullName;
	
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name="client_id")
	private Client client;
}
