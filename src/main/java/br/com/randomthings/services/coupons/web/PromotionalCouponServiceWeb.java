package br.com.randomthings.services.coupons.web;

import br.com.randomthings.dto.PromotionalCouponDTO;

public interface PromotionalCouponServiceWeb {
	PromotionalCouponDTO findByName(String name);
}
