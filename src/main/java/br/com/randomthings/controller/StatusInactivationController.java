package br.com.randomthings.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.dto.StatusInactivationDTO;

@CrossOrigin
@RestController
@RequestMapping("/statusInactivation")
public class StatusInactivationController {
	
	@GetMapping
	public ResponseEntity<?> buscar() {
		return ResponseEntity.ok().body(StatusInactivationDTO.getAll());

	}
}
