package br.com.randomthings.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.Product;
import br.com.randomthings.services.product.ProductService;
import br.com.randomthings.utils.Result;
import br.com.randomthings.viewhelper.ProductViewHelper;


@Controller
@RequestMapping("/products")
public class ProductController extends AbstractController<ProductViewHelper>{
	@Autowired
	private ProductService productService;
	
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
	
	@RequestMapping(path = "/paging/{page}", method = RequestMethod.GET)
	public ResponseEntity<?> pagingProduct(@PathVariable(name="page",required=true) Integer pageNumber){
		Integer qtdPage = 9; 
		String orderBy = "name";
		String direction = "ASC";
		List<ProductViewHelper> helpers = new ArrayList<>();
		Page<Product> pages = productService.getPageabled(pageNumber, qtdPage, direction, orderBy);
		for(Product product : pages) {
			ProductViewHelper helper = (ProductViewHelper) new ProductViewHelper().getViewHelper(product);
			helper.setTotalPage(pages.getTotalPages());
			helpers.add(helper);
		}
		return ResponseEntity.ok(helpers);
	}
}
