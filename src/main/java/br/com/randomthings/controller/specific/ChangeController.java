package br.com.randomthings.controller.specific;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.dto.ChangeDTO;
import br.com.randomthings.dto.OrderDTO;
import br.com.randomthings.dto.specific.GetOrderDTO;
import br.com.randomthings.services.change.web.ChangeServiceWeb;

@CrossOrigin
@RestController
@RequestMapping("/changes")
public class ChangeController {
	
	@Autowired
	private ChangeServiceWeb changeServiceWeb;
	
	@PreAuthorize("hasAnyRole('CLIENT')")
	@RequestMapping(value = "", method=RequestMethod.POST)
	public ResponseEntity<?> saveOrder(@RequestBody ChangeDTO changeDTO) {
		changeServiceWeb.save(changeDTO);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{status}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllByStatus(@PathVariable("status") String status){
		return ResponseEntity.ok(
				changeServiceWeb.getByStatus(status));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/aproved/{id}", method=RequestMethod.POST)
	public ResponseEntity<OrderDTO> aprovedChange(@PathVariable("id") Long id) {
		changeServiceWeb.aprovedChange(id);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/reproved/{id}", method=RequestMethod.POST)
	public ResponseEntity<OrderDTO> reprovedChange(@PathVariable("id") Long id) {
		changeServiceWeb.reprovedChange(id);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAnyRole('CLIENT')")
	@RequestMapping(value = "/byIdClient/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllByIdClient(@PathVariable("id") Long id){
		return ResponseEntity.ok(
				changeServiceWeb.getByIdClient(id));
	}
	
}
