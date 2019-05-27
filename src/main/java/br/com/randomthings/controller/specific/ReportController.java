package br.com.randomthings.controller.specific;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.services.reports.web.ReportServiceWeb;

@CrossOrigin
@RestController
@RequestMapping("/reports")
public class ReportController {
	
	@Autowired
	private ReportServiceWeb reportServiceWeb;
	
	@RequestMapping(value = "/ordersCategory", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersCategory(){
		return ResponseEntity.ok(reportServiceWeb.getOrdersCategoryByMounths());
	}
	
	@RequestMapping(value = "/ordersCategory/gender/{gender}/{mounth}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersCategoryGender(@PathVariable("gender") String gender, @PathVariable("mounth") int mounth){
		return ResponseEntity.ok(reportServiceWeb.getOrdersCategoryGender(gender, mounth));
	}
	
	@RequestMapping(value = "/ordersCategory/gender/{gender}/age/{start}/{end}/{mounth}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersCategoryGenderAge(@PathVariable("gender") String gender, @PathVariable("start") int start, @PathVariable("end") int end,
			@PathVariable("mounth") int mounth){
		return ResponseEntity.ok(reportServiceWeb.getOrdersCategoryGenderAge(gender, start, end, mounth));
	}
		
	@RequestMapping(value = "/orders", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrders(){
		return ResponseEntity.ok(Arrays.asList(reportServiceWeb.getOrdersByMounths()));
	}
	
	@RequestMapping(value = "/orders/categories", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersByCategories(){
		return ResponseEntity.ok(reportServiceWeb.getOrdersByCategory());
	}
	
	@RequestMapping(value = "/orders/products", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersByProducts(){
		return ResponseEntity.ok(reportServiceWeb.getOrdersByProducts());
	}
	
	@RequestMapping(value = "/ordersProduct", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersProduct(){
		return ResponseEntity.ok(reportServiceWeb.getOrdersProductByMounths());
	}
	
	@RequestMapping(value = "/ordersProduct/gender/{gender}/{mounth}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersProductGender(@PathVariable("gender") String gender, @PathVariable("mounth") int mounth){
		return ResponseEntity.ok(reportServiceWeb.getOrdersProductGender(gender, mounth));
	}
	
	@RequestMapping(value = "/ordersProduct/gender/{gender}/age/{start}/{end}/{mounth}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersProductGenderAge(@PathVariable("gender") String gender, @PathVariable("start") int start, @PathVariable("end") int end,
			@PathVariable("mounth") int mounth){
		return ResponseEntity.ok(reportServiceWeb.getOrdersProductGenderAge(gender, start, end, mounth));
	}
	
}
