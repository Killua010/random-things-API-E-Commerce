package br.com.randomthings.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="_shopping_cart")
public class ShoppingCart extends DomainEntity {

	private Float subTotal;
	
	@OneToMany(mappedBy="cart", cascade=CascadeType.ALL)
	private Set<ShoppingCartItem> cartItems = new HashSet<>();
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
	private Client client;
}
