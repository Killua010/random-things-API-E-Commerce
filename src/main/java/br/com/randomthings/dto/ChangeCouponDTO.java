package br.com.randomthings.dto;

import br.com.randomthings.domain.ChangeCoupon;
import lombok.Data;

@Data
public class ChangeCouponDTO {
	private String name;
	private Float value;
	private boolean status;
	
	public static ChangeCouponDTO from(ChangeCoupon coupon) {
		ChangeCouponDTO changeCouponDTO = new ChangeCouponDTO();
		changeCouponDTO.setName(coupon.getName());
		changeCouponDTO.setStatus(coupon.getStatus());
		changeCouponDTO.setValue(coupon.getValue());
		return changeCouponDTO;
	}

}
