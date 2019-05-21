package br.com.randomthings.controller.specific;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.services.change_coupon.web.ChangeCouponServiceWeb;

@CrossOrigin
@RestController
@RequestMapping("/coupons")
public class CouponController {
	
	@Autowired
	private ChangeCouponServiceWeb changeCouponServiceWeb;
	
	@RequestMapping(value = "/byIdClient/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllByIdClient(@PathVariable("id") Long id){
		return ResponseEntity.ok(
				changeCouponServiceWeb.getByIdClient(id));
	}
	
	@RequestMapping(value = "/byIdClientAndName/{id}/{name}", method=RequestMethod.GET)
	public ResponseEntity<?> getByIdClientAndName(@PathVariable("id") Long id, @PathVariable("name") String name){
		return ResponseEntity.ok(
				changeCouponServiceWeb.getByIdClientAndName(id, name));
	}
	
}
