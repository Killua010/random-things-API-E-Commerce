package br.com.randomthings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.randomthings.domain.Activation;
import br.com.randomthings.viewhelper.ActivationViewHelper;
import br.com.randomthings.viewhelper.CategoryViewHelper;


@Controller
@RequestMapping("/activations")
public class ActivationController extends AbstractController<ActivationViewHelper>{

}
