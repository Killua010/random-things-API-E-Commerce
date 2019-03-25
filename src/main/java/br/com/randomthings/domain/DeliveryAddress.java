package br.com.randomthings.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="_delivery_address")
public class DeliveryAddress extends Address {
	
	@Column(length = 100)
	private String fullName;
	
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name="client_id")
	private Client client;
}
