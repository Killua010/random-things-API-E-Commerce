package br.com.randomthings.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name="_coupon_payment")
public class CouponPayment extends DomainEntity {
	
	private Float promotionalCouponValue;
	
	private Float changeCouponValue;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.REFRESH)
    @JoinColumn(name = "change_coupon_id")
	private ChangeCoupon changeCoupon;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.REFRESH)
    @JoinColumn(name = "promotional_coupon_id")
	private PromotionalCoupon promotionalCoupon;
}
