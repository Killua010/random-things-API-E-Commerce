package br.com.randomthings.dto;

import org.springframework.stereotype.Component;

import br.com.randomthings.domain.ChangeCoupon;
import lombok.Data;

@Data
@Component
public class ChangeCouponDTO extends AbstractDTO<ChangeCoupon>{
	private String name;
	private Float value;
	

	@Override
	public IDTO from(ChangeCoupon coupon) {
		ChangeCouponDTO changeCouponDTO = new ChangeCouponDTO();
		this.from(coupon, changeCouponDTO);
		
		changeCouponDTO.setName(coupon.getName());
		changeCouponDTO.setValue(coupon.getValue());
		
		return changeCouponDTO;
	}

	@Override
	public ChangeCoupon fill(Long... params) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}

}
