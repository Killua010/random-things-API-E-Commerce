package br.com.randomthings.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.devskiller.friendly_id.FriendlyId;

import lombok.Data;

@Data
@MappedSuperclass
public class Coupon extends DomainEntity {
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	private Float value;
	
	public Coupon() {
		super();
		this.name = FriendlyId.toFriendlyId(UUID.randomUUID()); 
	}
}
