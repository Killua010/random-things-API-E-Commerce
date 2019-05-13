package br.com.randomthings.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.randomthings.domain.PromotionalCoupon;
import lombok.Data;

@Data
public class PromotionalCouponDTO extends EntityDTO {
	private Float value;
	
	private String shelfLife;
	
	private String name;
	
	public static PromotionalCouponDTO from(PromotionalCoupon coupon) {
		PromotionalCouponDTO promotionalCouponDTO = new PromotionalCouponDTO();
		
		promotionalCouponDTO.setCreationDate(coupon.getCreationDate());
		promotionalCouponDTO.setId(coupon.getId());
		promotionalCouponDTO.setLastUpdate(coupon.getLastUpdate());
		promotionalCouponDTO.setStatus(coupon.getStatus());
		promotionalCouponDTO.setShelfLife(coupon.getShelfLife().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		promotionalCouponDTO.setValue(coupon.getValue());
		promotionalCouponDTO.setName(coupon.getName());
		
		return promotionalCouponDTO;
	}
	
	public void fill(PromotionalCoupon promotionalCoupon) {
		promotionalCoupon.setShelfLife(LocalDate.parse(shelfLife));
		promotionalCoupon.setValue(value);
	}
	
}
