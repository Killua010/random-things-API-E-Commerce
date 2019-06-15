package br.com.randomthings.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import br.com.randomthings.domain.PromotionalCoupon;
import lombok.Data;

@Data
@Component
public class PromotionalCouponDTO extends AbstractDTO<PromotionalCoupon> {
	private Float value;
	
	private String shelfLife;
	
	private String name;

	@Override
	public IDTO from(PromotionalCoupon coupon) {
		PromotionalCouponDTO promotionalCouponDTO = new PromotionalCouponDTO();
		this.from(coupon, promotionalCouponDTO);

		promotionalCouponDTO.setShelfLife(coupon.getShelfLife().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		promotionalCouponDTO.setValue(coupon.getValue());
		promotionalCouponDTO.setName(coupon.getName());
		
		return promotionalCouponDTO;
	}

	@Override
	public PromotionalCoupon fill(Long... params) {
		PromotionalCoupon promotionalCoupon = new PromotionalCoupon();
		promotionalCoupon.setShelfLife((null == shelfLife) ? null: LocalDate.parse(shelfLife) );
		promotionalCoupon.setValue((null == value) ? null: value);
		promotionalCoupon.setId((params.length == 0) ? null : params[0]);
		promotionalCoupon.setStatus((null == this.status) ? null : this.status);
		
		return promotionalCoupon;
	}
	
}
