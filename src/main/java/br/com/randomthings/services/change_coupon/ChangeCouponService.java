package br.com.randomthings.services.change_coupon;

import java.util.List;

import br.com.randomthings.domain.ChangeCoupon;
import br.com.randomthings.domain.Client;
import br.com.randomthings.services.IService;

public interface ChangeCouponService extends IService<ChangeCoupon> {

	public List<ChangeCoupon> getByClient(Client client);
	
	public ChangeCoupon findByNameAndClient(String name, Client client);

}
