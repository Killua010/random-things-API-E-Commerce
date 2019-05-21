package br.com.randomthings.services.change_coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.ChangeCoupon;
import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.ChangeCouponRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class ChangeCouponServiceImpl extends AbstractService<ChangeCoupon, Long> implements ChangeCouponService{
	@Autowired
	private ChangeCouponRepository changeCouponRepository;
	
	public ChangeCouponServiceImpl(ChangeCouponRepository dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ChangeCoupon> getByClient(Client client) {
		return changeCouponRepository.findAllByClient(client);
	}

	@Override
	public ChangeCoupon findByNameAndClient(String name, Client client) {
		return changeCouponRepository.findByNameAndClient(name, client)
				.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! nome: " + name
				+ ", tipo: " + ChangeCoupon.class.getSimpleName()));
	}

}
