package br.com.randomthings.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.randomthings.domain.Client;
import br.com.randomthings.dto.ClientDTO;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.services.client.ClientService;
import br.com.randomthings.services.client.web.ClientServiceWeb;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientServiceWeb clientServiceWeb;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ClientDTO> find(@PathVariable Long id ) {
		Client client = clientService.findById(id);
		return ResponseEntity.ok()
				.body(ClientDTO.from(client));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody ClientDTO clientDTO) throws StrategyValidation {
		Client Client = clientServiceWeb.save(clientDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Client.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
