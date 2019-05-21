package br.com.randomthings.controller.generic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.randomthings.dto.InactivationDTO;


@Controller
@RequestMapping("/inactivations")
public class InactivationController extends AbstractController<InactivationDTO>{

}
