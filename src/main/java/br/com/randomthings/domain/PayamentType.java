	package br.com.randomthings.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name="_payment_type")
public class PayamentType extends DomainEntity{
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "card_payment_id")
	private CardPayment cardPayment;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "coupon_payment_id")
	private CouponPayment couponPayment;
}
