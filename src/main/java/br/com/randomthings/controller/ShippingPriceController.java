package br.com.randomthings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.dto.DeliveryAddressDTO;
import br.com.randomthings.services.shipping_price.web.ShippingPriceWebService;

@CrossOrigin
@RestController
@RequestMapping("/shipping")
public class ShippingPriceController {
	
	@Autowired
	private ShippingPriceWebService priceWebService;
	
	@RequestMapping(value = "/calculete/client/{clientId}", method=RequestMethod.POST)
	public ResponseEntity<Float> getShippingPrice(@PathVariable("clientId") Long clientId, @RequestBody DeliveryAddressDTO addressDTO) {
		return ResponseEntity.ok(priceWebService.calculatePrice(clientId, addressDTO.getId()));
	}

}
