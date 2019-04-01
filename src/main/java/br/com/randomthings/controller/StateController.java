package br.com.randomthings.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.dto.StateDTO;
import br.com.randomthings.services.state.StateService;

@CrossOrigin
@RestController
@RequestMapping("/states")
public class StateController {
	
	@Autowired
	private StateService stateService;
	
	@GetMapping
	public ResponseEntity<List<StateDTO>> getAll() {
		List<StateDTO> statesDTO = stateService.findAll().stream().map(state -> new StateDTO().from(state))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(statesDTO);

	}
}
