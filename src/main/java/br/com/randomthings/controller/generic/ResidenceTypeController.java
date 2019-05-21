package br.com.randomthings.controller.generic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.dto.ResidenceTypeDTO;

@RestController
@RequestMapping("/residenceTypes")
public class ResidenceTypeController extends AbstractController<ResidenceTypeDTO> {
	
}
