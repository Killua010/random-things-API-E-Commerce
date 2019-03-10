package br.com.randomthings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.randomthings.viewhelper.CategoryViewHelper;
import br.com.randomthings.viewhelper.PricingGroupViewHelper;

@Controller
@RequestMapping("/pricinggroup")
public class PricingController extends AbstractController<PricingGroupViewHelper>{

}
