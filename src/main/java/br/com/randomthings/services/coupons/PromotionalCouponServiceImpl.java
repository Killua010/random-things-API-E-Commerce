package br.com.randomthings.services.coupons;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.PromotionalCoupon;
import br.com.randomthings.domain.User;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.PromotionalCouponRepository;
import br.com.randomthings.repository.ContactRepository;
import br.com.randomthings.repository.UserRepository;

@Service
public class PromotionalCouponServiceImpl implements PromotionalCouponService {
	
	@Autowired
	private PromotionalCouponRepository promotionalCouponRepository;
	
	public PromotionalCoupon findById(Long id) {		
		return promotionalCouponRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + PromotionalCoupon.class.getSimpleName()));
	}

	@Override
	@Transactional 
	public PromotionalCoupon save(PromotionalCoupon domain) {
		return promotionalCouponRepository.save(domain);
	}

	@Override
	@Transactional 
	public void delete(Long id) {
		PromotionalCoupon PromotionalCoupon = findById(id);
		promotionalCouponRepository.delete(PromotionalCoupon);
	}

	@Override
	public List<PromotionalCoupon> findAll() {
		return promotionalCouponRepository.findAll();
	}

	@Override
	public PromotionalCoupon findByName(String name) {
		return promotionalCouponRepository.findByName(name).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! nome: " + name
				+ ", tipo: " + PromotionalCoupon.class.getSimpleName()));
	}

	
}
