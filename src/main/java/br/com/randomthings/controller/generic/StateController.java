package br.com.randomthings.controller.generic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.dto.StateDTO;


@RestController
@RequestMapping("/states")
public class StateController extends AbstractController<StateDTO> {

}
