package br.com.randomthings.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Entity(name="_order")
public class Order extends DomainEntity {
	
	private Float orderValue;
	
	@Column(name = "status_order", nullable = true)
	@Enumerated(EnumType.STRING)
	@Basic(fetch=FetchType.EAGER)
	private StatusOrder statusOrder;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "shipping_id")
	private ShippingPrice shippingPrice;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "payment_type_id")
	private PayamentType payamentType;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.REFRESH)
    @JoinColumn(name = "client_id")
	private Client client;
	
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<OrderItem> items = new HashSet<>();
}
