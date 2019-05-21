package br.com.randomthings.controller.generic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.randomthings.dto.ActivationDTO;


@Controller
@RequestMapping("/activations")
public class ActivationController extends AbstractController<ActivationDTO>{

}
