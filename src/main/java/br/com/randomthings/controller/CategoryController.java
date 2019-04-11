package br.com.randomthings.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.randomthings.utils.Result;
import br.com.randomthings.viewhelper.CategoryViewHelper;


@Controller
@RequestMapping("/categories")
public class CategoryController extends AbstractController<CategoryViewHelper>{
	
	@PostMapping
	public @ResponseBody ResponseEntity<Result> save(@Valid @ModelAttribute CategoryViewHelper vh) {
		Result result = searchCommand("Save").execute(vh.getEntity());
		if (null != result.getResponse() && !result.getResponse().toString().isEmpty()) {
			result.setHttpStatus(400);
		} else {
			result.setHttpStatus(201);
		}
		return restResponse(result);
	}
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Result> update(@Valid @ModelAttribute CategoryViewHelper vh, @PathVariable Long id) {
		Result result = searchCommand("Update").execute(vh.getEntity(id));
		if (null != result.getResponse() && !result.getResponse().toString().isEmpty()) {
			result.setHttpStatus(400);
		} else {
			result.setHttpStatus(200);
		}
		return restResponse(result);
	}

}
