package br.com.randomthings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.dto.OrderDTO;
import br.com.randomthings.services.order.web.OrderServiceWeb;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderServiceWeb orderServiceWeb;
	
	@RequestMapping(value = "", method=RequestMethod.POST)
	public ResponseEntity<?> saveOrder(@RequestBody OrderDTO orderDTO) {
		orderServiceWeb.save(orderDTO);
		return ResponseEntity.ok("");
	}
	
}
