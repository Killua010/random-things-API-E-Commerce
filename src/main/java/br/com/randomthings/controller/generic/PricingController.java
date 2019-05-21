package br.com.randomthings.controller.generic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.randomthings.dto.PricingGroupDTO;

@Controller
@RequestMapping("/pricinggroup")
public class PricingController extends AbstractController<PricingGroupDTO>{

}
