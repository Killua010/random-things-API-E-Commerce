package br.com.randomthings.domain;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity(name = "_promotional_coupon")
public class PromotionalCoupon extends Coupon {
	
	private LocalDate shelfLife;

}
