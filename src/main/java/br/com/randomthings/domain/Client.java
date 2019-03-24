package br.com.randomthings.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Entity(name="_client")
public class Client extends Person {
	
	private String cpf;
	
	@ColumnDefault("0")
	private Integer rank;
	
	@OneToMany(mappedBy="client")
	private Set<DeliveryAddress> addresses = new HashSet<DeliveryAddress>();
	
	@OneToMany(mappedBy="client")
	private Set<CreditCard> cards = new HashSet<CreditCard>();
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
	private Contact contact;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	private User user;
}
