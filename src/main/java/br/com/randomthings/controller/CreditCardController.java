package br.com.randomthings.controller;

import java.net.URI;

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

import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.dto.CreditCardDTO;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.services.card.CreditCardService;
import br.com.randomthings.services.card.web.CreditCardWebService;

@CrossOrigin
@RestController
@RequestMapping("/creditCards")
public class CreditCardController {
	
	@Autowired
	private CreditCardWebService creditCardWebService;
	
	@Autowired
	private CreditCardService creditCardService;
	
	@RequestMapping(path="/client/{idClient}", method=RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody CreditCardDTO creditCardDTO,
			@PathVariable("idClient") Long idClient) throws StrategyValidation {
		CreditCard address = creditCardWebService.save(creditCardDTO, idClient);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/client/{idClient}/{idCard}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@Valid @RequestBody CreditCardDTO creditCardDTO,
			@PathVariable("idClient") Long idClient, @PathVariable("idCard") Long idCard){
		creditCardDTO.setId(idCard);
		creditCardWebService.update(creditCardDTO);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		creditCardService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
}
