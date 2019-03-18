package br.com.randomthings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.randomthings.viewhelper.InactivationViewHelper;


@Controller
@RequestMapping("/inactivations")
public class InactivationController extends AbstractController<InactivationViewHelper>{

}
