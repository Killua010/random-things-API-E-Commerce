package br.com.randomthings.services.coupons;

import br.com.randomthings.domain.PromotionalCoupon;
import br.com.randomthings.dto.PromotionalCouponDTO;
import br.com.randomthings.services.IService;

public interface PromotionalCouponService extends IService<PromotionalCoupon, Long> {
	
	public PromotionalCoupon findByName(String name);
	
}
