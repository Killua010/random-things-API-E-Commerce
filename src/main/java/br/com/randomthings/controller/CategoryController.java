package br.com.randomthings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.randomthings.viewhelper.CategoryViewHelper;


@Controller
@RequestMapping("/categories")
public class CategoryController extends AbstractController<CategoryViewHelper>{

}
