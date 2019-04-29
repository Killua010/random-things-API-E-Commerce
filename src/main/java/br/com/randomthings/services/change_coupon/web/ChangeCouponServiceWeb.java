package br.com.randomthings.services.change_coupon.web;

import java.util.List;

import br.com.randomthings.dto.ChangeCouponDTO;

public interface ChangeCouponServiceWeb {

	public List<ChangeCouponDTO> getByIdClient(Long id);

}
