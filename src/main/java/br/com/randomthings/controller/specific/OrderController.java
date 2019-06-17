package br.com.randomthings.controller.specific;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.domain.Order;
import br.com.randomthings.dto.OrderDTO;
import br.com.randomthings.dto.specific.GetOrderDTO;
import br.com.randomthings.services.order.web.OrderServiceWeb;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderServiceWeb orderServiceWeb;
	
	@PreAuthorize("hasAnyRole('CLIENT')")
	@RequestMapping(value = "", method=RequestMethod.POST)
	public ResponseEntity<OrderDTO> saveOrder(@RequestBody GetOrderDTO orderDTO) {
		return ResponseEntity.ok((OrderDTO)new OrderDTO().from(
				orderServiceWeb.save(orderDTO)));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{status}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllByStatus(@PathVariable("status") String status){
		return ResponseEntity.ok(
				orderServiceWeb.getByStatus(status));
	}
	
	@PreAuthorize("hasAnyRole('CLIENT')")
	@RequestMapping(value = "/byIdClient/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllByIdClient(@PathVariable("id") Long id){
		return ResponseEntity.ok(
				orderServiceWeb.getByIdClient(id));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{orderId}/nextStep", method=RequestMethod.PUT)
	public ResponseEntity<?> alterStatus(@PathVariable("orderId") Long id){
		orderServiceWeb.nextStep(id);
		return ResponseEntity.noContent().build();
	}
	
}
