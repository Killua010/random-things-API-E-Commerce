package br.com.randomthings.services.coupons;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.controller.generic.PromotionalCouponController;
import br.com.randomthings.domain.PromotionalCoupon;
import br.com.randomthings.domain.User;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.PromotionalCouponRepository;
import br.com.randomthings.repository.ContactRepository;
import br.com.randomthings.repository.UserRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class PromotionalCouponServiceImpl extends AbstractService<PromotionalCoupon, Long> implements PromotionalCouponService {
	@Autowired
	private PromotionalCouponRepository promotionalCouponRepository;
	
	public PromotionalCouponServiceImpl(PromotionalCouponRepository dao) {
		super(dao);
	}

	@Override
	public PromotionalCoupon findByName(String name) {
		return promotionalCouponRepository.findByName(name).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! nome: " + name
				+ ", tipo: " + PromotionalCoupon.class.getSimpleName()));
	}
	
}
