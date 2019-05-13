package br.com.randomthings.services.change_coupon.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.ChangeCoupon;
import br.com.randomthings.domain.Client;
import br.com.randomthings.dto.ChangeCouponDTO;
import br.com.randomthings.services.change_coupon.ChangeCouponService;
import br.com.randomthings.services.client.ClientService;

@Service
public class ChangeCouponServiceWebImpl implements ChangeCouponServiceWeb{

	@Autowired
	private ChangeCouponService changeCouponService;
	
	@Autowired
	private ClientService clientService;
	
	@Override
	public List<ChangeCouponDTO> getByIdClient(Long id) {
		Client client = clientService.findById(id);
		List<ChangeCouponDTO> coupons = new ArrayList<>(); 
		for(ChangeCoupon coupon: changeCouponService.getByClient(client)) {
			coupons.add(ChangeCouponDTO.from(coupon));
		}
		
		return coupons;
	}

	@Override
	public ChangeCouponDTO getByIdClientAndName(Long id, String name) {
		Client client = clientService.findById(id);
		ChangeCoupon cg = changeCouponService.findByNameAndClient(name, client);

		return ChangeCouponDTO.from(cg);
	}

}
