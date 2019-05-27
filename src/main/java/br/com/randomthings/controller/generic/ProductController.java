package br.com.randomthings.controller.generic;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.randomthings.dto.ProductDTO;
import br.com.randomthings.services.product.web.ProductServiceWeb;
import br.com.randomthings.utils.Result;


@Controller
@RequestMapping("/products")
public class ProductController extends AbstractController<ProductDTO>{
	@Autowired
	private ProductServiceWeb productServiceWeb;
	
	@PostMapping
	public @ResponseBody ResponseEntity<Result> save(@Valid @ModelAttribute ProductDTO dto) {
		Result result = searchCommand("Save").execute(dto.fill());
		if (null != result.getResponse() && !result.getResponse().toString().isEmpty()) {
			result.setHttpStatus(400);
		} else {
			result.setHttpStatus(201);
		}
		return restResponse(result);
	}
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Result> update(@Valid @ModelAttribute ProductDTO dto, @PathVariable Long id) {
		Result result = searchCommand("Update").execute(dto.fill(id));
		if (null != result.getResponse() && !result.getResponse().toString().isEmpty()) {
			result.setHttpStatus(400);
		} else {
			result.setHttpStatus(200);
		}
		return restResponse(result);
	}
	
	@RequestMapping(path = "/paging/{page}", method = RequestMethod.GET)
	public ResponseEntity<?> pagingProduct(@PathVariable(name="page",required=true) Integer pageNumber, 
			@RequestParam(name = "categoryId", required = false) Long subCategoryId){
		
		Integer qtdPage = 9; 
		String orderBy = "name";
		String direction = "ASC";
		List<ProductDTO> helpers = new ArrayList<>();
		
		if(null == subCategoryId) {
			helpers = productServiceWeb.getPageabled(pageNumber, qtdPage, direction, orderBy);
		} else {
			helpers = productServiceWeb.getPageabledByCategory(pageNumber, qtdPage, direction, orderBy, subCategoryId);
		}
		
		return ResponseEntity.ok(helpers);
	}
	
	@RequestMapping(path = "/findBy/{param}", method = RequestMethod.GET)
	public ResponseEntity<?> findBy(@PathVariable(name="param",required=true) String param){
		return ResponseEntity.ok(productServiceWeb.findBy(param));
	}
	
	@RequestMapping(path = "/findByCategory/{categoryId}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductDTO>> findByCategoryId(@PathVariable(name="categoryId",required=true) Long categoryId){
		return ResponseEntity.ok(productServiceWeb.findByCategoryId(categoryId));
	}
}
