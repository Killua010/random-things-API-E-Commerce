package br.com.randomthings.domain;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.devskiller.friendly_id.FriendlyId;

import lombok.Data;

@Data
@MappedSuperclass
public class Coupon extends DomainEntity {
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	private float value;
	
	public Coupon() {
		super();
		this.name = FriendlyId.toFriendlyId(UUID.randomUUID()); 
	}
}
