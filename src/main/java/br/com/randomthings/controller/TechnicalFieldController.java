package br.com.randomthings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.randomthings.viewhelper.TechnicalFieldViewHelper;


@Controller
@RequestMapping("/technicalfields")
public class TechnicalFieldController extends AbstractController<TechnicalFieldViewHelper>{

}
