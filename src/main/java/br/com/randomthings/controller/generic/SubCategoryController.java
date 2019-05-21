package br.com.randomthings.controller.generic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.randomthings.dto.SubCategoryDTO;


@Controller
@RequestMapping("/subcategories")
public class SubCategoryController extends AbstractController<SubCategoryDTO>{

}
