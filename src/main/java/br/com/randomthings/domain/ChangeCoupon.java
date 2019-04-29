package br.com.randomthings.domain;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.devskiller.friendly_id.FriendlyId;

import lombok.Data;

@Data
@Entity(name = "_change_coupon")
public class ChangeCoupon extends DomainEntity {
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	private float value;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.REFRESH)
    @JoinColumn(name = "client_id")
	private Client client;
	
	public ChangeCoupon() {
		super();
		this.name = FriendlyId.toFriendlyId(UUID.randomUUID()); 
	}
}
