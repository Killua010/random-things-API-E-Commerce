package br.com.randomthings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.randomthings.dto.CategoryDto;
import br.com.randomthings.dto.PricingGroupDto;

@Controller
@RequestMapping("/pricinggroup")
public class PricingController extends AbstractController<PricingGroupDto>{

}
