package br.com.randomthings.controller.specific;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.domain.Gender;
import br.com.randomthings.domain.TelephoneType;

@CrossOrigin
@RestController
@RequestMapping("/telephonesTypes")
public class TelephoneTypesController {

	@GetMapping
	public ResponseEntity<?> find() {
		return ResponseEntity.ok().body(TelephoneType.values());

	}
}
