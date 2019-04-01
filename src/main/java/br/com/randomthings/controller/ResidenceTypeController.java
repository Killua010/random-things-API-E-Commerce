package br.com.randomthings.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.dto.ResidenceTypeDTO;
import br.com.randomthings.services.residence_type.ResidenceTypeService;

@CrossOrigin
@RestController
@RequestMapping("/residenceTypes")
public class ResidenceTypeController {
	
	@Autowired
	private ResidenceTypeService residenceTypeService;
	
	@GetMapping
	public ResponseEntity<List<ResidenceTypeDTO>> getAll() {
		List<ResidenceTypeDTO> residenceTypesDTO = residenceTypeService.findAll().stream().map(residenceType -> new ResidenceTypeDTO().from(residenceType))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(residenceTypesDTO);

	}
}
