package br.com.randomthings.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.dto.StatusActivationDTO;

@CrossOrigin
@RestController
@RequestMapping("/statusActivation")
public class StatusActivationController {
	
	@GetMapping
	public ResponseEntity<?> buscar() {
		return ResponseEntity.ok().body(StatusActivationDTO.getAll());

	}
}
