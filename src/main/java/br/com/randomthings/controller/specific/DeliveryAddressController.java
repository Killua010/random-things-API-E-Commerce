package br.com.randomthings.controller.specific;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.dto.DeliveryAddressDTO;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.services.address.DeliveryAddressService;
import br.com.randomthings.services.address.web.DeliveryAddressWebService;

@CrossOrigin
@RestController
@RequestMapping("/deliveryAddress")
public class DeliveryAddressController {
	
	@Autowired
	private DeliveryAddressWebService deliveryAddressWebService;
	
	@Autowired
	private DeliveryAddressService deliveryAddressService;
	
	@PreAuthorize("hasAnyRole('CLIENT')")
	@RequestMapping(path="/client/{idClient}", method=RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody DeliveryAddressDTO deliveryAddressDTO,
			@PathVariable("idClient") Long idClient) throws StrategyValidation {
		DeliveryAddress address = deliveryAddressWebService.save(deliveryAddressDTO, idClient);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('CLIENT')")
	@RequestMapping(value="/client/{idClient}/{idAddress}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@Valid @RequestBody DeliveryAddressDTO deliveryAddressDTO,
			@PathVariable("idClient") Long idClient, @PathVariable("idAddress") Long idAddress){
		deliveryAddressDTO.setId(idAddress);
		deliveryAddressWebService.update(deliveryAddressDTO, idClient);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('CLIENT')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		deliveryAddressService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
