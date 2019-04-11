package br.com.randomthings.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.randomthings.utils.Result;
import br.com.randomthings.viewhelper.CategoryViewHelper;
import br.com.randomthings.viewhelper.ProductViewHelper;


@Controller
@RequestMapping("/products")
public class ProductController extends AbstractController<ProductViewHelper>{
	@PostMapping
	public @ResponseBody ResponseEntity<Result> save(@Valid @ModelAttribute ProductViewHelper vh) {
		Result result = searchCommand("Save").execute(vh.getEntity());
		if (null != result.getResponse() && !result.getResponse().toString().isEmpty()) {
			result.setHttpStatus(400);
		} else {
			result.setHttpStatus(201);
		}
		return restResponse(result);
	}
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Result> update(@Valid @ModelAttribute ProductViewHelper vh, @PathVariable Long id) {
		Result result = searchCommand("Update").execute(vh.getEntity(id));
		if (null != result.getResponse() && !result.getResponse().toString().isEmpty()) {
			result.setHttpStatus(400);
		} else {
			result.setHttpStatus(200);
		}
		return restResponse(result);
	}
}
