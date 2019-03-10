package br.com.randomthings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.randomthings.viewhelper.CategoryViewHelper;
import br.com.randomthings.viewhelper.ProductViewHelper;


@Controller
@RequestMapping("/products")
public class ProductController extends AbstractController<ProductViewHelper>{

}
