package br.com.randomthings.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="_client")
public class Client extends Person {
	
	@Basic
	@Column(length = 11, nullable = false, unique = true)
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
