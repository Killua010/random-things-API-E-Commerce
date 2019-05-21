package br.com.randomthings.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.PromotionalCoupon;

public interface PromotionalCouponRepository extends IRepository<PromotionalCoupon, Long>, JpaRepository<PromotionalCoupon, Long> {
	
	Optional<PromotionalCoupon> findByName(String name);
	
}
