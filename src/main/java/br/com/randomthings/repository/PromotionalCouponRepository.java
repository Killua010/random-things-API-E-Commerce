package br.com.randomthings.repository;

import java.util.Optional;

import br.com.randomthings.domain.PromotionalCoupon;

public interface PromotionalCouponRepository extends RepositoryImpl<PromotionalCoupon>, IRepository<PromotionalCoupon> {
	
	Optional<PromotionalCoupon> findByName(String name);
	
}
