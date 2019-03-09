package br.com.randomthings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.randomthings.dto.TechnicalFieldDto;


@Controller
@RequestMapping("/technicalfields")
public class TechnicalFieldController extends AbstractController<TechnicalFieldDto>{

}
