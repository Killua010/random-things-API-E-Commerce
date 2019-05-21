package br.com.randomthings.controller.specific;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.dto.CreditCardDTO;
import br.com.randomthings.dto.FlagDTO;
import br.com.randomthings.dto.StateDTO;
import br.com.randomthings.services.flags.FlagService;
import br.com.randomthings.services.state.StateService;

@CrossOrigin
@RestController
@RequestMapping("/flags")
public class CreditCardFlagsController {
	
	@Autowired
	private FlagService flagService;
	
	@GetMapping
	public ResponseEntity<List<FlagDTO>> getAll() {
		List<FlagDTO> flagsDTO = flagService.findAll().stream().map(state -> (FlagDTO)new FlagDTO().from(state))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(flagsDTO);

	}
}
