package br.com.randomthings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.randomthings.viewhelper.SubCategoryViewHelper;


@Controller
@RequestMapping("/subcategories")
public class SubCategoryController extends AbstractController<SubCategoryViewHelper>{

}
