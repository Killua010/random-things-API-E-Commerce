package br.com.randomthings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.randomthings.dto.CategoryDto;


@Controller
@RequestMapping("/categories")
public class CategoryController extends AbstractController<CategoryDto>{

}
