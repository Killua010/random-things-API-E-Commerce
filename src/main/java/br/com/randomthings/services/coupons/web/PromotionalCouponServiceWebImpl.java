package br.com.randomthings.services.coupons.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.PromotionalCoupon;
import br.com.randomthings.dto.PromotionalCouponDTO;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.services.ExecuteStrategys;
import br.com.randomthings.services.coupons.PromotionalCouponService;

@Service
public class PromotionalCouponServiceWebImpl extends ExecuteStrategys<PromotionalCoupon> implements PromotionalCouponServiceWeb {
	
	@Autowired
	private PromotionalCouponService promotionalCouponService;

	@Override
	public PromotionalCouponDTO findByName(String name) {
		PromotionalCoupon coupon = promotionalCouponService.findByName(name);
		
		if(coupon.getShelfLife().isBefore(LocalDate.now())) {	
			throw new StrategyValidation(new StringBuilder("Cupom vencido no dia " + coupon.getShelfLife().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
		}
		
		return (PromotionalCouponDTO) new PromotionalCouponDTO().from(coupon);
	}

}
