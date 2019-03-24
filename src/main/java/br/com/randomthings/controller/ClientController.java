package br.com.randomthings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.domain.Client;
import br.com.randomthings.dto.ClientDTO;
import br.com.randomthings.services.client.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService ClientService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ClientDTO> find(@PathVariable Long id ) {
		Client client = ClientService.findById(id);
		return ResponseEntity.ok()
				.body(ClientDTO.from(client));
	}
}
