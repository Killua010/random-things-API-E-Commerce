package br.com.randomthings.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.randomthings.domain.PromotionalCoupon;
import br.com.randomthings.dto.PromotionalCouponDTO;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.services.coupons.PromotionalCouponService;
import br.com.randomthings.services.coupons.web.PromotionalCouponServiceWeb;

@CrossOrigin
@RestController
@RequestMapping("/promotionalCoupons")
public class PromotionalCouponController {
	
	@Autowired
	private PromotionalCouponService promotionalCouponService;
	
	@Autowired
	private PromotionalCouponServiceWeb promotionalCouponServiceWeb;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<PromotionalCouponDTO>> findAll() {
		List<PromotionalCoupon> coupons = promotionalCouponService.findAll();
		List<PromotionalCouponDTO> couponDTOs = new ArrayList<PromotionalCouponDTO>();
		
		for(PromotionalCoupon coupon: coupons) {
			couponDTOs.add(PromotionalCouponDTO.from(coupon));
		}
		
		return ResponseEntity.ok().body(couponDTOs);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PromotionalCouponDTO> find(@PathVariable Long id ) {
		return ResponseEntity.ok()
				.body(PromotionalCouponDTO.from(promotionalCouponService.findById(id)));
	}
	
	@RequestMapping(value="/findByName/{name}", method=RequestMethod.GET)
	public ResponseEntity<PromotionalCouponDTO> find(@PathVariable String name) {
		return ResponseEntity.ok()
				.body(promotionalCouponServiceWeb.findByName(name));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody PromotionalCouponDTO promotionalCouponDTO) throws StrategyValidation {
		PromotionalCoupon promotionalCoupon = promotionalCouponServiceWeb.save(promotionalCouponDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(promotionalCoupon.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		promotionalCouponService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
