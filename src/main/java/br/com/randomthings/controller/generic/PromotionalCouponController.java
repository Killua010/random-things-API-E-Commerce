package br.com.randomthings.controller.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.dto.PromotionalCouponDTO;
import br.com.randomthings.services.coupons.web.PromotionalCouponServiceWeb;

@CrossOrigin
@RestController
@RequestMapping("/promotionalCoupons")
public class PromotionalCouponController extends AbstractController<PromotionalCouponDTO>{

	@Autowired
	private PromotionalCouponServiceWeb promotionalCouponServiceWeb;
	
	@RequestMapping(value="/findByName/{name}", method=RequestMethod.GET)
	public ResponseEntity<PromotionalCouponDTO> find(@PathVariable String name) {
		return ResponseEntity.ok()
				.body(promotionalCouponServiceWeb.findByName(name));
	}
}
