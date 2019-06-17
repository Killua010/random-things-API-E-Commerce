package br.com.randomthings.controller.specific;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.dto.specific.StatusInactivationDTO;

@CrossOrigin
@RestController
@RequestMapping("/statusInactivation")
public class StatusInactivationController {

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<?> buscar() {
		return ResponseEntity.ok().body(StatusInactivationDTO.getAll());

	}
}
