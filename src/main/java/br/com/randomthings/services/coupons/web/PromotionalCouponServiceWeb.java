package br.com.randomthings.services.coupons.web;

import br.com.randomthings.domain.PromotionalCoupon;
import br.com.randomthings.dto.PromotionalCouponDTO;
import br.com.randomthings.exception.StrategyValidation;

public interface PromotionalCouponServiceWeb {
	PromotionalCoupon save(PromotionalCouponDTO PromotionalCouponDTO) throws StrategyValidation;
	PromotionalCouponDTO findByName(String name);
}
