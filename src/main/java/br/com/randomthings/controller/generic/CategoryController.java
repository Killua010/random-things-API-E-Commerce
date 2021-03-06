package br.com.randomthings.controller.generic;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.randomthings.domain.Category;
import br.com.randomthings.dto.CategoryDTO;
import br.com.randomthings.services.category.web.CategoryServiceWeb;
import br.com.randomthings.utils.Result;


@Controller
@RequestMapping("/categories")
public class CategoryController extends AbstractController<CategoryDTO>{
	
	@Autowired
	private CategoryServiceWeb categoryServiceWeb;
	
	@PostMapping
	public @ResponseBody ResponseEntity<Result> save(@Valid @ModelAttribute CategoryDTO dto) {
		Result result = searchCommand("Save").execute(dto.fill());
		result.setHttpStatus(201);
		return restResponse(result);
	}
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Result> update(@Valid @ModelAttribute CategoryDTO dto, @PathVariable Long id) {
		Result result = searchCommand("Update").execute(dto.fill(id));
		result.setHttpStatus(200);
		return restResponse(result);
	}
	
	@RequestMapping(path="/popular", method=RequestMethod.GET)
	public ResponseEntity<?> getPopularCategories(){
		return ResponseEntity.ok(categoryServiceWeb.getPopularCategory());
	}

}
